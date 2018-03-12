package com.quantvalley.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;

import com.quantvalley.batch.model.Trade;

public class ReaderTasklet implements Tasklet{

	public static List<FlatFileItemReader<Trade>> readerList=new ArrayList<FlatFileItemReader<Trade>>();
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		// TODO Auto-generated method stub
		
		 FlatFileItemReader<Trade> reader = new FlatFileItemReader<Trade>();
	        reader.setResource(new ClassPathResource("trades.csv"));
	        reader.setLineMapper(new DefaultLineMapper<Trade>() {{
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames(new String[] {"stock", "time", "price", "shares" });
	            }});
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<Trade>() {{
	                setTargetType(Trade.class);
	            }});
	        }});
	        
	        readerList.add(reader);
		return RepeatStatus.FINISHED;
	}

}
