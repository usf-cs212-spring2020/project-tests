import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * A test suite for project 4. During development, run individual tests instead
 * of this test suite.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Fall 2019
 */
@TestMethodOrder(Alphanumeric.class)
public class Project4Test {

	/**
	 * Makes sure the expected environment is setup before running any tests.
	 */
	@BeforeAll
	public static void testEnvironment() {
		assertTrue(TestUtilities.isEnvironmentSetup());
	}

	/*
	 * Make sure project 1 and 2 tests still pass.
	 */

	/**
	 * Includes all of the tests from the extended class.
	 * @see IndexOutputTest
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class A_SingleThread {

		/**
		 * Tests the word counts functionality of the inverted index on the entire
		 * input directory.
		 */
		@Order(1)
		@Test
		public void testCounts() {
			String filename = "counts.json";

			Path actual = TestUtilities.ACTUAL_PATH.resolve(filename);
			Path expected = TestUtilities.EXPECTED_PATH.resolve(filename);

			String[] args = {
					"-path", TestUtilities.TEXT_INPUT.normalize().toString(),
					"-counts", actual.normalize().toString()
			};

			TestUtilities.checkOutput(args, actual, expected);
		}

		/**
		 * Tests the index output for all of the text files.
		 */
		@Order(2)
		@Test
		public void testBuildText() {
			Path input = TestUtilities.TEXT_INPUT;
			IndexOutputTest.test(".", input);
		}

		/**
		 * Tests the exact search result output for the text subdirectory.
		 */
		@Order(3)
		@Test
		public void testSearchExact() {
			Path input = TestUtilities.TEXT_INPUT;
			String query = "complex.txt";
			SearchOutputTest.test("search-exact", input, query, true);
		}

		/**
		 * Tests the exact search result output for the text subdirectory.
		 */
		@Order(4)
		@Test
		public void testSearchPartial() {
			Path input = TestUtilities.TEXT_INPUT;
			String query = "complex.txt";
			SearchOutputTest.test("search-partial", input, query, false);
		}
	}

	/**
	 * Includes all of the tests from the extended class.
	 * @see IndexExceptionsTest
	 */
	@Nested
	public class B_IndexExceptions extends IndexExceptionsTest {

	}

	/**
	 * Includes all of the tests from the extended class.
	 * @see SearchExceptionsTest
	 */
	@Nested
	public class C_SearchExceptions extends SearchExceptionsTest {

	}

	/*
	 * Include project 3 functionality (not runtime) tests
	 */

	/**
	 * Includes all of the tests from the extended class.
	 * @see ThreadOutputTest
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class D_ThreadOutput {
		/**
		 * Tests the index output for all of the text files.
		 *
		 * @param threads the number of worker threads to use
		 */
		@Order(1)
		@ParameterizedTest(name = "{0} thread(s)")
		@ValueSource(ints = {1, 2, 5})
		public void testText(int threads) {
			Path input = TestUtilities.TEXT_INPUT;
			ThreadOutputTest.testBuilding(".", input, threads);
		}

		/**
		 * Tests the search result output for the text subdirectory.
		 *
		 * @param threads the number of worker threads to use
		 */
		@Order(2)
		@ParameterizedTest(name = "{0} thread(s)")
		@ValueSource(ints = {1, 2, 5})
		public void testSearchExact(int threads) {
			Path input = TestUtilities.TEXT_INPUT;
			String query = "complex.txt";
			ThreadOutputTest.testSearching("search-exact", input, query, true, threads);
		}

		/**
		 * Tests the search result output for the text subdirectory.
		 *
		 * @param threads the number of worker threads to use
		 */
		@Order(3)
		@ParameterizedTest(name = "{0} thread(s)")
		@ValueSource(ints = {1, 2, 5})
		public void testSearchPartial(int threads) {
			Path input = TestUtilities.TEXT_INPUT;
			String query = "complex.txt";
			ThreadOutputTest.testSearching("search-partial", input, query, false, threads);
		}
	}

	/**
	 * Includes all of the tests from the extended class.
	 * @see ThreadExceptionsTest
	 */
	@Nested
	public class E_ThreadExceptions extends ThreadExceptionsTest {

	}

	/**
	 * Include project 4 web crawler functionality.
	 */
	@Nested
	public class F_CrawlOutput extends CrawlOutputTest {

	}

}
