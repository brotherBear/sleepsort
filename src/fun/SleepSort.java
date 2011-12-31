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
		final StringBuilder sb = new StringBuilder();
		while (st.hasMoreTokens()) {
			final String token = st.nextToken();
			SleeperThread t = new SleeperThread(token, sb);
			threads.add(t);
			t.start();
		}
		int running = 0;
		do {
			running = 0;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					running++;
				}
			}
//			System.out.println("We have " + running + " running threads. ");
		} while (running > 0);

		return sb.toString();
	}

	protected static class SleeperThread extends Thread {
		private long millis;
		private String token;
		private StringBuilder response;
		public SleeperThread(String token, StringBuilder responseBuffer) {
			millis = Long.parseLong(token);
			this.token = token;
			response = responseBuffer;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(millis * 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.append(token + " ");
		}

	}
}
