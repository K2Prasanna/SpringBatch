package com.cts.hc.configurations;

import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.interceptor.Retryable;


public class FileVerificationRetry implements RetryPolicy {

	@Override
	public boolean canRetry(RetryContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(RetryContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RetryContext open(RetryContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerThrowable(RetryContext arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}
	
	}
