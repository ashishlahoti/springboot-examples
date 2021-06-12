# Spring Boot Configuration
This spring boot project is to demonstrate various Configurations available in Spring Boot

### Spring Boot @Value Annotation
1. Inject inline values using `@Value` annotation e.g. [InlineConfig.java](./src/main/java/com/example/demo/config/InlineConfig.java)
2. Inject values from property file using `@Value` annotation e.g. [CourseConfig.java](./src/main/java/com/example/demo/config/CourseConfig.java)   
3. Inject set of properties using `@ConfigurationProperties` annotation e.g. [PersonConfig.java](./src/main/java/com/example/demo/config/PersonConfig.java). This configuration class file defines different type of properties such as string, int, boolean, float, double, list, object, multi-line string, multi-line-indent string.
4. Use SpEL (Spring Expression Language) with `@Value` annotation e.g. [ExpressionConfig.java](./src/main/java/com/example/demo/config/ExpressionConfig.java). 

### Customize Spring Boot Banner
Example usage for custom [banner.txt](./src/main/resources/banner.txt) text to be shown in application startup logs

### Custom Actuator Endpoint
Example of custom actuator `release-notes` endpoint implementation [ReleaseNotesEndpoint.java](./src/main/java/com/example/demo/actuator/ReleaseNotesEndpoint.java)

### Spring Boot @Conditional Annotation
1. Create a custom condition implementing `Condition` e.g. [CustomCondition.java](./src/main/java/com/example/demo/condition/CustomCondition.java)
2. Create combined condition with ANY match by extending `AnyNestedCondition` e.g. [CombinedConditionsWithAnyMatch.java](./src/main/java/com/example/demo/condition/CombinedConditionsWithAnyMatch.java)
3. Create combined condition with ALL match by extending `AllNestedConditions` e.g. [CombinedConditionsWithAllMatch.java](./src/main/java/com/example/demo/condition/CombinedConditionsWithAllMatch.java)
4. Create combined condition with NONE match by extending `NoneNestedConditions` e.g. [CombinedConditionsWithNoneMatch.java](./src/main/java/com/example/demo/condition/CombinedConditionsWithNoneMatch.java)
5. Create custom condition annotation using `@Conditional` e.g. [CustomConditionAnnotation.java](./src/main/java/com/example/demo/condition/CustomConditionAnnotation.java)
6. Usage of Spring Boot's predefined Conditions `@ConditionalOn...` e.g. [PredefinedConditions.java](./src/main/java/com/example/demo/condition/PredefinedConditions.java)
