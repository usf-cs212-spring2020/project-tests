import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Path;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * JUnit tests for the web crawler functionality.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2020
 */
@TestMethodOrder(Alphanumeric.class)
public class CrawlOutputTest {

  /*
   * RUN THESE TESTS SPARINGLY
   *
   * Run only a subset of tests at a time. Do not run the entire file until you are sure all of the
   * tests will pass.
   */

  /** How long each individual test should wait before timing out. */
  public static final Duration TIMEOUT = Duration.ofSeconds(45);

  /**
   * Various tests for the word count functionality.
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class A_CountTest {

    /**
     * Tests the word counts for the given link and limit.
     */
    @Test
    @Order(1)
    public void testSimple() {
      String link = "https://www.cs.usfca.edu/~cs212/simple/index.html";
      test(link, 10);
    }

    /**
     * Tests the word counts for the given link and limit.
     */
    @Test
    @Order(2)
    public void testBirds() {
      String link = "https://www.cs.usfca.edu/~cs212/birds/birds.html";
      test(link, 50);
    }

    /**
     * Tests the word counts for the given link and limit.
     */
    @Test
    @Order(3)
    public void testGutenberg() {
      String link = "https://www.cs.usfca.edu/~cs212/guten/";
      test(link, 8);
    }

    /**
     * Tests the word counts for the given link and limit.
     */
    @Test
    @Order(4)
    public void testRFCs() {
      String link = "https://www.cs.usfca.edu/~cs212/rfcs/index.html";
      test(link, 6);
    }

    /**
     * Tests the word counts for the given link and limit.
     */
    @Test
    @Order(5)
    public void testJava() {
      String link = "https://www.cs.usfca.edu/~cs212/docs/jdk-13.0.2_doc-all/api/allclasses-index.html";
      test(link, 50);
    }

    /**
     * Tests the word counts for the given link and limit.
     */
    @Test
    @Order(6)
    public void testRecurse() {
      String link = "https://www.cs.usfca.edu/~cs212/recurse/link01.html";
      test(link, 100);
    }

    /**
     * Tests the word counts for the given link and limit.
     */
    @Test
    @Order(7)
    public void testRedirect() {
      String link = "https://www.cs.usfca.edu/~cs212/redirect/index.html";
      test(link, 10);
    }

    /**
     * Runs an individual test of the word count.
     *
     * @param link the link to test
     * @param limit the limit to use
     */
    public void test(String link, int limit) {
      String name = getName(link);
      String filename = String.format("counts-%s.json", name);

      Path actual = TestUtilities.ACTUAL_PATH.resolve(filename).normalize();
      Path expected =
          TestUtilities.EXPECTED_PATH.resolve("counts-crawl").resolve(filename).normalize();

      String[] args = {
          "-url", link,
          "-limit", Integer.toString(limit),
          "-threads", Integer.toString(5),
          "-counts", actual.normalize().toString()
      };

      Assertions.assertTimeoutPreemptively(TIMEOUT, () -> {
        TestUtilities.checkOutput(args, actual, expected);
      });
    }

  }

  /**
   * Various tests for the inverted index functionality.
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class B_IndexTest {

    /**
     * Tests an individual web page from the simple subdirectory.
     *
     * @param link the link to test
     */
    @Order(1)
    @ParameterizedTest
    @ValueSource(strings = {
        "https://www.cs.usfca.edu/~cs212/simple/hello.html",
        "https://www.cs.usfca.edu/~cs212/simple/mixed_case.htm",
        "https://www.cs.usfca.edu/~cs212/simple/position.html",
        "https://www.cs.usfca.edu/~cs212/simple/symbols.html"})
    public void testSimple(String link) {
      test(link, 1);
    }

    /**
     * Tests an individual web page from the RFCs subdirectory.
     *
     * @param link the link to test
     */
    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {
        "https://www.cs.usfca.edu/~cs212/rfcs/rfc475.html",
        "https://www.cs.usfca.edu/~cs212/rfcs/rfc5646.html",
        "https://www.cs.usfca.edu/~cs212/rfcs/rfc6805.html",
        "https://www.cs.usfca.edu/~cs212/rfcs/rfc6838.html",
        "https://www.cs.usfca.edu/~cs212/rfcs/rfc7231.html"})
    public void testRfcs(String link) {
      test(link, 1);
    }

    /**
     * Tests an individual web page from the guten subdirectory.
     *
     * @param link the link to test
     */
    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = {
        "https://www.cs.usfca.edu/~cs212/guten/1400-h/1400-h.htm",
        "https://www.cs.usfca.edu/~cs212/guten/50468-h/50468-h.htm",
        "https://www.cs.usfca.edu/~cs212/guten/1228-h/1228-h.htm",
        "https://www.cs.usfca.edu/~cs212/guten/1322-h/1322-h.htm",
        "https://www.cs.usfca.edu/~cs212/guten/1661-h/1661-h.htm",
        "https://www.cs.usfca.edu/~cs212/guten/22577-h/22577-h.htm",
        "https://www.cs.usfca.edu/~cs212/guten/37134-h/37134-h.htm"})
    public void testGuten(String link) {
      test(link, 1);
    }

    /**
     * Tests the inverted index for the given link and limit.
     */
    @Test
    @Order(4)
    public void testSimple() {
      String link = "https://www.cs.usfca.edu/~cs212/simple/index.html";
      test(link, 10);
    }

    /**
     * Tests the inverted index for the given link and limit.
     */
    @Test
    @Order(5)
    public void testBirds() {
      String link = "https://www.cs.usfca.edu/~cs212/birds/birds.html";
      test(link, 50);
    }

    /**
     * Tests the inverted index for the given link and limit.
     */
    @Test
    @Order(6)
    public void testGutenberg() {
      String link = "https://www.cs.usfca.edu/~cs212/guten/";
      test(link, 8);
    }

    /**
     * Tests the inverted index for the given link and limit.
     */
    @Test
    @Order(7)
    public void testRFCs() {
      String link = "https://www.cs.usfca.edu/~cs212/rfcs/index.html";
      test(link, 6);
    }

    /**
     * Tests the inverted index for the given link and limit.
     */
    @Test
    @Order(8)
    public void testJavaAbout() {
      String link = "https://www.cs.usfca.edu/~cs212/docs/jdk-13.0.2_doc-all/api/java.desktop/java/awt/desktop/AboutHandler.html";
      test(link, 1);
    }

    /**
     * Tests the inverted index for the given link and limit.
     */
    @Test
    @Order(9)
    public void testJava() {
      String link = "https://www.cs.usfca.edu/~cs212/docs/jdk-13.0.2_doc-all/api/allclasses-index.html";
      test(link, 50);
    }

    /**
     * Runs an individual test of the web crawler inverted index output.
     *
     * @param link the link to test
     * @param limit the limit to use
     */
    public void test(String link, int limit) {
      String name = getName(link);
      String filename = String.format("index-%s.json", name);

      Path actual = TestUtilities.ACTUAL_PATH.resolve(filename).normalize();
      Path expected = TestUtilities.EXPECTED_PATH.resolve("index-crawl").resolve(filename).normalize();

      String[] args = {
          "-url", link,
          "-limit", Integer.toString(limit),
          "-threads", Integer.toString(5),
          "-index", actual.normalize().toString()};

      Assertions.assertTimeoutPreemptively(TIMEOUT, () -> {
        TestUtilities.checkOutput(args, actual, expected);
      });
    }

  }

  /**
   * Various tests for the search functionality.
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class C_SearchTest {

    /**
     * Tests the search functionality for the given link and limit.
     *
     * @param exact whether it is an exact search
     */
    @ParameterizedTest(name = "exact = {0}")
    @ValueSource(strings = {"true", "false"})
    @Order(1)
    public void testSimple(boolean exact) {
      String link = "https://www.cs.usfca.edu/~cs212/simple/index.html";
      test(link, 10, exact, "simple.txt");
    }

    /**
     * Tests the search functionality for the given link and limit.
     *
     * @param exact whether it is an exact search
     */
    @ParameterizedTest(name = "exact = {0}")
    @ValueSource(strings = {"true", "false"})
    @Order(2)
    public void testBirds(boolean exact) {
      String link = "https://www.cs.usfca.edu/~cs212/birds/birds.html";
      test(link, 50, exact, "letters.txt");
    }

    /**
     * Tests the search functionality for the given link and limit.
     *
     * @param exact whether it is an exact search
     */
    @ParameterizedTest(name = "exact = {0}")
    @ValueSource(strings = {"true", "false"})
    @Order(3)
    public void testGutenberg(boolean exact) {
      String link = "https://www.cs.usfca.edu/~cs212/guten/";
      test(link, 8, exact, "complex.txt");
    }

    /**
     * Tests the search functionality for the given link and limit.
     *
     * @param exact whether it is an exact search
     */
    @ParameterizedTest(name = "exact = {0}")
    @ValueSource(strings = {"true", "false"})
    @Order(4)
    public void testRFCs(boolean exact) {
      String link = "https://www.cs.usfca.edu/~cs212/rfcs/index.html";
      test(link, 6, exact, "complex.txt");
    }

    /**
     * Tests the search functionality for the given link and limit.
     *
     * @param exact whether it is an exact search
     */
    @ParameterizedTest(name = "exact = {0}")
    @ValueSource(strings = {"true", "false"})
    @Order(5)
    public void testJava(boolean exact) {
      String link = "https://www.cs.usfca.edu/~cs212/docs/jdk-13.0.2_doc-all/api/allclasses-index.html";
      test(link, 50, exact, "complex.txt");
    }

    /**
     * Runs an individual test of the web crawler search output.
     *
     * @param link the link to test
     * @param limit the limit to use
     * @param exact whether it is an exact search
     * @param query the query file to use
     */
    public void test(String link, int limit, boolean exact, String query) {
      String name = getName(link);
      String type = exact ? "exact" : "partial";
      String filename = String.format("results-%s-%s.json", type, name);

      Path actual = TestUtilities.ACTUAL_PATH.resolve(filename).normalize();
      Path expected = TestUtilities.EXPECTED_PATH.resolve("search-crawl").resolve(filename).normalize();

      String[] args = {
          "-url", link,
          "-limit", Integer.toString(limit),
          "-threads", Integer.toString(5),
          "-query", TestUtilities.QUERY_INPUT.resolve(query).toString(),
          "-results", actual.normalize().toString()};

      Assertions.assertTimeoutPreemptively(TIMEOUT, () -> {
        TestUtilities.checkOutput(args, actual, expected);
      });
    }
  }

  /**
   * Various tests for the crawl runtime.
   */
  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  public class D_RuntimeTest {
    /**
     * Tests that code runs faster with {@value ThreadRuntimeTest#THREADS} threads is faster versus just 1 worker
     * thread.
     */
    @Order(1)
    @Test
    public void testSearchMultithreaded() {
      String link = "https://www.cs.usfca.edu/~cs212/docs/jdk-13.0.2_doc-all/api/allclasses-index.html";
      String query = TestUtilities.QUERY_INPUT.resolve("complex.txt").toString();

      String[] args1 = {"-url", link, "-limit", Integer.toString(50), "-query", query,
          "-threads", Integer.toString(1)};

      String[] args2 = {"-url", link, "-limit", Integer.toString(50), "-query", query,
          "-threads", Integer.toString(ThreadRuntimeTest.THREADS)};

      System.out.println();
      System.out.printf("### Testing Crawl 1 vs %d Workers...%n", ThreadRuntimeTest.THREADS);

      // make sure code runs without exceptions before testing
      TestUtilities.testExceptionTimeout(args1, TIMEOUT);
      TestUtilities.testExceptionTimeout(args2, TIMEOUT);

      // then test the timing
      assertTimeoutPreemptively(ThreadRuntimeTest.LONG_TIMEOUT, () -> {
        double result = ThreadRuntimeTest.compare("1 Worker", args1, String.valueOf(ThreadRuntimeTest.THREADS) + " Workers", args2);
        assertTrue(result > 0, () -> String.format(ThreadRuntimeTest.SPEEDUP_FORMAT, "1 worker", -result, ThreadRuntimeTest.THREADS));
      });
    }
  }

  /**
   * Used to parse important components of the link to determine the name to use for test output
   * files.
   */
  public static final Pattern LINK_REGEX = Pattern.compile(".*/~cs212/([^/]+)/.*?(?:([^/]*)\\.html?)?");

  /**
   * Gets the name to use for test output files based on the link.
   *
   * @param link the link to test
   * @return the name to use for test output
   */
  public static String getName(String link) {
    Matcher matcher = LINK_REGEX.matcher(link);

    if (matcher.matches()) {
      String path = matcher.group(1);
      String file = matcher.group(2);

      if (file == null || file.endsWith("index") ||
          file.endsWith("birds") || file.endsWith("link01")) {
        return path;
      }

      return file;
    }

    return "";
  }
}
