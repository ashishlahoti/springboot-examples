package com.example.assertion;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {
    @Test
    public void testAddition() {
        int result = MyClass.add(2, 3);
        assertThat(result).isEqualTo(5);
    }
}
