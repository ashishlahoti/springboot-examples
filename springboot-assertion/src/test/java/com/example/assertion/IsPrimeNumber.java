package com.example.assertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.stream.IntStream;

public class IsPrimeNumber extends TypeSafeMatcher<Integer> {

    @Override
    protected boolean matchesSafely(Integer number) {
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(n -> (number % n == 0));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a prime number");
    }

    public static Matcher isPrimeNumber() {
        return new IsPrimeNumber();
    }
}
