package io.sudheer.practice.simple;

import java.util.*;

public class PrimeTest {

    public static void main(String[]args){
    int p = -999;
    Scanner in = new Scanner(System.in);
    System.out.printf("enter two integers (smaller num first):");
    int a= in.nextInt();
    int b=in.nextInt();
    while(a<b){
    for(int x=0;x<20;x++) {
    	p=isPrime(a,b);
    }
    System.out.println("\nthe prime numbers between %d and %d are : %d\n" + a + b + p);

    }
    }

public  static int isPrime(int a, int b){
	int x = -999;
    while (a<b){
	    int n;
	    n=a+1;
	    n++;
		for ( int i=2; i<b ;i++ ) {
			x = (n%i);
           if(i == n)
        	   break;
	    }

	 }
    return x;
}
}
