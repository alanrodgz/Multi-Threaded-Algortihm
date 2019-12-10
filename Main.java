import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//import java.awt.List;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		
		int[] a = new int[10];
		int[] b = new int[10];
		
		if (a.length == b.length) {
			
			for(int i = 0; i < a.length; i++) {
				a[i] = (int) (Math.random() * 100);
				
				b[i] = (int) (Math.random() * 100);
				
				System.out.println("a: " + a[i] + "\tb: " + b[i]);
			}
		}
		
		
		Callable<Integer> task1 = new Numbers(a);
		Callable<Integer> task2 = new Numbers(b);
		
		
		List<Callable<Integer>> tasks = new ArrayList<>();
		
		tasks.add(task1);
		tasks.add(task2);
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		
		//List<Future<Integer>> resultFutures = service.invokeAll(tasks);
		
		int result = service.invokeAny(tasks);
		
		
		/*
		int total = 0;
		for(Future<Integer> resultFuture : resultFutures) {
			
			total += resultFuture.get();
			
		}

		System.out.println("The number of integers that are greater than 50: " + total);
		*/
		
		System.out.println("The number of integers that are greater than 50: " + result) ;
		
		service.shutdown();
		System.exit(0);
		
	}

}



class Numbers implements Callable<Integer> {
	
	private int[] array;
	
	public Numbers(int[] a) {
		this.array = a;
	}

	@Override
	public Integer call() throws Exception {
		
		int count = 0;
		
		for(int i : this.array) {
			
			try {
				Thread.sleep(500);
				System.out.println(i);
				if(i >= 50) {
					count++;
				}
			} catch(InterruptedException e) {
				System.out.println(e.toString()); 
			}
			
		}
		
		
		return count;
	}
	
	
}
