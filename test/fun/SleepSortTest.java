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

	@Test
	public void sortManyMoreNumbers() {
		String input = "8 3 45 10 6 5 51 71 243 9 8218 42 94 21 208 39 4274 1 27 34 5 127 8 347 19 89 8 908 7 127 9 401";
		String response = SleepSort.sort(input);
		assertEquals("1 3 5 5 6 7 8 8 8 9 9 10 19 21 27 34 39 42 45 51 71 89 94 127 127 208 243 347 401 908 4274 8218 ", response);
	}
}
