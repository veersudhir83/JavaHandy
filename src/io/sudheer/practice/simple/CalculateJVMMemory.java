package io.sudheer.practice.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CalculateJVMMemory {
	public static void main(String[] args) {
		HashMap map = new HashMap();
		ArrayList<Object> al = new ArrayList<Object>();
	    Runtime rt = Runtime.getRuntime();
	    long prevTotal = 0;
	    long prevFree = rt.freeMemory();

	    for (int i = 0; i < 20000; i++) {
	        long total = rt.totalMemory();
	        long free = rt.freeMemory();
	        if (total != prevTotal || free != prevFree) {
	            long used = total - free;
	            long prevUsed = (prevTotal - prevFree);
	            System.out.println(
	                "#" + i +
	                ", Total: " + total +
	                ", Used: " + used +
	                ", ∆Used: " + (used - prevUsed) +
	                ", Free: " + free +
	                ", ∆Free: " + (prevFree - free));
	            prevTotal = total;
	            prevFree = free;
	        }
	        map.put(i, new Object());
	    }
	}
	
	
}
