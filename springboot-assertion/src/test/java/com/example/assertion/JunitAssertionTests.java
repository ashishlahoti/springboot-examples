package com.example.assertion;

import com.example.assertion.model.Product;
import com.example.assertion.model.User;
import com.example.assertion.service.FooService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class JunitAssertionTests {

  @Test
  public void givenObject_whenNull_thenPass() {
    String text = null;
    Integer number = null;
    Double amount = null;
    Boolean flag = null;
    LocalDate date = null;
    List list = null;
    Map map = null;
    Character[] chars = null;

    assertNull(text);
    assertNull(number);
    assertNull(amount);
    assertNull(flag);
    assertNull(date);
    assertNull(list);
    assertNull(map);
    assertNull(chars);
  }

  @Test
  public void givenObject_whenNotNull_thenPass() {
    String text = "foo";
    Integer number = 1;
    Double amount = 100.5;
    Boolean flag = true;
    LocalDate date = LocalDate.now();
    List list = Arrays.asList();
    Map map = Collections.emptyMap();
    Character[] chars = new Character[2];

    assertNotNull(text);
    assertNotNull(number);
    assertNotNull(amount);
    assertNotNull(flag);
    assertNotNull(date);
    assertNotNull(list);
    assertNotNull(map);
    assertNotNull(chars);
  }

  @Test
  public void givenObject_whenEquals_thenPass() {
    String text = "foo";
    Integer number = 1;
    Double amount = 100.5;
    Boolean flag = true;
    LocalDate date = LocalDate.now();
    List list = Arrays.asList("a", "b");
    Map map = Map.of("key1", "value1");

    // assertEquals(expected, actual)
    assertEquals("foo", text);
    assertEquals(1, number);
    assertEquals(100.5, amount);
    assertEquals(true, flag);
    assertEquals(LocalDate.now(), date);
    assertEquals(Arrays.asList("a", "b"), list);
    assertEquals(Map.of("key1", "value1"), map);
  }

  @Test
  public void givenObject_whenNotEquals_thenPass() {
    String text = "foo";
    Integer number = 1;
    Double amount = 100.5;
    Boolean flag = true;
    LocalDate date = LocalDate.now();
    List list = Arrays.asList("a", "b");
    Map map = Map.of("key1", "value1");

    // assertNotEquals(unexpected, actual)
    assertNotEquals("bar", text);
    assertNotEquals(2, number);
    assertNotEquals(1.5, amount);
    assertNotEquals(false, flag);
    assertNotEquals(LocalDate.now().plusDays(1), date);
    assertNotEquals(Arrays.asList("c", "d"), list);
    assertNotEquals(Map.of("key2", "value2"), map);
  }

  @Test
  public void givenArray_whenEquals_thenPass() {
    Character[] chars = new Character[]{'a', 'b'};
    String[] strings = new String[]{"foo", "bar"};
    Integer[] numbers = new Integer[]{1, 2};
    Boolean[] flags = new Boolean[]{true, false};

    // assertArrayEquals(expected, actual)
    assertArrayEquals(new Character[]{'a', 'b'}, chars);
    assertArrayEquals(new String[]{"foo", "bar"}, strings);
    assertArrayEquals(new Integer[]{1, 2}, numbers);
    assertArrayEquals(new Boolean[]{true, false}, flags);
  }

  @Test
  void givenTwoIterables_whenItemsAreEqualAndSameOrder_thenPass() {
    List<String> list1 = new LinkedList<>(Arrays.asList("foo", "bar", "baz"));
    Set<String> list2 = new LinkedHashSet<>(Arrays.asList("foo", "bar", "baz"));

    assertIterableEquals(list1, list2);
  }

  @Test
  void whenAssertingEqualityListOfStrings_thenEqual() {
    List<String> expected = Arrays.asList("ls -la", "total \\d+", "drwxr-xr-x \\d+ root \\d+ Jan 1 1970");
    List<String> actual = Arrays.asList("ls -la", "total 1", "drwxr-xr-x 0 root 512 Jan 1 1970");

    assertLinesMatch(expected, actual);
  }

  @Test
  public void givenObject_whenSame_thenPass() {
    String text = "foo";
    Integer number = 1;
    Double amount = 100.5;
    Boolean flag = true;
    LocalDate date = LocalDate.now();
    List list = Arrays.asList("a", "b");
    Map map = Map.of("key1", "value1");

    String sameAsText = text;
    Integer sameAsNumber = number;
    Double sameAsAmount = amount;
    Boolean sameAsFlag = flag;
    LocalDate sameAsDate = date;
    List sameAsList = list;
    Map sameAsMap = map;

    // assertSame(expected, actual)
    assertSame(sameAsText, text);
    assertSame(sameAsNumber, number);
    assertSame(sameAsAmount, amount);
    assertSame(sameAsFlag, flag);
    assertSame(sameAsDate, date);
    assertSame(sameAsList, list);
    assertSame(sameAsMap, map);
  }

  @Test
  public void givenObject_whenNotSame_thenPass() {
    String text = "foo";
    Integer number = 1;
    Double amount = 100.5;
    Boolean flag = true;
    LocalDate date = LocalDate.now();
    List list = Arrays.asList("a", "b");
    Map map = Map.of("key1", "value1");

    // assertNotSame(expected, actual)
    assertNotSame(new String("foo"), text);
    assertNotSame(2, number);
    assertNotSame(1.5, amount);
    assertNotSame(false, flag);
    assertNotSame(LocalDate.now(), date);
    assertNotSame(Arrays.asList("a", "b"), list);
    assertNotSame(Map.of("key1", "value1"), map);
  }

  @Test
  public void givenTwoStrings_whenEqualIgnoringCase_thenPass() {
    String text1 = "foo";
    String text2 = "FOO";
    assertTrue(text1.equalsIgnoreCase(text2));
  }

  @Test
  public void givenTwoArrays_whenEqual_thenPass() {
    String[] array1 = new String[]{"a", "b"};
    String[] array2 = new String[]{"a", "b"};
    assertTrue(Arrays.equals(array1, array2));
  }

  @Test
  public void givenTwoArrays_whenNotEqual_thenPass() {
    String[] array1 = new String[]{"a", "b"};
    String[] array2 = new String[]{"c", "d"};
    assertFalse(Arrays.equals(array1, array2));
  }

  @Test
  public void givenList_whenContainsGivenValue_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertTrue(list.contains("lord"));
  }

  @Test
  public void givenMap_whenHasGivenKeyValueOrPair_thenPass() {
    Map<String, String> myMap = Map.of("myKey", "myValue");
    assertTrue(myMap.containsKey("myKey"));
    assertTrue(myMap.containsValue("myValue"));
    assertTrue(myMap.containsKey("myKey") && myMap.get("myKey").equals("myValue"));
  }

  @Test
  public void givenNumber_whenPositive_thenPass() {
    Integer number = 2;
    assertTrue(number >= 0);
  }

  @Test
  public void givenObject_whenInstanceOfClassType_thenPass() {
    User user = User.builder().build();
    //assertInstanceOf(expectedType, object);
    assertInstanceOf(User.class, user);

    assertInstanceOf(String.class, "text");
    assertInstanceOf(Integer.class, 1);
    assertInstanceOf(Boolean.class, true);
    assertInstanceOf(List.class, Arrays.asList());
    assertInstanceOf(Collection.class, Arrays.asList());
  }

  /**
   * Assert Exception
   */

  @Test
  public void doStuff_testThrownException() {
    FooService fooService = new FooService();
    Throwable exception = assertThrows(RuntimeException.class, () -> fooService.doStuff(null));
    assertEquals("Unexpected error occurred", exception.getMessage());
    assertEquals(NullPointerException.class, exception.getCause().getClass());

    assertDoesNotThrow(() -> System.out.println("Hello"));
  }

  @Test
  public void doStuff_shouldNotThrowException() {
    FooService fooService = new FooService();
    assertDoesNotThrow(() -> fooService.doStuff(true));
  }

  @Test
  void parseInvalidInt_whenThrowIllegalArgumentException_thenPass() {
    assertThrows(IllegalArgumentException.class, () -> Integer.valueOf("foo"));
  }

  @Test
  public void getInvalidIndex_whenThrowIndexOutOfBoundsException_thenPass() {
    Exception e = assertThrows(IndexOutOfBoundsException.class, () -> Arrays.asList("foo", "bar").get(2));
    assertEquals("Index 2 out of bounds for length 2", e.getMessage());
  }

  @Test
  public void accessNull_whenThrowRuntimeException_thenPass() {
    String str = null;
    assertThrows(RuntimeException.class, () -> str.equals("foo"));
    assertThrowsExactly(NullPointerException.class, () -> str.equals("foo"));
  }

  /**
   * Assert Timeout
   */

  @Test
  public void networkCall_whenFinishInGivenTime_thenPass() {
    assertTimeout(Duration.ofSeconds(5), () -> networkCall());
    // PASS
  }

  @Test
  public void networkCall_whenTakesLongerThanExpected_thenFail() {
    assertTimeoutPreemptively(Duration.ofSeconds(5), () -> delayedNetworkCall());
    // FAIL - execution timed out after 5000 ms
  }

  @Test
  void voidMethod_whenRunWithoutException_thenPass() {
    assertDoesNotThrow(() -> {
    });
  }

  @Test
  void givenEmail_whenValid_thenPass() {
    String email = "admin@gmail.com";
    assertAll("Should be a valid email",
            () -> assertNotNull(email),
            () -> assertTrue(email.contains("@")),
            () -> assertTrue(email.endsWith(".org")));
  }

  @Test
  void givenEmail_whenInalid_thenFail() {
    String email = "admin@gmail.com";
    assertNotNull(email);
    assertTrue(email.contains("@"));
    assertTrue(email.endsWith(".org"));
  }

  public void networkCall() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
  }

  public void delayedNetworkCall() throws InterruptedException {
    TimeUnit.SECONDS.sleep(7);
  }

  @ParameterizedTest(name = "{0} + {1} = {2}")
  @CsvSource({
          "0,    1,   1",
          "1,    2,   3",
          "49,  51, 100",
          "1,  100, 101"
  })
  void add(int first, int second, int expectedResult) {
    assertEquals(expectedResult, first + second,
            () -> first + " + " + second + " should equal " + expectedResult);
  }

  @Test
  void givenCondition_whenNotTrue_thenFailWithCustomErrorMessage() {
    Boolean condition = false;
    assertTrue(condition, "Condition must be true");
  }

  @Test
  public void givenMap_whenNotContainsKey_thenFailWithCustomErrorMessageSupplier() {
    Map<String, String> myMap = Map.of("myKey", "myValue");
    String KEY = "anotherKey";
    assertTrue(myMap.containsKey(KEY), () -> String.format("The map doesn't contain the key: %s", KEY));
  }

  // Multiple Properties
  @Test
  public void givenObject_testAllProperties() {
    // Object to test
    Product product = new Product(1L, "Office Desk", true, 50, new BigDecimal("599.99"), Arrays.asList("Wooden", "Electric"));

    // Verify property values collectively
    assertAll("Verify All Product Property Values",
            () -> assertEquals(1L, product.getId()),
            () -> assertEquals("Office Desk", product.getName()),
            () -> assertEquals(true, product.getOnSale()),
            () -> assertEquals(50, product.getStockQuantity()),
            () -> assertEquals("599.99", product.getPrice().toString()),
            () -> assertEquals(Arrays.asList("Wooden", "Electric"), product.getLabels()));

    // Test property conditions collectively
    assertAll("Verify Product Property Conditions",
            () -> assertNotNull(product),
            () -> assertNotNull(product.getId()),
            () -> assertTrue(product.getName().contains("Desk")),
            () -> assertTrue(product.getOnSale()),
            () -> assertTrue(product.getStockQuantity() > 0),
            () -> assertTrue(product.getPrice().compareTo(new BigDecimal(1000.0)) < 0),
            () -> assertTrue(product.getLabels().contains("Wooden")));
  }

  /**
   * Assert Optional
   */

  @Test
  public void givenOptional_testProperties() {
    Optional<String> optional = Optional.of("foo");
    assertTrue(optional.isPresent());
    assertEquals("foo", optional.get());

    optional.ifPresent(text -> assertEquals("foo", text));

    Optional<User> userOptional = Optional.of(new User("Jack", 21, true));
    assertTrue(userOptional.isPresent());
    userOptional.ifPresent(user -> {
      assertEquals("Jack", user.getFirstName());
      assertTrue(user.getAge() > 18);
      assertTrue(user.getIsPremiumUser());
    });
  }

  /**
   * Hard Assertion
   */
  @Test
  public void whenTestFail_halt(){
    assertEquals("a", "b");
    assertTrue(true);
    assertNotNull("abc");
  }
}
