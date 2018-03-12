package com.quantvalley.batch.writer;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.quantvalley.batch.listener.JobCompletionNotificationListener;
import com.quantvalley.batch.model.FxMarketVolumeStore;
import com.quantvalley.batch.model.StockVolume;
import com.quantvalley.batch.processor.FxMarketEventProcessor;

public class Step2WriterTasklet implements Tasklet{
	

	private static final String HEADER = "stock,volume";

	private static final String LINE_DILM = ",";
	private FxMarketVolumeStore fxMarketVolumeStore;
	private static final Logger log = LoggerFactory.getLogger(StockVolumeAggregator.class);
	@Override
	public RepeatStatus execute(StepContribution step, ChunkContext arg1) throws Exception {
		// TODO Auto-generated method stub
		
		//System.out.println(FxMarketEventProcessor.tradeList.size());
		
		fxMarketVolumeStore=new FxMarketVolumeStore();
		FxMarketEventProcessor.tradeList.forEach(t -> {
			if(fxMarketVolumeStore!=null)
			{
			   if(t!=null)	
					
			   {
				   //System.out.println(t.getStock());
			    if (fxMarketVolumeStore.containsKey(t.getStock()) ){
			    	
					StockVolume stockVolume = fxMarketVolumeStore.get(t.getStock());
					long newVolume = stockVolume.getVolume() + t.getShares();
					// Increment stock volume
						stockVolume.setVolume(newVolume);
			    	} else {
			    		log.trace("Adding new stock {}", t.getStock());
			    		fxMarketVolumeStore.put(t.getStock(),
						new StockVolume(t.getStock(), t.getShares()));
			    	}
			}
			
			}
		});
		
		Path path = Paths.get("volume.csv");
		try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
			fileWriter.write(HEADER);
			fileWriter.newLine();
			System.out.println("job completion called...");
			System.out.println("size"+fxMarketVolumeStore.values().size());
			for (StockVolume pd : fxMarketVolumeStore.values()) {
				fileWriter.write(new StringBuilder().append(pd.getStock())
						.append(LINE_DILM).append(pd.getVolume()).toString());
				fileWriter.newLine();
			}
		} catch (Exception e) {
			log.error("Fetal error: error occurred while writing {} file", path.getFileName());
		}
		
		return RepeatStatus.FINISHED;
	}

}
