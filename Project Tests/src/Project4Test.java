import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * A test suite for project 4 web crawler. During development, run individual tests
 * instead of this test suite.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2020
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
   * Includes only one of the tests from the first project.
   *
   * @see IndexOutputTest
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class A_SingleThreadOutput {

    /**
     * Tests the partial search result output for the text subdirectory.
     */
    @Order(1)
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
     * Tests the search result output for the text subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(1)
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
   * Include some project 3 runtime tests
   */

  /**
   * Includes all of the tests from the extended class, but some are overridden
   * and then disabled.
   *
   * @see ThreadRuntimeTest
   */
  @Nested
  public class F_ThreadRuntime extends ThreadRuntimeTest {

    // Disable all but two of the runtime tests

    @Disabled
    @Override
    public void testIndexConsistency() {

    }

    @Disabled
    @Override
    public void testSearchConsistency() {

    }

    @Disabled
    @Override
    public void testIndexSingleMulti() {

    }

    @Disabled
    @Override
    public void testSearchSingleMulti() {

    }

  }

  /**
   * Include the web crawler output (and runtime) tests.
   */
  public class G_CrawlOutput extends CrawlOutputTest {

  }
}
