package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(-1)
    val s5 = singletonSet(0)
    val s6 = singletonSet(500)
    val s7 = singletonSet(-1001)
    val s8 = singletonSet(1001)
    val s9 = union(union(s1, s2), s7)
    val s10 = union(union(union(s3, s4), s5), s8)
    val s11 = union(union(s1, s2), s6)
    
    val p1 = (x : Int) => x < 0
    val p2 = (x : Int) => x > 0 && x < 2
    val p3 = (x : Int) => x >= 3
    val p4 = (x : Int) => x >= 300 && x <= 550
    val p5 = (x : Int) => x >= -300 && x <= 500
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1 - Different elements")
      assert(contains(s, 2), "Union 2 - Different elements")
      assert(!contains(s, 3), "Union 3 - Different elements")
      
      val t = union(s1, s1)
      assert(contains(t, 1), "Union 1 - Same elements")
      assert(!contains(t, 2), "Union 2 - Same elements")
      
      val u = union(union(s1, s2), s4)
      assert(contains(u, 1), "Union 1 - Sets different size")
      assert(contains(u, 2), "Union 2 - Sets different size")
      assert(contains(u, -1), "Union 3 - Sets different size")
      assert(!contains(u, 3), "Union 4 - Sets different size")
    }
  }
  
  test("intersect contains only similar elements") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect 1 - Different elements")
      assert(!contains(s, 2), "Intersect 2 - Different elements")
      
      val t = intersect(s1, s1)
      assert(contains(t, 1), "Intersect 1 - Same elements")
      assert(!contains(t, 2), "Intersect 2 - Same elements")
      
      val u = intersect(union(s1, s2), s2)
      assert(!contains(u, 1), "Intersect 1 - Same elements, sets different size")
      assert(contains(u, 2), "Intersect 2 - Same elements, sets different size")
    }
  }
  
  test("diff contains only elements from first set") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff 1 - Different elements")
      assert(contains(s, 2), "Diff 2 - Different elements")
      
      val t = diff(s1, s1)
      assert(!contains(t, 1), "Diff 1 - Same elements")
      assert(!contains(t, 2), "Diff 2 - Same elements")
      
      val u = diff(union(union(s1, s2), s5), s1)
      assert(contains(u, 0), "Diff 1 - Same elements, sets different size")
      assert(!contains(u, 1), "Diff 1 - Same elements, sets different size")
      assert(contains(u, 2), "Diff 2 - Same elements, sets different size")
    }
  }
  
  test("filter contains a subset of the first set") {
    new TestSets {
      val s = filter(s4, p1)
      assert(!contains(s, -2), "Filter 1 - Elements less than 0")
      assert(contains(s, -1), "Filter 2 - Elements less than 0")
      
      val t = filter(s1, p2)
      assert(contains(t, 1), "Filter 1 - Only elements equal to 1")
      assert(!contains(t, 2), "Filter 2 - Only elements equal to 1")
      
      val u = filter(s3, p3)
      assert(contains(u, 3), "Filter 1 - Elements equal to or greater than 3")
      assert(!contains(u, 4), "Filter 2 - Elements equal to or greater than 3")
    }
  }
  
  test("forall test if a given predicate is true for all elements, using bounds to limit the search space") {
    new TestSets {
      val s = forall(s9, p1)
      assert(!s, "ForAll 1 - Set with negative elements outside the bounds, Predicate: only negative numbers")
      
      val t = forall(s9, p4)
      assert(!t, "ForAll 2 - Set with negative elements outside the bounds, Predicate: only numbers between 300 and 550")
      
      val u = forall(s10, p3)
      assert(!u, "ForAll 3 - Set with positive elements outside the bounds, Predicate: only numbers equal to or greater than 3")
      
      val v = forall(s10, p5)
      assert(v, "ForAll 4 - Set with positive elements outside the bounds, Predicate: only numbers between -300 and 500")

      val w = forall(s11, p4)
      assert(!w, "ForAll 5 - Set with all elements within the bounds, Predicate: only numbers between 300 and 550")

      val x = forall(s11, p5)
      assert(x, "ForAll 6 - Set with all elements within the bounds, Predicate: only numbers between -300 and 500")

    }
  }
}
