import java.nio.file.Path;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * JUnit tests for the multithreading functionality.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2020
 */
@TestMethodOrder(Alphanumeric.class)
public class ThreadOutputTest {

  /** Amount of time to wait for an individual test to finish. */
  public static final Duration TIMEOUT = Duration.ofMinutes(3);

  /**
   * Generates the arguments to use for testing inverted index output. Designed to be used inside a
   * JUnit test.
   *
   * @param subdir the output subdirectory to use
   * @param input the input path to use
   * @param threads the number of worker threads to use
   */
  public static void testBuilding(String subdir, Path input, int threads) {
    String filename = TestUtilities.outputFileName("index", input);

    Path actual = TestUtilities.ACTUAL_PATH.resolve(filename).normalize();
    Path expected = TestUtilities.EXPECTED_PATH.resolve(subdir).resolve(filename).normalize();

    String[] args = {
        "-path", input.normalize().toString(),
        "-index", actual.normalize().toString(),
        "-threads", Integer.toString(threads)};

    TestUtilities.testOutputTimeout(args, actual, expected, TIMEOUT);
  }

  /**
   * Generates the arguments to use for testing search output. Designed to be used inside a JUnit
   * test.
   *
   * @param subdir the output subdirectory to use
   * @param input the input path to use
   * @param query the query file to use for search
   * @param exact whether to perform exact or partial search
   * @param threads the number of worker threads to use
   */
  public static void testSearching(String subdir, Path input, String query, boolean exact, int threads) {
    String type = exact ? "exact" : "partial";
    String prefix = "search-" + type;
    String filename = TestUtilities.outputFileName(prefix, input);

    Path actual = TestUtilities.ACTUAL_PATH.resolve(filename).normalize();
    Path expected = TestUtilities.EXPECTED_PATH.resolve(subdir).resolve(filename).normalize();

    String[] args = {
        "-path", input.normalize().toString(),
        "-query", TestUtilities.QUERY_INPUT.resolve(query).toString(),
        "-results", actual.normalize().toString(),
        "-threads", Integer.toString(threads),
        exact ? "-exact" : ""};

    TestUtilities.testOutputTimeout(args, actual, expected, TIMEOUT);
  }

  /**
   * Junit tests for the inverted index output.
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class A_ThreadBuildIndexTest {

    /**
     * Tests the index output for hello.txt in the simple subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(1)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testSimpleHello(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt");
      testBuilding("index-simple", input, threads);
    }

    /**
     * Tests the index output for the simple subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(2)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testSimpleDirectory(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("simple");
      testBuilding("index-simple", input, threads);
    }

    /**
     * Tests the index output for the rfcs subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(3)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testRfcDirectory(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("rfcs");
      testBuilding("index-rfcs", input, threads);
    }

    /**
     * Tests the index output for 1400-0.txt in the guten subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(4)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testGuten1400(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("guten").resolve("1400-0.txt");
      testBuilding("index-guten", input, threads);
    }

    /**
     * Tests the index output for the guten subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(5)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testGutenDirectory(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("guten");
      testBuilding("index-guten", input, threads);
    }

    /**
     * Tests the index output for all of the text files.
     *
     * @param threads the number of worker threads to use
     */
    @Order(6)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testText(int threads) {
      Path input = TestUtilities.TEXT_INPUT;
      testBuilding(".", input, threads);
    }

    /**
     * Tests the word counts functionality of the inverted index on the entire input directory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(7)
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

      TestUtilities.testOutputTimeout(args, actual, expected, TIMEOUT);
    }
  }

  /**
   * Junit tests for the exact search output.
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class B_ThreadExactSearchTest {

    /** The subdirectory to find the expected output. */
    public String subdir;

    /** Whether to conduct exact search (true) or partial search (false). */
    public boolean exact;

    /**
     * Initialize the subdir and search type before each test.
     */
    @BeforeEach
    public void init() {
      subdir = "search-exact";
      exact = true;
    }

    /**
     * Tests the search result output for the simple directory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(1)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testSimpleDirectory(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("simple");
      String query = "simple.txt";
      testSearching(subdir, input, query, exact, threads);
    }

    /**
     * Tests the search result output for the rfcs subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(2)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testRfcDirectory(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("rfcs");
      String query = "letters.txt";
      testSearching(subdir, input, query, exact, threads);
    }

    /**
     * Tests the search result output for the guten subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(3)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testGutenDirectory(int threads) {
      Path input = TestUtilities.TEXT_INPUT.resolve("guten");
      String query = "letters.txt";
      testSearching(subdir, input, query, exact, threads);
    }

    /**
     * Tests the search result output for the text subdirectory.
     *
     * @param threads the number of worker threads to use
     */
    @Order(4)
    @ParameterizedTest(name = "{0} thread(s)")
    @ValueSource(ints = {1, 2, 5})
    public void testTextDirectory(int threads) {
      Path input = TestUtilities.TEXT_INPUT;
      String query = "complex.txt";
      testSearching(subdir, input, query, exact, threads);
    }
  }

  /**
   * Junit tests for the partial search output.
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class C_ThreadPartialSearchTest extends B_ThreadExactSearchTest {

    /**
     * Initialize the subdir and search type before each test.
     */
    @Override
    @BeforeEach
    public void init() {
      subdir = "search-partial";
      exact = false;
    }

  }
}
