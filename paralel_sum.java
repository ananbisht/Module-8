import java.util.ArrayList;
import java.lang.Math;  
import java.time.*;
import java.util.Random;

public class paralel_sum implements Runnable{
	int index;
	int index2;
	static int sum_single=0;
	int sum_multi=0;

	
	
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static ArrayList<Integer> list2 = new ArrayList<Integer>();
	
	public paralel_sum(int index, int index2) {
		
		this.index=index;
		this.index2=index2;
	}
	
	
	public void run() {
		for (int i=this.index; i<this.index2; i++) {
			sum_multi = sum_multi + list.get(i);
		}
		list2.add(sum_multi);
	
	}
	
	public static  void main (String[] args){
		Random rand = new Random();
		for (int i=0; i<200000000; i++){
			int a = rand.nextInt(11);
			list.add(a);
		}
// Multi-threaded 	sum
		long starttime1 = System.nanoTime();
		
		paralel_sum p1 = new paralel_sum(0,100000000);
		Thread part1 = new Thread(p1);
		
		paralel_sum p2 = new paralel_sum(100000000,200000000);
		Thread part2 = new Thread(p2);
		
		part1.start();
		part2.start();
	
		
		int sum_f = list2.get(0) + list2.get(1);
	
		long endtime1 = System.nanoTime();
		long time1 = endtime1 - starttime1;
		
		System.out.println("Multi-threaded Results:");
		System.out.println("Run time - " + time1 + " nanoseconds");
		System.out.println("Sum of array - "+ sum_f);
		
		
// Single Threaded sum
		long starttime2 = System.nanoTime();
		for (int i=0; i<list.size(); i++) {
			sum_single = sum_single + list.get(i);
		}
		long endtime2 = System.nanoTime();
		long time2 = endtime2 - starttime2;
		System.out.println();
		System.out.println("Single threaded Results:");
		System.out.println("Run time - " + time2 + " nanoseconds");
		System.out.println("Sum of array - "+ sum_single);
	}

}
