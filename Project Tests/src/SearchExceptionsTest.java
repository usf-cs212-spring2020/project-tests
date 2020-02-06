import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tests that code runs without throwing exceptions.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2020
 */
@TestMethodOrder(OrderAnnotation.class)
public class SearchExceptionsTest {

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(1)
  @Test
  public void testMissingQueryPath() {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String[] args = {"-path", path, "-query"};
    TestUtilities.testNoExceptions(args);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(2)
  @Test
  public void testInvalidQueryPath() {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = Long.toHexString(Double.doubleToLongBits(Math.random()));
    String[] args = {"-path", path, "-query", query};
    TestUtilities.testNoExceptions(args);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(3)
  @Test
  public void testInvalidExactPath() {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = Long.toHexString(Double.doubleToLongBits(Math.random()));
    String[] args = {"-path", path, "-query", query, "-exact"};
    TestUtilities.testNoExceptions(args);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(4)
  @Test
  public void testNoOutput() throws IOException {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String[] args = {"-path", path, "-query", query};

    // make sure to delete old index.json and results.json if it exists
    Path index = Path.of("index.json");
    Path results = Path.of("results.json");
    Files.deleteIfExists(index);
    Files.deleteIfExists(results);

    TestUtilities.testNoExceptions(args);

    // make sure a new index.json and results.json were not created
    Assertions.assertFalse(Files.exists(index) || Files.exists(results));
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(5)
  @Test
  public void testDefaultOutput() throws IOException {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String[] args = {"-path", path, "-query", query, "-results"};

    // make sure to delete old index.json and results.json if it exists
    Path index = Path.of("index.json");
    Path results = Path.of("results.json");
    Files.deleteIfExists(index);
    Files.deleteIfExists(results);

    TestUtilities.testNoExceptions(args);

    // make sure a new results.json was not created (but index.json was not)
    Assertions.assertTrue(Files.exists(results) && !Files.exists(index));
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(6)
  @Test
  public void testEmptyIndex() throws IOException {
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String[] args = {"-query", query, "-results"};

    // make sure to delete old index.json and results.json if it exists
    Path index = Path.of("index.json");
    Path results = Path.of("results.json");
    Files.deleteIfExists(index);
    Files.deleteIfExists(results);

    TestUtilities.testNoExceptions(args);

    // make sure a new results.json was not created (but index.json was not)
    Assertions.assertTrue(Files.exists(results) && !Files.exists(index));
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(7)
  @Test
  public void testEmptyQuery() throws IOException {
    String[] args = {"-results"};

    // make sure to delete old index.json and results.json if it exists
    Path index = Path.of("index.json");
    Path results = Path.of("results.json");
    Files.deleteIfExists(index);
    Files.deleteIfExists(results);

    TestUtilities.testNoExceptions(args);

    // make sure a new results.json was not created (but index.json was not)
    Assertions.assertTrue(Files.exists(results) && !Files.exists(index));
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(8)
  @Test
  public void testSwitchedOrder() throws IOException {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String[] args = {"-query", query, "-results", "-path", path, "-exact"};

    // make sure to delete old index.json and results.json if it exists
    Path index = Path.of("index.json");
    Path results = Path.of("results.json");
    Files.deleteIfExists(index);
    Files.deleteIfExists(results);

    TestUtilities.testNoExceptions(args);

    // make sure a new results.json was not created (but index.json was not)
    Assertions.assertTrue(Files.exists(results) && !Files.exists(index));
  }
}
