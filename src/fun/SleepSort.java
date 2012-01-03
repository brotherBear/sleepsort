package fun;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class that implements the famous SleepSort algorithm
 * 
 * <pre>
 * #!/bin/bash
 * function f() {
 *     sleep "$1"
 *     echo "$1"
 * }
 * while [ -n "$1" ]
 * do
 *     f "$1" &
 *     shift
 * done
 * wait
 * 
 * example usage:
 * ./sleepsort.bash 5 3 6 3 6 3 1 4 7
 * </pre>
 * 
 * Comment:
 * 
 * <pre>
 * Oh god, it works.
 * 
 * But I don't like to wait 218382 seconds to sort '(0 218382)
 * </pre>
 * 
 * The function (in bash) works by creating a process that sleeps for the
 * specified number of seconds, then outputs the number before shifting it one
 * step to the left.
 * 
 * 
 * <p>
 * fun little project for a demo? After making it work, find a way to handle
 * negative numbers as well?
 * 
 */
public class SleepSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	public static String sort(String input) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Callable<String>> callables = new ArrayList<Callable<String>>();
		List<Future<String>> responseList = new ArrayList<Future<String>>();
		StringTokenizer st = new StringTokenizer(input);
		int countTokens = st.countTokens();
		long startTime = System.currentTimeMillis();
		final StringBuilder sb = new StringBuilder();
		// Create the threads
		while (st.hasMoreTokens()) {
			final String token = st.nextToken();
			SleeperThread t = new SleeperThread(token);
			callables.add(t);
		}
		// Start the threads
		for (Callable<String> job : callables) {
			responseList.add(executor.submit(job));
		}
		while (!responseList.isEmpty()) {
			List<Future<String>> doneList = new ArrayList<Future<String>>();
			for (Future<String> future : responseList) {
				if (future.isDone()) {
					try {
						sb.append(future.get());
						doneList.add(future);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}

			}
			responseList.removeAll(doneList);
		}
		long stopTime = System.currentTimeMillis();
		System.out.println("Sorted " + countTokens + " elements in "
				+ (stopTime - startTime) / 1000 + " seconds");
		return sb.toString();
	}

	protected static class SleeperThread implements Callable<String> {
		private long millis;
		private String token;

		public SleeperThread(String token) {
			millis = Long.parseLong(token);
			this.token = token;
		}

		@Override
		public String call() {
			try {
				long delay = calculateDelay();
				System.out.println("Sleeping for " + delay + " before adding "
						+ token);
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return token + " ";
		}

		/**
		 * Determine how long to sleep before returning to report its position.
		 * 
		 * @return
		 */
		private long calculateDelay() {
			// Need a delay to allow the entire sort set to be loaded before the
			// quickest threads finish.
			double delay = millis * 10;
			return (long) delay;
		}

	}
}
