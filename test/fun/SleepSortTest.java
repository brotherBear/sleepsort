package fun;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SleepSortTest {

	@Test
	public void sortTwoNumbers() {
		String input = "2 1";
		String response = SleepSort.sort(input);
		assertEquals("1 2 ", response);
	}

	@Test
	public void sortTwoOtherNumbers() {
		String input = "8 1";
		String response = SleepSort.sort(input);
		assertEquals("1 8 ", response);
	}

	@Test
	public void sortManyNumbers() {
		String input = "8 3 45 10 6 5 5 17";
		String response = SleepSort.sort(input);
		assertEquals("3 5 5 6 8 10 17 45 ", response);
	}

}
