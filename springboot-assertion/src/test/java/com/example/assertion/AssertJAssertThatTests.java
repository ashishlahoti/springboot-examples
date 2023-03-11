package com.example.assertion;

import com.example.assertion.model.Product;
import com.example.assertion.model.User;
import com.example.assertion.service.FooService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class AssertJAssertThatTests {

  /**
   * Object assertions
   */

  @Test
  public void givenTwoObjects_whenEquals_thenPass() {
    User user1 = User.builder().firstName("Adam").build();
    User user2 = User.builder().firstName("Adam").build();
    assertThat(user1).isEqualTo(user2);
    //assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  public void givenObject_whenNull_thenPass() {
    User user = null;
    assertThat(user).isNull();
    //assertThat(user).isNotNull();
  }

  @Test
  public void givenTwoObjects_whenSameInstance_thenPass() {
    User user = User.builder().firstName("Adam").build();
    assertThat(user).isSameAs(user);
    //assertThat(user).isNotSameAs(user);
  }

  @Test
  public void givenTwoObjects_whenSamePropertyValuesExcludingIgnoredProps_thenPass() {
    User user1 = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    User user2 = User.builder().firstName("Adam").age(18).isPremiumUser(false).build();

    assertThat(user1).usingRecursiveComparison().ignoringFields("age", "isPremiumUser").isEqualTo(user2);
  }

  @Test
  public void givenTwoObjects_whenSamePropertyValuesExcludingNullProps_thenPass() {
    User user1 = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    User user2 = User.builder().firstName("Adam").age(null).isPremiumUser(null).build();

    assertThat(user1).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(user2);
  }

  @Test
  public void givenObject_whenHasNoNullProperties_thenPass() {
    User user = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    assertThat(user).hasNoNullFieldsOrProperties();
  }

  @Test
  public void givenObject_whenHasAllNullProperties_thenPass() {
    User user = User.builder().firstName(null).age(null).isPremiumUser(null).build();
    assertThat(user).hasAllNullFieldsOrProperties();
  }

  @Test
  public void givenObject_whenHasGivenProperties_thenPass() {
    User user = User.builder().build();
    assertThat(user).hasFieldOrProperty("firstName");
    assertThat(user).hasFieldOrProperty("age");
    assertThat(user).hasFieldOrProperty("isPremiumUser");
  }

  @Test
  public void givenObject_whenHasPropertyWithGivenNameAndValue_thenPass() {
    User user = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    assertThat(user).hasFieldOrPropertyWithValue("firstName", "Adam");
    assertThat(user).hasFieldOrPropertyWithValue("age", 22);
    assertThat(user).hasFieldOrPropertyWithValue("isPremiumUser", true);

    assertThat(user).extracting("firstName").isEqualTo("Adam");
    assertThat(user).extracting("age").isEqualTo(22);
    assertThat(user).extracting("isPremiumUser").isEqualTo(true);

    assertThat(user).extracting("firstName", "age", "isPremiumUser").containsExactly("Adam", 22, true);
  }

  @Test
  public void givenTwoObjects_whenSamePropertyValues_thenPass() {
    User user1 = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    User user2 = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();

    assertThat(user1).usingRecursiveComparison().isEqualTo(user2);
  }

  /**
   * String assertions
   */

  @Test
  public void givenString_testCondition_thenPass() {
    String nullString = null;
    String emptyString = "";

    assertThat(nullString).isNullOrEmpty();
    assertThat(emptyString).isNullOrEmpty();

    assertThat(emptyString).isEmpty();

    // null, empty or consists of one or more whitespace characters
    assertThat(nullString).isBlank();
    assertThat(emptyString).isBlank();
    assertThat(" ").isBlank();
    assertThat("  \t  ").isBlank();
    assertThat("notnull").isNotNull();

    assertThat("a").isNotBlank();
    assertThat(" b").isNotBlank();
    assertThat(" c ").isNotBlank();

    assertThat(" ").containsWhitespaces();
    assertThat("a b").containsWhitespaces();
    assertThat(" c ").containsWhitespaces();

    assertThat(" ").containsOnlyWhitespaces();
    assertThat("    ").containsOnlyWhitespaces();

    assertThat("a").doesNotContainAnyWhitespaces();
    assertThat("").doesNotContainAnyWhitespaces();
    assertThat("ab").doesNotContainAnyWhitespaces();


    assertThat("abc").hasSize(3);
    assertThat("abc").hasSizeLessThan(4);
    assertThat("abc").hasSizeLessThanOrEqualTo(4);
    assertThat("abc").hasSizeGreaterThan(2);
    assertThat("abc").hasSizeGreaterThanOrEqualTo(2);

    assertThat("abc").hasSameSizeAs("def");
    assertThat("abc").hasSameSizeAs(new char[]{'d', 'e', 'f'});
    assertThat("abc").hasSameSizeAs(Arrays.asList(1, 2, 3));

    assertThat("A Game of Thrones").hasSizeBetween(5, 25);
    assertThat("A Game of Thrones").hasSizeBetween(16, 17);

    String multiLine = "First line\n" + "Last line";
    assertThat(multiLine).hasLineCount(2);

    assertThat("Lord of the Rings").isEqualToIgnoringCase("lord of the rings");

    assertThat("10").containsOnlyDigits();

    assertThat("Foo").containsOnlyOnce("F");

    assertThat("Lord of the Rings").contains("Ring");
    assertThat("Lord of the Rings").contains("Lor", "Ring");

    assertThat("foo").isEqualTo("foo");

    // Number assertions
    assertThat(BigDecimal.ZERO).isZero();
    assertThat(BigDecimal.ONE).isNotZero();
  }


  @Test
  public void givenList_whenMatchesConditions_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");

    assertThat(list).isNotNull().isNotEmpty().hasSize(4).startsWith("lord").contains("of").contains("the", atIndex(2)).endsWith("rings").containsSequence("of", "the");

    assertThat("lord").isIn(list);
  }

  @Test
  public void givenMap_whenMatchesConditions_thenPass() {
    assertThat(new HashMap()).isNullOrEmpty();
    assertThat(new HashMap()).isEmpty();

    Map<String, String> myMap = new HashMap<>();
    myMap.put("myKey1", "myValue1");
    myMap.put("myKey2", "myValue2");

    assertThat(myMap).isNotNull().isNotEmpty().hasSize(2).containsKey("myKey1").containsValue("myValue2").containsEntry("myKey1", "myValue1").contains(entry("myKey2", "myValue2"));
  }

  /**
   * LocalDate assertions
   */

  @Test
  public void givenLocalDate_whenHasGivenYearMonthAndDay_thenPass() {
    LocalDate date = LocalDate.parse("2020-01-15"); //ISO YYYY-MM-DD

    assertThat(LocalDate.now()).isNotNull().isToday();
    assertThat(date).isEqualTo("2020-01-15");

    assertThat(date).hasYear(2020);
    assertThat(date).hasMonth(Month.JANUARY);
    assertThat(date).hasMonthValue(1);
    assertThat(date).hasDayOfMonth(15);
  }

  @Test
  public void givenLocalDate_whenBeforeOrAfterGivenDate_thenPass() {
    LocalDate date = LocalDate.parse("2020-01-15"); //ISO YYYY-MM-DD
    LocalDate nextDate = LocalDate.parse("2020-01-16"); //ISO YYYY-MM-DD

    assertThat(date).isNotEqualTo(nextDate);

    assertThat(date).isBefore(nextDate);
    assertThat(date).isBeforeOrEqualTo(nextDate);
    assertThat(nextDate).isAfter(date);
    assertThat(nextDate).isAfterOrEqualTo(date);
  }

  @Test
  public void givenLocalDate_whenInGivenDateRangeInclusive_thenPass() {
    LocalDate localDate = LocalDate.now();
    assertThat(localDate).isBetween(localDate.minusDays(1), localDate.plusDays(1)).isBetween(localDate, localDate.plusDays(1)).isBetween(localDate.minusDays(1), localDate).isBetween(localDate, localDate);

    LocalDate firstOfJanuary2000 = LocalDate.parse("2000-01-01");
    assertThat(firstOfJanuary2000).isBetween("1999-01-01", "2001-01-01").isBetween("2000-01-01", "2001-01-01").isBetween("1999-01-01", "2000-01-01").isBetween("2000-01-01", "2000-01-01");
  }

  @Test
  public void givenLocalDate_whenInGivenDateRangeExclusive_thenPass() {
    LocalDate localDate = LocalDate.now();
    assertThat(localDate).isStrictlyBetween(localDate.minusDays(1), localDate.plusDays(1));

    LocalDate firstOfJanuary2000 = LocalDate.parse("2000-01-01");
    assertThat(firstOfJanuary2000).isStrictlyBetween("1999-01-01", "2001-01-01");
  }

  @Test
  public void givenLocalTime_whenMatchesConditions_thenPass() {
    LocalTime localTime = LocalTime.parse("10:00:00"); // ISO HH::MM::SS

    assertThat(localTime).isNotNull().isEqualTo("10:00:00");

    assertThat(localTime).isBefore("14:00:00");
    assertThat(localTime).isAfter("09:00:00");
  }

  @Test
  public void givenLocalDateTime_whenMatchesConditions_thenPass() {
    LocalDateTime localDateTime = LocalDateTime.parse("2000-01-01T23:59:59");

    assertThat(localDateTime).isNotNull().isEqualTo("2000-01-01T23:59:59");

    assertThat(localDateTime).isBefore("2000-01-02T00:00:00");
    assertThat(localDateTime).isAfter("1999-01-01T00:00:00");
  }

  /**
   * URI assertions
   */

  @Test
  public void givenURI_whenMatchesConditions_thenPass() throws URISyntaxException {
    assertThat(new URI("http://localhost:8080")).hasScheme("http").hasHost("localhost").hasPort(8080).hasPath("").hasNoQuery().hasNoParameters();

    assertThat(new URI("https://reqres.in/api/users?name=adam&page=1")).hasScheme("https").hasHost("reqres.in").hasNoPort().hasPath("/api/users").hasQuery("name=adam&page=1").hasParameter("name").hasParameter("page", "1");

    assertThat(new URI("mailto:java-net@java.sun.com")).hasScheme("mailto").hasNoHost().hasNoPort().hasNoPath();

    assertThat(new URI("file:///home/user/Documents/hello-world.txt")).hasScheme("file").hasNoPort().hasNoHost().hasPath("/home/user/Documents/hello-world.txt");
  }

  @Test
  public void givenFile_whenMatchesConditions_thenPass() throws IOException {
    File tmpFile = File.createTempFile("tmp", "txt");
    File tmpDir = Files.createTempDirectory("tmpDir").toFile();

    assertThat(tmpFile).exists().isFile().isReadable().isWritable().hasSize(0);

    assertThat(tmpDir).exists().isDirectory();
  }

  // Multiple Properties
  @Test
  public void givenObject_testAllProperties_extracting_containsExactly() {
    // Object to test
    Product product = new Product(1L, "Office Desk", true, 50, new BigDecimal("599.99"), Arrays.asList("Wooden", "Electric"));

    // Verify property values collectively
    assertThat(product).describedAs("Verify All Product Property Values").extracting("id", "name", "onSale", "stockQuantity", "price", "labels").containsExactly(1L, "Office Desk", true, 50, new BigDecimal("599.99"), Arrays.asList("Wooden", "Electric"));

    // Test property conditions collectively
    assertThat(product).describedAs("Verify Product Property Conditions").extracting(Product::getId, Product::getName, Product::getOnSale, p -> p.getStockQuantity() > 0, p -> p.getPrice().compareTo(new BigDecimal(1000.0)) < 0, p -> p.getLabels().contains("Wooden")).containsExactly(1L, "Office Desk", true, true, true, true);

  }

  @Test
  public void givenObject_testAllProperties_returns_from() {
    // Object to test
    Product product = new Product(1L, "Office Desk", true, 50, new BigDecimal("599.99"), Arrays.asList("Wooden", "Electric"));

    // Verify property values collectively
    assertThat(product).describedAs("Verify All Product Property Values").returns(1L, from(Product::getId)).returns("Office Desk", from(Product::getName)).returns(true, from(Product::getOnSale)).returns(50, from(Product::getStockQuantity)).returns(new BigDecimal("599.99"), from(Product::getPrice)).returns(Arrays.asList("Wooden", "Electric"), from(Product::getLabels));

    // Test property conditions collectively
    assertThat(product).describedAs("Verify Product Property Conditions").isNotNull().hasNoNullFieldsOrProperties().returns(true, from(p -> p.getName().contains("Desk"))).returns(true, from(Product::getOnSale)).returns(true, from(p -> p.getStockQuantity() > 0)).returns(true, from(p -> p.getPrice().compareTo(new BigDecimal(1000.0)) < 0)).returns(true, from(p -> p.getLabels().contains("Wooden")));
  }

  /**
   * Assert Exception
   */

  @Test
  public void doStuff_testThrownException() {
    FooService fooService = new FooService();
    assertThatThrownBy(() -> fooService.doStuff(null)).isInstanceOf(RuntimeException.class).hasMessage("Unexpected error occurred").hasCauseInstanceOf(NullPointerException.class);

    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> fooService.doStuff(null)).withMessage("Unexpected error occurred").withCauseInstanceOf(NullPointerException.class);

    assertThatRuntimeException().isThrownBy(() -> fooService.doStuff(null)).withMessage("Unexpected error occurred").withCauseInstanceOf(NullPointerException.class);
  }

  @Test
  public void doStuff_shouldNotThrowException() {
    FooService fooService = new FooService();
    assertThatNoException().isThrownBy(() -> fooService.doStuff(true));
  }

  @Test
  public void testThrownException() {
    assertThatIndexOutOfBoundsException().isThrownBy(() -> Arrays.asList("foo", "bar").get(2)).withMessage("Index 2 out of bounds for length 2");

    assertThatIllegalArgumentException().isThrownBy(() -> Integer.valueOf("foo")).isInstanceOf(NumberFormatException.class);

    assertThatNullPointerException().isThrownBy(() -> {
      String text = null;
      text.equals("foo");
    });
  }

  /**
   * Assert Optional
   */

  @Test
  public void givenOptional_testProperties() {
    Optional<String> optional = Optional.of("foo");
    assertThat(optional).isPresent().isNotEmpty().containsInstanceOf(String.class).hasValue("foo").contains("foo");


    Optional<Object> emptyOptional = Optional.empty();
    assertThat(emptyOptional).isNotPresent().isEmpty();

    Optional<User> userOptional = Optional.of(new User("Jack", 21, true));
    assertThat(userOptional).isPresent().isNotEmpty().containsInstanceOf(User.class).hasValueSatisfying(user -> {
      assertThat(user.getFirstName()).isEqualTo("Jack");
      assertThat(user.getAge()).isGreaterThan(18);
      assertThat(user.getIsPremiumUser()).isTrue();
    });
  }

  /**
   * Hard and Soft Assertion
   */
  @Test
  public void whenTestFail_hardAssert_stopExecution() {
    String text = "abc";

    assertThat(text).hasSize(3);   // pass
    assertThat(text).contains("z"); // fail - stop execution
    assertThat(text).startsWith("a");  // not executed
    assertThat(text).isEqualTo("def"); // not executed
  }

  @Test
  public void whenTestFail_softAssert_continueExecution() {
    String text = "abc";
    SoftAssertions softly = new SoftAssertions();

    softly.assertThat(text).hasSize(3);  // pass
    softly.assertThat(text).contains("z");  // fail - continue execution
    softly.assertThat(text).startsWith("a");  // pass
    softly.assertThat(text).isEqualTo("def"); // fail

    softly.assertAll();
  }

  @Test
  public void whenTestFail_softAssert_continueExecution_lambda() {
    String text = "abc";
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(text).hasSize(3);  // pass
      softly.assertThat(text).contains("z");  // fail - continue execution
      softly.assertThat(text).startsWith("a");  // pass
      softly.assertThat(text).isEqualTo("def"); // fail
    });
  }

  /**
   * Custom Message
   */
  @Test
  public void givenObject_testAgeWithCustomMessage() {
    User user = new User("Joe", 22, true);
    assertThat(user.getAge()).as("check %s's age", user.getFirstName()).isEqualTo(18);
  }

  /**
   * Number assertions
   */

  @Test
  public void givenNumber_testCondition() {
    // Assert that two Numbers are equal
    assertThat(1).isEqualTo(1);

    // Assert that a Number is 0
    assertThat(0).isZero();
    assertThat(0.0).isZero();
    assertThat(BigDecimal.ZERO).isZero();

    // Assert that a Number is not 0
    assertThat(42).isNotZero();
    assertThat(3.14).isNotZero();
    assertThat(BigDecimal.ONE).isNotZero();

    // Assert that a Number is positive
    assertThat(42).isPositive();
    assertThat(3.14).isPositive();

    // Assert that a Number is negative
    assertThat(-42).isNegative();
    assertThat(-3.12).isNegative();

    // Assert that a Number is even
    assertThat(12).isEven();
    assertThat(-46).isEven();

    // Assert that a Number is odd
    assertThat(3).isOdd();
    assertThat(-17).isOdd();

    // Assert that a number is less than (or equal to) given number
    assertThat(1).isLessThan(2);
    assertThat(-2).isLessThan(-1);
    assertThat(1).isLessThanOrEqualTo(1);

    // Assert that a number is greater than (or equal to) given number
    assertThat(1).isGreaterThan(0);
    assertThat(-1).isGreaterThan(-2);
    assertThat(1).isGreaterThanOrEqualTo(1);

    // Assert that a number is within given range (inclusive)
    assertThat(1).isBetween(1, 3);

    // Assert that a number is within given range (exclusive)
    assertThat(2).isStrictlyBetween(1, 3);

    // Assert that a double is equal within given precision upto 2 decimal places = 0.01
    assertThat(3.141592653589793238).isEqualTo(3.14, withPrecision(0.01));
  }
}
