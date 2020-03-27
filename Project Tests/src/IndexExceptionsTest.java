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
public class IndexExceptionsTest {

  /**
   * Tests no exceptions are thrown with no arguments.
   */
  @Order(1)
  @Test
  public void testNoArguments() {
    String[] args = {};
    TestUtilities.testExceptionTimeout(args);
  }

  /**
   * Tests no exceptions are thrown with invalid arguments.
   */
  @Order(2)
  @Test
  public void testBadArguments() {
    String[] args = {"hello", "world"};
    TestUtilities.testExceptionTimeout(args);
  }

  /**
   * Tests no exceptions are thrown with a missing path value.
   */
  @Order(3)
  @Test
  public void testMissingPath() {
    String[] args = {"-path"};
    TestUtilities.testExceptionTimeout(args);
  }

  /**
   * Tests no exceptions are thrown with an invalid path value.
   */
  @Order(4)
  @Test
  public void testInvalidPath() {
    // generates a random path name
    String path = Long.toHexString(Double.doubleToLongBits(Math.random()));
    String[] args = {"-path", path};
    TestUtilities.testExceptionTimeout(args);
  }

  /**
   * Tests no exceptions are thrown with no index output.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(5)
  @Test
  public void testNoOutput() throws IOException {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String[] args = {"-path", path};

    // make sure to delete old index.json if it exists
    Path output = Path.of("index.json");
    Files.deleteIfExists(output);

    TestUtilities.testExceptionTimeout(args);

    // make sure a new index.json was not created
    Assertions.assertFalse(Files.exists(output));
  }

  /**
   * Tests no exceptions are thrown with a default output value.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(6)
  @Test
  public void testDefaultOutput() throws IOException {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String[] args = {"-path", path, "-index"};

    // make sure to delete old index.json if it exists
    Path output = Path.of("index.json");
    Files.deleteIfExists(output);

    TestUtilities.testExceptionTimeout(args);

    // make sure a new index.json was created
    Assertions.assertTrue(Files.exists(output));
  }

  /**
   * Tests no exceptions are thrown with only output (no input path).
   *
   * @throws IOException if I/O error occurs
   */
  @Order(7)
  @Test
  public void testEmptyOutput() throws IOException {
    String[] args = {"-index"};

    // make sure to delete old index.json if it exists
    Path output = Path.of("index.json");
    Files.deleteIfExists(output);

    TestUtilities.testExceptionTimeout(args);

    // make sure a new index.json was created
    Assertions.assertTrue(Files.exists(output));
  }

  /**
   * Tests no exceptions are thrown with arguments in a different order.
   *
   * @throws IOException if I/O error occurs
   */
  @Order(8)
  @Test
  public void testSwitchedOrder() throws IOException {
    String path = TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String[] args = {"-index", "-path", path};

    // make sure to delete old index.json if it exists
    Path output = Path.of("index.json");
    Files.deleteIfExists(output);

    TestUtilities.testExceptionTimeout(args);

    // make sure a new index.json was created
    Assertions.assertTrue(Files.exists(output));
  }
}
