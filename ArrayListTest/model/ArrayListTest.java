package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.collection.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest<T>
{
  private ArrayList<String> list;
  private ArrayList<Integer> numberList;

  @BeforeEach void setUp()
  {
    list = new ArrayList<>();
    numberList = new ArrayList<>();
  }


  // --->>> following ZOMB-E for addIndex() method <<<--- //

  // 1. addIndexZero
  @Test void addIndexZero()
  {
    list.add(0, "mouse");
    assertEquals("mouse", list.get(0));

    list = new ArrayList<>();
    list.add(0, null);
    assertNull(list.get(0));
  }

  // 2. addIndexOne
  @Test void addIndexOne()
  {
    list.add(0,"One");
    list.add(1,"Two");
    assertEquals("Two",list.get(1));

    //null elements
    list = new ArrayList<>();
    list.add(0,null);
    list.add(1,"Two");
    assertEquals("Two",list.get(1));

    list = new ArrayList<>();
    list.add(0,"One");
    list.add(1,null);
    assertNull(list.get(1));

    list = new ArrayList<>();
    list.add(0,null);
    list.add(1,null);
    assertNull(list.get(1));

    //duplicate
    list = new ArrayList<>();
    list.add(0,"One");
    list.add(1,"One");
    assertEquals("One",list.get(1));
  }

  // 3. addIndexMany
  @Test void addIndexMany()
  {
    // adding many String elements to an ArrayList
    for (int i = 0; i < 20; i++)
    {
      int finalI = i;
      assertDoesNotThrow(() -> list.add(finalI, "test"));
      assertEquals("test", list.get(i));
    }

    list = new ArrayList<>();
    // adding many null
    for(int i = 0; i<20; i++){
      int finalI = i;
      assertDoesNotThrow(()->list.add(finalI,null));
      assertNull(list.get(i));
    }
  }

  // 4. addIndexBoundary
  @Test void addIndexBoundary()
  {
    // boundary: index at -1
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(-1, "impossible");
    });
    // boundary: index at 0 already tested at addIndexZero()

    for (int i = 0; i < 20; i++)
    {
      list.add("something");
    }

    // positive and negative
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(215, "help");
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(-215, "help");
    });

    // right boundary
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.add(21, "something");
    });

    // left boundary
    assertDoesNotThrow(() -> {
      list.add(20, "help");
    });
    assertEquals("help", list.get(20));
  }

  // 5. addIndexException
  @Test void addIndexException()
  {
    //Every possible exception for this method already tested in Boundary testing
  }


  // END addIndex() TEST

  // ********************************************************************* //


  // --->>> following ZOMB-E for addElement() method <<<--- //

  // 1. addElementZero
  @Test void addElementZero()
  {
    // if zero elements are added, the ArrayList must be empty!
    assertTrue(list.isEmpty());
    assertEquals(0, list.size()); // the size is zero too!
  }

  // 2. addElementOne
  @Test void addElementOne()
  {
    // adding one String element to an ArrayList
    list.add("hello");
    assertEquals("hello", list.get(0)); // should return the same element!
    assertEquals(1, list.size()); // the size of an ArrayList must be one!
  }

  // 3. addElementMany
  @Test void addElementMany()
  {
    // adding many String elements to an ArrayList
    list.add("monkey");
    list.add("human");
    list.add("robot");
    list.add("alien");
    list.add("ghost");
    list.add("marmalade");

    assertEquals(6, list.size());
    assertEquals("marmalade", list.get(5));
    assertEquals("ghost", list.get(4));
    assertEquals("alien", list.get(3));
    assertEquals("robot", list.get(2));
    assertEquals("human", list.get(1));
    assertEquals("monkey", list.get(0));
  }

  // 4. addElementBoundary
  @Test void addElementBoundary()
  {
    //lower boundary already tested in zero and one case, no upper boundary exists
  }

  // 5. addElementException
  @Test void addElementException()
  {
    // IllegalArgumentException – if there is a mismatch in the input, e.g. if a null element is not allowed
    // IllegalStateException – if the list is full and trying to insert at the end
    // in our case the null elements are allowed and he list expands all the time, so we never meet these Exceptions!
  }

  @Test void additionalCases()
  {
    //null elements
    list = new ArrayList<>();
    list.add(null);
    list.add("Two");
    assertEquals("Two",list.get(1));

    list = new ArrayList<>();
    list.add("One");
    list.add(null);
    assertNull(list.get(1));

    list = new ArrayList<>();
    list.add(null);
    list.add(null);
    assertNull(list.get(1));

    //duplicate
    list = new ArrayList<>();
    list.add("One");
    list.add("One");
    assertEquals("One",list.get(1));
  }
  // END addElement() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for set() method <--- //

  // 1. setZero()
  @Test void setZero()
  {
    list.add("bob");
    list.set(0,"jenny");
    assertEquals("jenny",list.get(0));

    list.set(0, null);
    // both names at indexes 0 and 1 should be set to null
    assertNull(list.get(0));
  }

  // 2. setOne()
  @Test void setOne()
  {
    list.add(0,"One");
    list.add(1,"Two");
    list.set(1,"Test");
    assertEquals("Test",list.get(1));

    //null elements
    list = new ArrayList<>();
    list.add(0,null);
    list.add(1,null);
    list.set(1,"Test");
    assertEquals("Test",list.get(1));

    list = new ArrayList<>();
    list.add(0,"One");
    list.add(1,null);
    list.set(1,"Test");
    assertEquals("Test",list.get(1));

    list = new ArrayList<>();
    list.add(0,null);
    list.add(1,null);
    list.set(1,null);
    assertNull(list.get(1));

    //duplicate
    list = new ArrayList<>();
    list.add(0,"One");
    list.add(1,"One");
    list.set(1,"One");
    assertEquals("One",list.get(1));
  }

  // 3. setMany()
  @Test void setMany()
  {
    for (int i = 0; i < 10; i++)
    {
      list.add("something"); // creating list of the same 10 elements
    }

    for (int j = 0; j < list.size(); j++) {
      list.set(j, "hello"); // setting another element to the whole list
    }

    for (int k = 0; k < list.size(); k++) {
      assertEquals("hello", list
          .get(k)); // testing if all the elements are set to "hello"
    }

    list.set(4, "giraffe");
    // the element at index 4 should be set to "giraffe"
    assertEquals("giraffe", list.get(4));

    list.set(9, null);
    // the element at index 9 should be set to null
    assertNull(list.get(9));

    list.set(2, "crocodile");
    // the element at index 2 should be set to "crocodile"
    assertEquals("crocodile", list.get(2));

    System.out.println(list.toString()); // works as expected
  }

  // 4. setBoundary()
  @Test void setBoundary()
  {
    for (int i = 0; i < 20; i++) {
      list.add("testing"); // creating list of the same 20 elements
    }

    // lower left boundary: index -1
    assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "fish"));

    // lower right boundary: 0 - already tested in setZero()

    // upper left boundary: index 19 {indexes of 20 elements are from 0-19}
    list.set(19, "chili");
    assertEquals("chili", list.get(19));

    // upper right boundary: index 20
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(20, "beach");
    });
  }

  // 5. setException()
  @Test void setException()
  {
    // negative values
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(-12, "nope");
    });

    // values to set >= arrayList size
    for (int i = 0; i < 10; i++)
    {
      list.add(i, "element"); // creating an arrayList with 10 same elements
    }

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(10, "tomato"); // last index in the arrayList is 9!
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.set(35, "potato"); // last index in the arrayList is 9!
    });
  }

  // END set() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for get() method <--- //

  // 1. getZero()
  @Test void getZero()
  {
    //already tested in addZero() and setZero() test cases;
  }

  // 2. getOne()
  @Test void getOne()
  {
    //already tested in addOne() and setOne() test cases;
  }

  // 3. getMany()
  @Test void getMany()
  {
    for (int i = 0; i < 15; i++)
    {
      list.add("morning coffee at index: "
          + i); // arrayList with the same 15 elements with different numbers
    }
    System.out.println(list.size());

    assertEquals(15, list.size()); // size must be 15
    // indexes are from 0 - 14
    assertEquals("morning coffee at index: 5", list.get(5));
    assertEquals("morning coffee at index: 9", list.get(9));
    assertEquals("morning coffee at index: 14", list.get(14));
    assertEquals("morning coffee at index: 2", list.get(2));
    assertEquals("morning coffee at index: 4", list.get(4));

  }

  // 4. getBoundary()
  @Test void getBoundary()
  {
    for (int i = 0; i < 10; i++)
    {
      list.add("cup: " + i);
    }

    // lower left boundary: index -1
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.get(-1); /// <--- instead throws new IllegalStateException
    });

    // lower right boundary: 0 - already tested in getZero()

    // upper left boundary: index 9 {indexes of 10 elements are from 0-9}
    assertEquals("cup: 9", list.get(9));

    // upper right boundary: index 10
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.get(10); /// <--- instead throws new IllegalStateException
    });
  }

  // 5. getException()
  @Test void getException()
  {
    // negative values  ---> already tested in getBoundary()
    // values to get >= arrayList size ---> already tested in getBoundary()
  }

  // END get() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for removeIndex() method <--- //

  // 1. removeIndexZero()
  @Test void removeIndexZero()
  {
    list.add("star"); // index 0
    list.add("planet");

    assertEquals(2, list.size()); // list consists of 2 elements
    assertEquals("star",
        list.remove(0)); // removes and returns element at 0 index
    // now the list should consist of 1 element - "planet", which has been moved to index 0
    assertEquals(1, list.size());
    assertEquals("planet", list.get(0));
  }

  // 2. removeIndexOne()
  @Test void removeIndexOne()
  {
    list.add("mushroom");
    list.add("avocado"); // index 1
    list.add("banana");

    assertEquals("avocado", list.remove(1));
    // now there are 2 elements left in the list and "banana" has been moved to index 1
    assertEquals(2, list.size());
    assertEquals("banana", list.get(1));
  }

  // 3. removeIndexMany()
  @Test void removeIndexMany()
  {
    for (int i = 0; i < 10; i++)
    {
      list.add("juice - " + i); // arrayList of 10 elements with indexes 0-9
    }

    assertEquals("juice - 2", list.remove(2));
    // after the previous removeIndex method the list should become shorter by one element
    // all elements after the removed index should move one place down the list
    assertEquals(9, list.size());
    assertEquals("juice - 1",
        list.get(1)); // before the removed index stays the same
    assertEquals("juice - 3", list.get(2)); // after the removed index
    assertEquals("juice - 6", list.get(5)); // after the removed index

    assertEquals("juice - 7", list.remove(6));
    // after the previous removeIndex method the list should become shorter by one element
    // all elements after the removed index should move one place down the list
    assertEquals(8, list.size());
    assertEquals("juice - 8", list.get(6)); // after the removed index = true

    assertEquals("juice - 6", list.remove(5));
    // after the previous removeIndex method the list should become shorter by one element
    // all elements after the removed index should move one place down the list
    assertEquals(7, list.size());
    assertEquals("{juice - 0, juice - 1, juice - 3, juice - 4, juice - 5, "
            + "juice - 8, juice - 9}",
        list.toString()); // 7 elements left, no removed elements and all the list has been shifted
  }

  // 4. removeIndexBoundary()
  @Test void removeIndexBoundary()
  {
    for (int i = 0; i < 30; i++)
    {
      list.add(i + " anotherList");
    }

    // lower left boundary: index -1
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.remove(-1);
    });

    // lower right boundary: 0 - already tested in removeIndexZero()

    // upper right boundary: index 30
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.remove(30); // the list doesn't go so far
    });

    // upper left boundary: index 29 {indexes of 30 elements are from 0-29}
    assertEquals("29 anotherList", list.remove(29));
    assertEquals(29, list.size());
  }

  // 5. removeIndexException()
  @Test void removeIndexException()
  {
    // negative values  ---> already tested in getIndexBoundary()
    // values to get >= arrayList size ---> already tested in getIndexBoundary()
  }

  // END removeIndex() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for removeElement() method <--- //

  // 1. removeElementZero()
  @Test void removeElementZero()
  {
    list.add("computer");
    list.add("monitor");

    assertEquals("computer", list.remove("computer"));
    // now the size is one and "monitor" should be at the index 0
    assertEquals("monitor", list.get(0));
  }

  // 2. removeElementOne()
  @Test void removeElementOne()
  {
    list.add("cupcake");
    list.add("cookie"); // index 1
    list.add("pancake");

    assertEquals("cookie", list.remove("cookie"));
    // now the list should consist of 2 elements and "pancake" should bee moved to index 1
    assertEquals(2, list.size());
    assertEquals("pancake", list.get(1));
  }

  // 3. removeElementMany()
  @Test void removeElementMany()
  {
    for (int i = 0; i < 10; i++)
    {
      list.add("milk: " + i); // arrayList of 10 elements with indexes 0-9
    }

    assertEquals("milk: 4", list.remove("milk: 4"));
    assertEquals("milk: 9", list.remove("milk: 9"));
    assertEquals("milk: 3", list.remove("milk: 3"));
    assertEquals("milk: 7", list.remove("milk: 7"));
    assertEquals("milk: 8", list.remove("milk: 8"));
    // after the removal of elements above, the list should consist of 4 elements
    // at indexes 0 - 4, elements left: "milk: 0", "milk: 1", "milk: 2", "milk: 5", "milk: 6"
    assertEquals(5, list.size());
    assertEquals("{milk: 0, milk: 1, milk: 2, milk: 5, milk: 6}",
        list.toString());
  }

  // 4. removeElementBoundary()
  @Test void removeElementBoundary()
  {
    //already tested in zero and one
  }

  // 5. removeElementException()
  @Test void removeElementException()
  {
    for (int i = 0; i < 25; i++)
    {
      list.add("horse #" + i);
    }

    // Exception: the element is not in the arrayList
    assertThrows(IllegalStateException.class, () -> {
      list.remove("horse #-1");
    });
    assertThrows(IllegalStateException.class, () -> {
      list.remove("horse #-25");
    });
  }

  // END removeElement() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for indexOfElement() method <--- //

  // 1. indexOfElementZero()
  @Test void indexOfElementZero()
  {
    // if there are 0 elements the arrayList must be empty!
    assertTrue(list.isEmpty());
  }

  // 2. indexOfElementOne()
  @Test void indexOfElementOne()
  {
    list.add("cheese"); // element 1
    list.add("tomato");
    list.add("mayo");

    // the element one should always be at index 0
    assertEquals(0, list.indexOf("cheese"));
  }

  // 3. indexOfElementMany()
  @Test void indexOfElementMany()
  {
    for (int i = 0; i < 18; i++)
    {
      list.add("place #" + i); // arrayList of 18 elements at indexes 0 - 17
    }
    // in this case the place number should be equal to the index number
    assertEquals(13, list.indexOf("place #13"));
    assertEquals(7, list.indexOf("place #7"));
    assertEquals(3, list.indexOf("place #3"));
    assertEquals(10, list.indexOf("place #10"));
    assertEquals(16, list.indexOf("place #16"));
    assertEquals(9, list.indexOf("place #9"));
  }

  // 4. indexOfElementBoundary()
  @Test void indexOfElementBoundary()
  {
    //not applicable
  }

  // 5. indexOfElementException()
  @Test void indexOfElementException()
  {
    list.add("ketchup");

    // the element is not in the arrayList - index should be -1
    assertEquals(-1, list.indexOf("batman"));
  }

  // END indexOfElement() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for containsElement() method <--- //

  // 1. containsElementZero()
  @Test void containsElementZero()
  {
    // if there are 0 elements the arrayList must be empty!
    assertTrue(list.isEmpty());
  }

  // 2. containsElementOne()
  @Test void containsElementOne()
  {
    list.add("cheese"); // element 1
    list.add("tomato");
    list.add("mayo");

    assertTrue(list.contains("cheese"));
  }

  // 3. containsElementMany()
  @Test void containsElementMany()
  {
    for (int i = 0; i < 12; i++)
    {
      list.add(i + ". task"); // arrayList of 12 elements
    }
    // in this case must return true for all elements since they are all in the list
    assertTrue(list.contains("4. task"));
    assertTrue(list.contains("9. task"));
    assertTrue(list.contains("3. task"));
    assertTrue(list.contains("10. task"));
    assertTrue(list.contains("7. task"));
  }

  // 4. containsElementBoundary()
  @Test void containsElementBoundary()
  {
    //not used
  }

  // 5. containsElementException()
  @Test void containsElementException()
  {
    list.add("batMan");
    list.add("roboCop");

    // boundary: the element is not in the arrayList - should return false
    assertFalse(list.contains("spiderMan"));
  }

  // END containsElement() TEST

  // ********************************************************************* //


  // Testing of isEmpty() method <---

  @Test void isEmpty()
  {
    // when there are no elements is the list, this method should return true
    assertTrue(list.isEmpty());

    for (int i = 0; i < 10; i++)
    {
      list.add(
          "Good Morning!"); // creates arrayList of 10 "Good Morning!" elements
    }
    // should return false
    assertFalse(list.isEmpty());


    list = new ArrayList<>();
    list.add(null);
    //should return false even if the element is null
    assertFalse(list.isEmpty());
  }

  //END isEmpty() TEST

  // ********************************************************************* //


  // Testing of isFull() method <---

  @Test void isFull()
  {
    // when there are no elements is the list, this method should return false
    assertFalse(list.isFull());

    for (int i = 0; i < 16; i++)
    {
      list.add(
          "Good Evening!"); // creates arrayList of 10 "Good Evening!" elements
    }
    assertFalse(list.isFull());

  }

  //END isEmpty() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for size() method <--- //

  // 1. sizeZero()
  @Test void sizeZero()
  {
    // if there are 0 elements the arrayList must be empty!
    assertEquals(0, list.size());
  }

  // 2. sizeOne()
  @Test void sizeOne()
  {
    list.add("cheese"); // element 1
    assertEquals(1, list.size());
  }

  // 3. sizeMany()
  @Test void sizeMany()
  {
    for (int i = 0; i < 100; i++)
    {
      list.add("test"); // arrayList of 100 elements
      assertEquals(i+1,list.size());
    }
    assertEquals(100, list.size());
  }

  // 4. sizeBoundary()
  @Test void sizeBoundary()
  {
    //testing capacity expansion
    for (int i = 0; i < 16; i++)
      list.add("test");

    assertEquals(16,list.size());
    list.add("test");
    assertEquals(17,list.size());

    for(int i = 0; i<15; i++)
      list.add("more");
    assertEquals(32,list.size());

  }

  // 5. sizeException()
  @Test void sizeException()
  {
    // not used
  }

  // END size() TEST

  // ********************************************************************* //


  // ---> ZOMB-E for toString() method <--- //

  // 1. toStringZero()
  @Test void toStringZero()
  {
    // if there are 0 elements the arrayList must be empty!
    assertTrue(list.isEmpty());
    assertEquals("{}", list.toString());
  }

  // 2. toStringOne()
  @Test void toStringOne()
  {
    list.add("randomString");
    assertEquals("{randomString}", list.toString());

    numberList.add(5);
    assertEquals("{5}", numberList.toString());
  }

  // 3. toStringMany()
  @Test void toStringMany()
  {
    for (int i = 0; i < 5; i++)
    {
      list.add("dumpling");
      numberList.add(i);
    }
    assertEquals("{dumpling, dumpling, dumpling, dumpling, dumpling}", list.toString());
    assertEquals("{0, 1, 2, 3, 4}", numberList.toString());
  }

  // 4. toStringBoundary()
  @Test void toStringBoundary()
  {
    // not used
  }

  // 5. toStringException()
  @Test void toStringException()
  {
    // not used
  }

  // END toString() TEST
}