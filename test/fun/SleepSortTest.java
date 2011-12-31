package fun;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SleepSortTest {

	@Test
	public void sortTwoNumbers() {
		String input = "2 1";
		String response = SleepSort.sort(input);
		assertEquals("1 2", response);
	}

}
