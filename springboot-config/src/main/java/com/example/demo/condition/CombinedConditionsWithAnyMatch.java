package com.example.demo.condition;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.context.annotation.Conditional;

public class CombinedConditionsWithAnyMatch extends AnyNestedCondition {

    public CombinedConditionsWithAnyMatch() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

    @Conditional(CustomCondition.class)
    static class OnCustomCondition {}

    @Conditional(AnotherCustomCondition.class)
    static class OnAnotherCustomCondition {}
}
