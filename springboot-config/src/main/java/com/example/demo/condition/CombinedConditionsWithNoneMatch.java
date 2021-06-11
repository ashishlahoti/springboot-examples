package com.example.demo.condition;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.NoneNestedConditions;
import org.springframework.context.annotation.Conditional;

public class CombinedConditionsWithNoneMatch extends NoneNestedConditions {

    public CombinedConditionsWithNoneMatch() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @Conditional(CustomCondition.class)
    static class OnCustomCondition {}

    @Conditional(AnotherCustomCondition.class)
    static class OnAnotherCustomCondition {}
}
