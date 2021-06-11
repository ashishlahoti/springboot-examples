package com.example.demo.condition;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.context.annotation.Conditional;

public class CombinedConditionsWithAllMatch extends AllNestedConditions {

    public CombinedConditionsWithAllMatch() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @Conditional(CustomCondition.class)
    static class OnCustomCondition {}

    @Conditional(AnotherCustomCondition.class)
    static class OnAnotherCustomCondition {}
}
