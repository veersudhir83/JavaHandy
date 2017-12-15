package io.sudheer.practice.simple;

/**
 * 
 */

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class Fibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int befPrev =0;
		int prev=0;
		int current = 0;
		int value = Integer.parseInt(args[0]);
		System.out.println("Value=" + value);
		for(int i=0;i<value;i++) {
			if(i==0)
				current = befPrev + prev + 1;
			else
				current = befPrev + prev;	
			
			if(i==0)
				System.out.println(prev);
			else 
				System.out.println(current);
			befPrev = prev;
			prev = current;
		}
	}

}
