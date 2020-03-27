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
 * A test suite for project 3 part a (functionality). During development, run individual tests
 * instead of this test suite.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2020
 */
@TestMethodOrder(Alphanumeric.class)
public class Project3Test {

  /*
   * To be eligible for your first code review for Project 3, you must pass the Project3aTest class
   * on the lab computers using the `project` script.
   *
   * To be eligible for your second code review for Project 3, you must pass the Project3Test class
   * instead.
   */

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
   *
   * @see IndexOutputTest
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class A_SingleThreadOutput {

    /**
     * Tests the word counts functionality of the inverted index on the entire input directory.
     */
    @Order(1)
    @Test
    public void testCounts() {
      String filename = "counts.json";

      Path actual = TestUtilities.ACTUAL_PATH.resolve(filename);
      Path expected = TestUtilities.EXPECTED_PATH.resolve(filename);

      String[] args = {
          "-path", TestUtilities.TEXT_INPUT.normalize().toString(),
          "-counts", actual.normalize().toString()};

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
   *
   * @see IndexExceptionsTest
   */
  @Nested
  public class B_IndexExceptions extends IndexExceptionsTest {

  }

  /**
   * Includes all of the tests from the extended class.
   *
   * @see SearchExceptionsTest
   */
  @Nested
  public class C_SearchExceptions extends SearchExceptionsTest {

  }

  /*
   * Include project 3 functionality tests
   */

  /**
   * Includes only select tests from {@link ThreadOutputTest}. Not useful for
   * debugging, but reduces time this suite takes given runtime tests are slow.
   *
   * @see ThreadOutputTest
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class D_ThreadOutput {
    /**
     * Tests the word counts functionality of the inverted index on the entire input directory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(1)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testCounts(int threads) {
      String filename = "counts.json";

      Path actual = TestUtilities.ACTUAL_PATH.resolve(filename);
      Path expected = TestUtilities.EXPECTED_PATH.resolve(filename);

      String[] args = {
          "-path", TestUtilities.TEXT_INPUT.normalize().toString(),
          "-counts", actual.normalize().toString(),
          "-threads", Integer.toString(threads)};

      TestUtilities.testOutputTimeout(args, actual, expected, ThreadOutputTest.TIMEOUT);
    }

    /**
     * Tests the index output for all of the text files.
     *
     * @param threads the number of worker threads to use
     */
    @Order(2)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testIndex(int threads) {
      Path input = TestUtilities.TEXT_INPUT;
      ThreadOutputTest.testBuilding(".", input, threads);
    }

    /**
     * Tests the search result output for the text subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(3)
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
    @Order(4)
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
   *
   * @see ThreadExceptionsTest
   */
  @Nested
  public class E_ThreadExceptions extends ThreadExceptionsTest {

  }

  /*
   * Include new project 3 runtime tests
   */

  /**
   * Includes all of the tests from the extended class.
   *
   * @see ThreadRuntimeTest
   */
  @Nested
  public class F_ThreadRuntime extends ThreadRuntimeTest {

  }
}
