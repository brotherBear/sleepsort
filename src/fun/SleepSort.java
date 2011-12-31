package fun;

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
		ExecutorService e = Executors.newCachedThreadPool();
	}

	public static String sort(String input) {
		// TODO Auto-generated method stub
		return null;
	}
}
