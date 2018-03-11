package com.cts.hc.configuration;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.cts.hc.listener.MTJobExecutionListener;
import com.cts.hc.models.Trade;
import com.cts.hc.models.TradeDB;
import com.cts.hc.store.TradeStore;
import com.cts.hc.workers.TradeProcessor;


@Configuration
@EnableBatchProcessing
@ComponentScan
//spring boot configuration
@EnableAutoConfiguration
// file that contains the properties
@PropertySource("classpath:application.properties")
public class MTBatchConfiguration {

    /*
        Load the properties
     */
    @Value("${database.driver}")
    private String databaseDriver;
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUsername;
    @Value("${database.password}")
    private String databasePassword;

    @Bean
    public TradeStore tradeStore()
    {
    	return new TradeStore();
    }

    /**
     * We define a bean that read each line of the input file.
     *
     * @return
     */
    @Bean
    public ItemReader<Trade> reader() {
        // we read a flat file that will be used to fill a Person object
        FlatFileItemReader<Trade> reader = new FlatFileItemReader<Trade>();
        // we pass as parameter the flat file directory
        reader.setResource(new ClassPathResource("trades.csv"));
        // we use a default line mapper to assign the content of each line to the Person object
        reader.setLineMapper(new DefaultLineMapper<Trade>() {{
            // we use a custom fixed line tokenizer
        	DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        	tokenizer.setNames("stock", "time", "price", "shares");
            setLineTokenizer(tokenizer);
            // as field mapper we use the name of the fields in the Person class
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Trade>() {{
                // we create an object Person
                setTargetType(Trade.class);
            }});
        }});
        return reader;
    }

    /**
     * The ItemProcessor is called after a new line is read and it allows the developer
     * to transform the data read
     * In our example it simply return the original object
     *
     * @return
     */
    @Bean
    public ItemProcessor<Trade, TradeDB> processor() {
        return new TradeProcessor();
    }

    /**
     * Nothing special here a simple JpaItemWriter
     * @return
     */
    @Bean
    public JpaItemWriter<TradeDB> writer() {
        JpaItemWriter<TradeDB> writer = new JpaItemWriter<TradeDB>();
        writer.setEntityManagerFactory(entityManagerFactory().getObject());
        return writer;
    }
    
    /**
     * Nothing special here a simple JpaItemWriter
     * @return
     */
    @Bean
    public ItemWriter<TradeDB> nullwriter() {
        return new ItemWriter<TradeDB>() {

			@Override
			public void write(List<? extends TradeDB> arg0) throws Exception {
			}
        	
        };
    }

    @Bean
    public JobExecutionListener jobExecutionListener()
    {
    	return new MTJobExecutionListener();
    }

    /**
     * This method declare the steps that the batch has to follow
     *
     * @param jobs
     * @param s1
     * @return
     */
    @Bean
    public Job processJob(JobBuilderFactory jobs, Step s1) {

        return jobs.get("import")
                .incrementer(new RunIdIncrementer()).listener(jobExecutionListener()) // because a spring config bug, this incrementer is not really useful
                .flow(s1)
                .end()
                .build();
    }


    /**
     * Step
     * We declare that every 1000 lines processed the data has to be committed
     *
     * @param stepBuilderFactory
     * @param reader
     * @param writer
     * @param processor
     * @return
     */
    @Bean
    public TaskExecutor taskExecutor(){

        SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");

        asyncTaskExecutor.setConcurrencyLimit(5);

        return asyncTaskExecutor;

    }
    



    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Trade> reader,
                      ItemWriter<TradeDB> writer, ItemProcessor<Trade, TradeDB> processor) {
        return stepBuilderFactory.get("step1")
                .<Trade, TradeDB>chunk(10000)
                .reader(reader)
                .processor(processor)
                .writer(nullwriter()).taskExecutor(taskExecutor())
                .build();
    }

    /**
     * As data source we use an external database
     *
     * @return
     */

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setPackagesToScan("com.cts.hc.models");
        lef.setDataSource(dataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setJpaProperties(new Properties());
        return lef;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);

        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return jpaVendorAdapter;
    }

}