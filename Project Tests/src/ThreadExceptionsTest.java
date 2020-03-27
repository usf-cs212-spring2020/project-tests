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
 * @version Spring 2020
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
    String path =  TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String threads = "-1";

    String[] args = {"-path", path, "-query", query, "-threads", threads};
    TestUtilities.testExceptionTimeout(args, TIMEOUT);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(2)
  @Test
  public void testZeroThreads() {
    String path =  TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String threads = "0";

    String[] args = {"-path", path, "-query", query, "-threads", threads};
    TestUtilities.testExceptionTimeout(args, TIMEOUT);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(3)
  @Test
  public void testFractionThreads() {
    String path =  TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String threads = "3.14";

    String[] args = {"-path", path, "-query", query, "-threads", threads};
    TestUtilities.testExceptionTimeout(args, TIMEOUT);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(4)
  @Test
  public void testWordThreads() {
    String path =  TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();
    String threads = "fox";

    String[] args = {"-path", path, "-query", query, "-threads", threads};
    TestUtilities.testExceptionTimeout(args, TIMEOUT);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(5)
  @Test
  public void testDefaultThreads() {
    String path =  TestUtilities.TEXT_INPUT.resolve("simple").resolve("hello.txt").toString();
    String query = TestUtilities.QUERY_INPUT.resolve("simple.txt").toString();

    String[] args = {"-path", path, "-query", query, "-threads"};
    TestUtilities.testExceptionTimeout(args, TIMEOUT);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(6)
  @Test
  public void testNoOutputBuild() {
    String path = TestUtilities.TEXT_INPUT.toString();
    String[] args = {"-path", path, "-threads", String.valueOf(3)};
    TestUtilities.testExceptionTimeout(args, TIMEOUT);
  }

  /**
   * Tests that no exceptions are thrown with the provided arguments.
   */
  @Order(7)
  @Test
  public void testNoOutputSearch() {
    String path = TestUtilities.TEXT_INPUT.toString();
    String query = TestUtilities.QUERY_INPUT.resolve("complex.txt").toString();

    String[] args = {"-path", path, "-query", query, "-threads", String.valueOf(3)};
    TestUtilities.testExceptionTimeout(args, TIMEOUT);
  }
}
