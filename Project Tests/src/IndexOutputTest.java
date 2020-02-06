import java.nio.file.Path;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * JUnit tests for the inverted index functionality.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2020
 */
@TestMethodOrder(OrderAnnotation.class)
public class IndexOutputTest {

  /**
   * Generates the arguments to use for this test case. Designed to be used inside a JUnit test.
   *
   * @param subdir the output subdirectory to use
   * @param input the input path to use
   */
  public static void test(String subdir, Path input) {
    String filename = TestUtilities.outputFileName("index", input);

    Path actual = TestUtilities.ACTUAL_PATH.resolve(filename).normalize();
    Path expected = TestUtilities.EXPECTED_PATH.resolve(subdir).resolve(filename).normalize();

    String[] args =
        {"-path", input.toString(), "-index", actual.toString()};

    TestUtilities.checkOutput(args, actual, expected);
  }

  /**
   * Tests the index output for files in the simple subdirectory.
   *
   * @param filename filename of a text file in the simple subdirectory
   */
  @Order(1)
  @ParameterizedTest
  @ValueSource(strings = {"hello.txt", "animals.text", "capitals.txt", "digits.txt",
      "position.teXt", "words.tExT"})
  public void testSimpleFiles(String filename) {
    Path input = TestUtilities.TEXT_INPUT.resolve("simple").resolve(filename);
    test("index-simple", input);
  }

  /**
   * Tests the index output for the simple subdirectory.
   */
  @Order(2)
  @Test
  public void testSimpleDirectory() {
    Path input = TestUtilities.TEXT_INPUT.resolve("simple");
    test("index-simple", input);
  }

  /**
   * Tests the index output for files in the stemmer subdirectory.
   *
   * @param filename filename of a text file in the stemmer subdirectory
   */
  @Order(3)
  @ParameterizedTest
  @ValueSource(strings = {"voc.txt", "output.txt", "stop.txt"})
  public void testStemmerFiles(String filename) {
    Path input = TestUtilities.TEXT_INPUT.resolve("stemmer").resolve(filename);
    test("index-stemmer", input);
  }

  /**
   * Tests the index output for the stemmer subdirectory.
   */
  @Order(4)
  @Test
  public void testStemmerDirectory() {
    Path input = TestUtilities.TEXT_INPUT.resolve("stemmer");
    test("index-stemmer", input);
  }

  /**
   * Tests the index output for files in the rfcs subdirectory.
   *
   * @param filename filename of a text file in the rfcs subdirectory
   */
  @Order(5)
  @ParameterizedTest
  @ValueSource(strings = {"rfc475.txt", "rfc5646.txt", "rfc6805.txt", "rfc6838.txt", "rfc7231.txt"})
  public void testRfcFiles(String filename) {
    Path input = TestUtilities.TEXT_INPUT.resolve("rfcs").resolve(filename);
    test("index-rfcs", input);
  }

  /**
   * Tests the index output for the rfcs subdirectory.
   */
  @Order(6)
  @Test
  public void testRfcDirectory() {
    Path input = TestUtilities.TEXT_INPUT.resolve("rfcs");
    test("index-rfcs", input);
  }

  /**
   * Tests the index output for files in the guten subdirectory.
   *
   * @param filename filename of a text file in the guten subdirectory
   */
  @Order(7)
  @ParameterizedTest
  @ValueSource(strings = {"50468-0.txt", "pg37134.txt", "pg22577.txt", "pg1661.txt", "pg1322.txt",
      "pg1228.txt", "1400-0.txt"})
  public void testGutenFiles(String filename) {
    Path input = TestUtilities.TEXT_INPUT.resolve("guten").resolve(filename);
    test("index-guten", input);
  }

  /**
   * Tests the index output for the guten subdirectory.
   */
  @Order(8)
  @Test
  public void testGutenDirectory() {
    Path input = TestUtilities.TEXT_INPUT.resolve("guten");
    test("index-guten", input);
  }

  /**
   * Tests the index output for all of the text files.
   */
  @Order(9)
  @Test
  public void testText() {
    Path input = TestUtilities.TEXT_INPUT;
    test(".", input);
  }
}
