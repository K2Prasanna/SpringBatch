package com.cts.hc.main;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ParallelSort {
public static void main(String[] args)
{
	System.out.println(Runtime.getRuntime().availableProcessors());
	System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
	ForkJoinPool commonPool = ForkJoinPool.commonPool();
	System.out.println("parallel: "+commonPool.getParallelism());
	
	Double[] array1 = getArray();
	long starttime = System.currentTimeMillis();
	Arrays.sort(array1);
	System.out.println("time" + (System.currentTimeMillis() - starttime));
	
	Double[] array2 = getArray();
	long starttime2 = System.currentTimeMillis();
	Arrays.parallelSort(array2);
	System.out.println("time" + (System.currentTimeMillis() - starttime2));

}

public static Double[] getArray()
{
	Double[] unsorted = new Double[10000000];
	for(int i=0; i < unsorted.length;i++)
	{
		unsorted[i] = Math.random();
		
	}
	
	return unsorted;

}
}
