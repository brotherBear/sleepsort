package fun;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		ExecutorService e = Executors.newCachedThreadPool();
		List<Thread> threads = new ArrayList<Thread>();
		StringTokenizer st = new StringTokenizer(input);
		int countTokens = st.countTokens();
		long startTime = System.currentTimeMillis();
		final StringBuilder sb = new StringBuilder();
		int count = 0;
		while (st.hasMoreTokens()) {
			final String token = st.nextToken();
			SleeperThread t = new SleeperThread(token, sb, countTokens, count++);
			threads.add(t);
		}
		for (Thread thread : threads) {
			thread.start();
		}
		int running = 0;
		do {
			running = 0;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					running++;
				}
			}
			// System.out.println("We have " + running + " running threads. ");
		} while (running > 0);
		long stopTime = System.currentTimeMillis();
		System.out.println("Sorted " + countTokens + " elements in "
				+ (stopTime - startTime) / 1000 + " seconds");
		return sb.toString();
	}

	protected static class SleeperThread extends Thread {
		private long millis;
		private String token;
		private int sequenceNumber;
		private StringBuilder response;
		private int numberOfTokens;

		public SleeperThread(String token, StringBuilder responseBuffer,
				int numberOfTokens, int sequenceNumber) {
			millis = Long.parseLong(token);
			this.token = token;
			response = responseBuffer;
			this.sequenceNumber = sequenceNumber;
			this.numberOfTokens = numberOfTokens;
		}

		@Override
		public void run() {
			try {
				long delay = calculateDelay();
				System.out.println("Sleeping for " + delay + " before adding "
						+ token);
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			response.append(token + " ");
		}

		private long calculateDelay() {
			double delay = millis * 10;
//			if (millis < numberOfTokens/2) {
//				delay = numberOfTokens + millis
//						* (2 * numberOfTokens - sequenceNumber)
//						/ numberOfTokens;
//			} else {
//				delay = numberOfTokens + millis;
//			}
			return (long) delay;
		}

	}
}
