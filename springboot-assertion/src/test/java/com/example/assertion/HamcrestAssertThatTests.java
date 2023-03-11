package com.example.assertion;

import com.example.assertion.model.Product;
import com.example.assertion.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static com.example.assertion.IsPrimeNumber.isPrimeNumber;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestAssertThatTests {
  // Null Value
  @Test
  public void givenValue_whenNull_thenPass() {
    String text = null;
    Integer number = null;
    Boolean flag = null;
    Object obj = null;

    // assertThat(null, is(null)); <- throw NullPointerException

    assertThat(null, is(nullValue())); //pass
    assertThat(text, is(nullValue()));  //pass
    assertThat(number, is(nullValue()));  //pass
    assertThat(flag, is(nullValue()));  //pass
    assertThat(obj, is(nullValue()));  //pass
  }

  @Test
  public void givenValue_whenNotNull_thenPass() {
    assertThat("a", is(notNullValue()));  // pass
    assertThat(1, is(notNullValue())); // pass
    assertThat(false, is(notNullValue())); // pass
    assertThat(new Object(), is(notNullValue())); // pass
  }

  // Text Matchers
  @Test
  public void givenText_whenEmpty_thenPass() {
    String text = "";
    assertThat(text, is(emptyString()));
  }

  @Test
  public void givenText_whenEmptyOrNull_thenPass() {
    String text = null;
    assertThat(text, is(emptyOrNullString()));
  }

  @Test
  public void givenText_whenNotNull_thenPass() {
    String text = "notnull";
    assertThat(text, notNullValue());
  }

  @Test
  public void givenTwoTexts_whenEqualToIgnoringCase_thenPass() {
    String text1 = "foo";
    String text2 = "FOO";
    assertThat(text1, equalToIgnoringCase(text2));
  }

  @Test
  public void givenTwoTexts_whenEqualToIgnoringWhiteSpace_thenPass() {
    String text1 = "   my\tfoo  bar ";
    String text2 = " my  foo bar";
    //assertThat(a, equalToIgnoringWhiteSpace(b)); // deprecated
    assertThat(text1, equalToCompressingWhiteSpace(text2)); // use this!
  }

  @Test
  public void givenTwoTexts_whenFirstContainsSecond_thenPass() {
    String text1 = "lordOfTheRings";
    String text2 = "Ring";
    assertThat(text1, containsString(text2));
  }

  @Test
  public void givenTwoTexts_whenFirstContainsSecondIgnoringCase_thenPass() {
    String text1 = "lordOfTheRings";
    String text2 = "RING";
    assertThat(text1, containsStringIgnoringCase(text2));
  }

  @Test
  public void givenText_whenContainsGivenSubtexts_thenPass() {
    String str = "lordOfTheRings";
    assertThat(str, stringContainsInOrder(Arrays.asList("lord", "Ring")));
  }

  @Test
  public void givenTwoTexts_whenFirstStartsWithSecond_thenPass() {
    String text1 = "lordOfTheRings";
    String text2 = "lord";
    assertThat(text1, startsWith(text2));
  }

  @Test
  public void givenTwoTexts_whenFirstStartsWithSecondIgnoringCase_thenPass() {
    String text1 = "lordOfTheRings";
    String text2 = "LORD";
    assertThat(text1, startsWithIgnoringCase(text2));
  }

  @Test
  public void givenTwoTexts_whenFirstEndsWithSecond_thenPass() {
    String text1 = "lordOfTheRings";
    String text2 = "Rings";
    assertThat(text1, endsWith(text2));
  }

  @Test
  public void givenTwoTexts_whenFirstEndsWithSecondIgnoringCase_thenPass() {
    String text1 = "lordOfTheRings";
    String text2 = "RINGS";
    assertThat(text1, endsWithIgnoringCase(text2));
  }

  // Number Matchers
  @Test
  public void givenNumber_whenGreaterThan1_thenPass() {
    assertThat(2, greaterThan(1));
  }

  @Test
  public void givenNumber_whenGreaterThanOrEqTo1_thenPass() {
    assertThat(1, greaterThanOrEqualTo(1));
  }

  @Test
  public void givenNumber_whenLessThanTo1_thenPass() {
    assertThat(-1, lessThan(1));
  }

  @Test
  public void givenNumber_whenLessThanOrEqTo1_thenPass() {
    assertThat(1, lessThanOrEqualTo(1));
  }

  @Test
  public void givenNumber_whenWithinRange_thenPass() {
    // 0.8 is within range of 1.0 (+/-) 0.2 = 0.8 to 1.2
    assertThat(0.8, is(closeTo(1.0, 0.2)));
  }

  @Test
  public void givenBigDecimal_whenWithinRange_thenPass() {
    // 1.8 is within range of 2.0 (+/-) 0.5 = 1.5 to 2.5
    assertThat(new BigDecimal("1.8"), is(closeTo(new BigDecimal("2.0"), new BigDecimal("0.5"))));
  }

  // Object Matchers
  @Test
  public void givenTwoObjects_whenEquals_thenPass() {
    User user1 = User.builder().firstName("Adam").build();
    User user2 = User.builder().firstName("Adam").build();
    assertThat(user1, equalTo(user2));
  }

  @Test
  public void givenTwoObjects_whenSameInstance_thenPass() {
    User user = User.builder().firstName("Adam").build();
    assertThat(user, sameInstance(user));
  }

  @Test
  public void givenObject_whenInstanceOfGivenClass_thenPass() {
    User user = User.builder().firstName("Adam").build();
    assertThat(user, instanceOf(User.class));
  }

  @Test
  public void givenObject_whenToStringMethodReturnsGivenString_thenPass() {
    User user = User.builder().firstName("Adam").build();
    String str = user.toString();
    assertThat(user, hasToString(str));
  }

  @Test
  public void given2Classes_whenFirstClassChildOfSecondClass_thenCorrect() {
    assertThat(Integer.class, typeCompatibleWith(Number.class));
  }

  // Bean Matchers

  @Test
  public void givenObject_whenHasGivenProperties_thenPass() {
    User user = User.builder().build();
    assertThat(user, hasProperty("firstName"));
    assertThat(user, hasProperty("age"));
    assertThat(user, hasProperty("isPremiumUser"));
  }

  @Test
  public void givenObject_whenHasPropertyWithGivenNameAndValue_thenPass() {
    User user = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    assertThat(user, hasProperty("firstName", equalTo("Adam")));
    assertThat(user, hasProperty("age", equalTo(22)));
    assertThat(user, hasProperty("isPremiumUser", equalTo(true)));
  }

  @Test
  public void givenTwoObjects_whenSamePropertyValues_thenPass() {
    User user1 = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    User user2 = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();

    assertThat(user1, samePropertyValuesAs(user2));
  }

  @Test
  public void givenTwoObjects_whenSamePropertyValuesExcludingIgnoredProps_thenPass() {
    User user1 = User.builder().firstName("Adam").age(22).isPremiumUser(true).build();
    User user2 = User.builder().firstName("Adam").age(18).isPremiumUser(false).build();

    assertThat(user1, samePropertyValuesAs(user2, "age", "isPremiumUser"));
  }

  // Collection List Matchers
  @Test
  public void givenList_whenEmpty_thenPass() {
    List<String> list = new ArrayList<>();
    assertThat(list, empty());
  }

  @Test
  public void givenList_whenSizeMatches_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertThat(list, hasSize(4));
    assertThat(list, iterableWithSize(4));
  }

  @Test
  public void givenList_whenContainsAllValuesInSameOrder_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertThat(list, contains("lord", "of", "the", "rings"));
  }

  @Test
  public void givenList_whenContainsAllValuesInAnyOrder_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertThat(list, containsInAnyOrder("rings", "of", "the", "lord"));
  }

  @Test
  public void givenList_whenContainsValuesInRelativeOrder_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertThat(list, containsInRelativeOrder("of", "rings"));
  }

  @Test
  public void givenList_whenContainsGivenValue_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertThat(list, hasItem("lord"));
    assertThat("of", is(in(list)));
  }

  @Test
  public void givenList_whenContainsGivenValueWithCondition_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertThat(list, hasItem(equalTo("of")));
    assertThat(list, hasItem(startsWith("th")));
    assertThat(list, hasItem(endsWith("ngs")));
  }


  @Test
  public void givenList_whenContainsValuesStartsWithEndsWithAndEqualTo_thenPass() {
    List<String> list = Arrays.asList("lord", "of", "the", "rings");
    assertThat(list, hasItems(startsWith("ring"), endsWith("ord"), equalTo("of")));
  }

  @Test
  public void givenList_whenEveryItemMatchesCondition_thenPass() {
    List<String> list = Arrays.asList("bar", "baz");
    assertThat(list, everyItem(startsWith("ba")));
  }


  // Collection Array Matchers

  @Test
  public void givenArray_whenSizeMatches_thenPass() {
    String[] list = {"lord", "of", "the", "rings"};
    assertThat(list, arrayWithSize(4));
  }

  @Test
  public void givenArray_whenValueFoundInArray_thenPass() {
    String[] arrayItems = {"lord", "of", "the", "rings"};
    assertThat(arrayItems, hasItemInArray("lord"));
    assertThat("of", oneOf(arrayItems));
    assertThat("the", is(in(arrayItems)));
  }

  @Test
  public void givenArray_whenContainsAllValuesInSameOrder_thenPass() {
    String[] arrayItems = {"lord", "of", "the", "rings"};
    assertThat(arrayItems, arrayContaining("lord", "of", "the", "rings"));
  }

  @Test
  public void givenArray_whenContainsAllValuesInAnyOrder_thenPass() {
    String[] arrayItems = {"lord", "of", "the", "rings"};
    assertThat(arrayItems, arrayContainingInAnyOrder("rings", "of", "the", "lord"));
  }

  // Collection Map Matchers
  @Test
  public void givenMap_whenEmptyMap_thenPass() {
    Map<String, String> myMap = new HashMap<>();
    assertThat(myMap, is(anEmptyMap()));
  }

  @Test
  public void givenMap_whenMapHasGivenKey_thenPass() {
    Map<String, String> myMap = Map.of("myKey", "myValue");
    assertThat(myMap, hasKey("myKey"));
    assertThat(myMap, hasKey(startsWith("my")));
    assertThat(myMap, hasKey(endsWith("Key")));
  }

  @Test
  public void givenMap_whenMapHasGivenValue_thenPass() {
    Map<String, String> myMap = Map.of("myKey", "myValue");
    assertThat(myMap, hasValue("myValue"));
    assertThat(myMap, hasValue(startsWith("my")));
    assertThat(myMap, hasValue(endsWith("Value")));
  }

  @Test
  public void givenMap_whenMapHasGivenEntry_thenPass() {
    Map<String, String> myMap = Map.of("myKey", "myValue");
    assertThat(myMap, hasEntry("myKey", "myValue"));
    assertThat(myMap, hasEntry(endsWith("Key"), endsWith("Value")));
  }

  @Test
  public void givenMap_whenSizeMatched_thenPass() {
    Map<String, String> myMap = Map.of("key1", "value1", "key2", "value2");
    assertThat(myMap, is(aMapWithSize(equalTo(2))));
  }

  // Logical Matchers

  @Test
  public void givenTwoStrings_whenNotEquals_thenPass() {
    String text1 = "text1";
    String text2 = "text2";
    assertThat(text1, not(equalTo(text2)));
  }

  @Test
  public void givenString_whenAnyOfGivenConditionsMatch_thenPass() {
    String text = "lord of the rings";
    assertThat(text, anyOf(startsWith("lord"), containsString("power")));
  }

  @Test
  public void givenString_whenAllOfGivenConditionsMatch_thenPass() {
    String text = "lord of the rings";
    assertThat(text, allOf(startsWith("lord"), endsWith("rings")));
  }

  @Test
  public void givenString_whenComplexConditionMatch_thenPass() {
    String text = "lord of the rings";
    assertThat(text, anyOf(allOf(startsWith("lord"), endsWith("rings")), endsWith("power")));
  }

  @Test
  public void givenString_whenBothConditionMatch_thenPass() {
    String text = "lord of the rings";
    assertThat(text, both(startsWith("lord")).and(endsWith("rings")));
  }

  @Test
  public void givenString_whenEitherConditionMatch_thenPass() {
    String text = "lord of the rings";
    assertThat(text, either(startsWith("lord")).or(endsWith("power")));
  }

  // Custom Matcher
  @Test
  public void givenNumber_whenPrimeNumber_thenPass() {
    Integer num = 8;
    assertThat(num, isPrimeNumber());
  }

  // Multiple Properties
  @Test
  public void givenObject_testAllProperties() {
    // Object to test
    Product product = new Product(1L, "Office Desk", true, 50, new BigDecimal("599.99"), Arrays.asList("Wooden", "Electric"));

    // Verify property values collectively
    assertThat("Verify All Product Property Values", product, allOf(
            hasProperty("id", equalTo(1L)),
            hasProperty("name", equalTo("Office Desk")),
            hasProperty("onSale", is(true)),
            hasProperty("stockQuantity", equalTo(50)),
            hasProperty("price", is(new BigDecimal("599.99"))),
            hasProperty("labels", equalTo(List.of("Wooden", "Electric")))));

    // Test property conditions collectively
    assertThat("Verify Product Property Conditions", product, allOf(
            notNullValue(),
            hasProperty("id", notNullValue()),
            hasProperty("name", endsWith("Desk")),
            hasProperty("onSale", is(true)),
            hasProperty("stockQuantity", greaterThan(0)),
            hasProperty("price", lessThan(new BigDecimal(1000.0))),
            hasProperty("labels", hasItem("Wooden"))));
  }

  /**
   * Hard Assertion
   */
  @Test
  public void whenTestFail_halt(){
    assertThat("a", equalTo("b"));
    assertThat(true, is(true));
    assertThat("abc", startsWith("a"));
  }

}
