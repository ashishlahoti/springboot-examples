# Spring Boot Configuration
This spring boot project is to demonstrate various Configurations available in Spring Boot

### Spring Boot @Value annotation
1. Inject inline values using `@Value` annotation e.g. [InlineConfig.java](./src/main/java/com/example/demo/config/InlineConfig.java)
2. Inject values from property file using `@Value` annotation e.g. [CourseConfig.java](./src/main/java/com/example/demo/config/CourseConfig.java)   
3. Inject set of properties using `@ConfigurationProperties` annotation e.g. [PersonConfig.java](./src/main/java/com/example/demo/config/PersonConfig.java). This configuration class file defines different type of properties such as string, int, boolean, float, double, list, object, multi-line string, multi-line-indent string.
4. Use SpEL (Spring Expression Language) with `@Value` annotation e.g. [ExpressionConfig.java](./src/main/java/com/example/demo/config/ExpressionConfig.java). 

### Customize Spring Boot Banner
Example usage for custom [banner.txt](./src/main/resources/banner.txt) text to be shown in application startup logs

### Custom Actuator Endpoint
Example of custom actuator `release-notes` endpoint implementation [ReleaseNotesEndpoint.java](./src/main/java/com/example/demo/actuator/ReleaseNotesEndpoint.java)