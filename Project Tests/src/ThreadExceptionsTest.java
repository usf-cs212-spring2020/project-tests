import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.nio.file.Path;
import java.time.Duration;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tests that multithreading code runs without throwing exceptions.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Fall 2019
 */
@TestMethodOrder(OrderAnnotation.class)
public class ThreadExceptionsTest {
	/** Amount of time to wait for an individual test to finish. */
	private static final Duration TIMEOUT = Duration.ofMinutes(3);

	/**
	 * Tests that no exceptions are thrown with the provided arguments.
	 */
	@Order(1)
	@Test
	public void testNegativeThreads() {
		String path = Path.of("text", "simple", "hello.txt").toString();
		String query = Path.of("query", "simple.txt").toString();
		String threads = "-1";
		String[] args = { "-path", path, "-query", query, "-threads", threads };
		assertTimeoutPreemptively(TIMEOUT, () -> TestUtilities.checkExceptions(args));
	}

	/**
	 * Tests that no exceptions are thrown with the provided arguments.
	 */
	@Order(2)
	@Test
	public void testZeroThreads() {
		String path = Path.of("text", "simple", "hello.txt").toString();
		String query = Path.of("query", "simple.txt").toString();
		String threads = "0";
		String[] args = { "-path", path, "-query", query, "-threads", threads };
		assertTimeoutPreemptively(TIMEOUT, () -> TestUtilities.checkExceptions(args));
	}

	/**
	 * Tests that no exceptions are thrown with the provided arguments.
	 */
	@Order(3)
	@Test
	public void testFractionThreads() {
		String path = Path.of("text", "simple", "hello.txt").toString();
		String query = Path.of("query", "simple.txt").toString();
		String threads = "3.14";
		String[] args = { "-path", path, "-query", query, "-threads", threads };
		assertTimeoutPreemptively(TIMEOUT, () -> TestUtilities.checkExceptions(args));
	}

	/**
	 * Tests that no exceptions are thrown with the provided arguments.
	 */
	@Order(4)
	@Test
	public void testWordThreads() {
		String path = Path.of("text", "simple", "hello.txt").toString();
		String query = Path.of("query", "simple.txt").toString();
		String threads = "fox";
		String[] args = { "-path", path, "-query", query, "-threads", threads };
		assertTimeoutPreemptively(TIMEOUT, () -> TestUtilities.checkExceptions(args));
	}

	/**
	 * Tests that no exceptions are thrown with the provided arguments.
	 */
	@Order(5)
	@Test
	public void testDefaultThreads() {
		String path = Path.of("text", "simple", "hello.txt").toString();
		String query = Path.of("query", "simple.txt").toString();
		String[] args = { "-path", path, "-query", query, "-threads" };
		assertTimeoutPreemptively(TIMEOUT, () -> TestUtilities.checkExceptions(args));
	}

	/**
	 * Tests that no exceptions are thrown with the provided arguments.
	 */
	@Order(6)
	@Test
	public void testNoOutputBuild() {
		String path = Path.of("text").toString();
		String[] args = { "-path", path, "-threads", String.valueOf(5) };
		assertTimeoutPreemptively(TIMEOUT, () -> TestUtilities.checkExceptions(args));
	}

	/**
	 * Tests that no exceptions are thrown with the provided arguments.
	 */
	@Order(7)
	@Test
	public void testNoOutputSearch() {
		String path = Path.of("text").toString();
		String query = Path.of("query", "complex.txt").toString();
		String[] args = { "-path", path, "-query", query, "-threads", String.valueOf(5) };
		assertTimeoutPreemptively(TIMEOUT, () -> TestUtilities.checkExceptions(args));
	}
}
