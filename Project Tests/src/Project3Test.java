import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * A test suite for project 3 part a (functionality). During development, run
 * individual tests instead of this test suite.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Fall 2019
 */
@TestMethodOrder(Alphanumeric.class)
public class Project3Test {

	/*
	 * To be eligible for your first code review for Project 3, you must pass the
	 * Project3aTest class on the lab computers using the `project` script.
	 *
	 * To be eligible for your second code review for Project 3, you must pass the
	 * Project3Test class instead.
	 */

	/**
	 * Makes sure the expected environment is setup before running any tests.
	 */
	@BeforeAll
	public static void testEnvironment() {
		assertTrue(TestUtilities.isEnvironmentSetup());
	}

	/*
	 * Make sure project 1 tests still pass.
	 */

	/**
	 * Includes all of the tests from the extended class.
	 * @see IndexOutputTest
	 */
	@Nested
	public class A_IndexOutput extends IndexOutputTest {

	}

	/**
	 * Includes all of the tests from the extended class.
	 * @see IndexExceptionsTest
	 */
	@Nested
	public class B_IndexExceptions extends IndexExceptionsTest {

	}

	/*
	 * Make sure project 2 tests still pass
	 */

	/**
	 * Includes all of the tests from the extended class.
	 * @see SearchOutputTest
	 */
	@Nested
	public class C_SearchOutput extends SearchOutputTest {

	}

	/**
	 * Includes all of the tests from the extended class.
	 * @see SearchExceptionsTest
	 */
	@Nested
	public class D_SearchExceptions extends SearchExceptionsTest {

	}

	/*
	 * Include project 3 functionality tests
	 */

	/**
	 * Includes all of the tests from the extended class.
	 * @see ThreadOutputTest
	 */
	@Nested
	public class E_ThreadOutput extends ThreadOutputTest {

	}

	/**
	 * Includes all of the tests from the extended class.
	 * @see ThreadExceptionsTest
	 */
	@Nested
	public class F_ThreadExceptions extends ThreadExceptionsTest {

	}

	/*
	 * Include new project 3 runtime tests
	 */

	/**
	 * Includes all of the tests from the extended class.
	 * @see ThreadRuntimeTest
	 */
	@Nested
	public class G_ThreadRuntime extends ThreadRuntimeTest {

	}
}
