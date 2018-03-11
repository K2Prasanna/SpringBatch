package com.cts.hc.listeners;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ClaimChunkListener implements ChunkListener{

	public void afterChunk(ChunkContext arg0) {
		System.out.println("Chunk listener: " + arg0.toString());
		
	}

	public void afterChunkError(ChunkContext arg0) {
		System.err.println("Chunk listener: " + arg0.toString());
		
	}

	public void beforeChunk(ChunkContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
