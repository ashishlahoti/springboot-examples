# springboot-xml

## Overview
Generate Classes from XML Schema .xsd file and Automate the conversion from XML to json

## Steps:-
### 1. Gradle Task
Add a `jaxb` gradle task in `build.gradle` where you specify the schema *.xsd* file location and target package to generate classes

```gradle
configurations {
    jaxb
}

dependencies {
    jaxb(
        'com.sun.xml.bind:jaxb-xjc:2.3.1',
        'com.sun.xml.bind:jaxb-impl:2.3.1',
        'org.glassfish.jaxb:jaxb-runtime:2.3.1',
        'org.jvnet.jaxb2_commons:jaxb2-basics:0.12.0'
    )
}

task jaxb {
    def generatedResouces = "src/main/generated-sources"
    def jaxbTargetDir = file(generatedResouces)
    jaxbTargetDir.deleteDir()

    doLast {
        jaxbTargetDir.mkdirs()
        ant.taskdef(name: 'xjc', classname: 'com.sun.tools.xjc.XJCTask', classpath: configurations.jaxb.asPath)
        ant.jaxbTargetDir = jaxbTargetDir
        ant.xjc(destDir: '${jaxbTargetDir}', package: 'com.example.jaxb', extension: true){
            schema(dir: "src/main/resources/schema", includes: "schema.xsd")
            binding(dir: "src/main/resources/jaxb", includes: "bindings.xjb")
            arg(line: '-XenumValue')
        }
    }
}

sourceSets.main.java.srcDirs += 'src/main/generated-sources'

compileJava.dependsOn jaxb
```

When you run the above gradle task `jaxb`, it generates the java classes from schema file `src/main/resources/schema/schema.xsd`.

Java classes are generated in the package `com.example.jaxb` of directory `src/main/generated-sources`.

Also note that while conversion, JAXB use the binding file `src/main/resources/jaxb/bindings.xjb`

#### Generate java classes multiple XSD schema files
If you require generating classes from multiple schema files in different packages, you can add multiple `ant.xjc` like this:-

```gradle
ant.xjc(destDir: '${jaxbTargetDir}', package: 'com.example.jaxb.schema1', extension: true){
    schema(dir: "src/main/resources/schema", includes: "schema1.xsd")
    binding(dir: "src/main/resources/jaxb", includes: "bindings.xjb")
    arg(line: '-XenumValue')
}
ant.xjc(destDir: '${jaxbTargetDir}', package: 'com.example.jaxb.schema2', extension: true){
    schema(dir: "src/main/resources/schema", includes: "schema2.xsd")
    binding(dir: "src/main/resources/jaxb", includes: "bindings.xjb")
    arg(line: '-XenumValue')
}
```
It will generate classes from `schema1.xsd` in package `com.example.jaxb.schema1` and `schema2.xsd` in package `com.example.jaxb.schema2`

#### Limitations of generated java classes
We came across two limitations of generated java classes, which required some custom solutions:-

1. XMLGregorianCalendar
2. Enums

### 2. Fix XMLGregorianCalendar
JAXB maps `xs:time`, `xs:date`, and `xs:dateTime` to `javax.xml.datatype.XMLGregorianCalendar` by default. 

`XMLGregorianCalendar` lacks semantics of what the underlying data type really is:
1. it lacks the information on whether this is a time, date or dateTime
2. it lacks the information on whether the value is a local date/time versus one tied to a specific timezone offset.
3. it is mutable

To avoid this, we want JAXB to map:-
1. `xs:time` to `java.time.LocalTime`
2. `xs:date` to `java.time.LocalDate`
3. `xs:dateTime` to `java.time.LocalDateTime`

We need to do two things for this, first create adapter classes (check `com.example.xml.adapter` package) and then tell JAXB to use these classes through binding file.

One of the example of `TimeAdapter` class:-

```java
package com.example.xml.adapter;

public class TimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String v) {
        if (Objects.nonNull(v)) {
            try {
                return LocalTime.parse(v);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Failed to parse time: " + v, e);
            }
        }
        return null;
    }

    @Override
    public String marshal(LocalTime v) {
        if (Objects.nonNull(v)) {
            return v.format(DateTimeFormatter.ISO_TIME);
        }
        return null;
    }
}
```

JAXB binding configuration:-
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<jaxb:bindings version="2.1"
               xmlns:jaxb="http://java.sun.com/xml/ns/jaxb"
               xmlns:xjc="http://java.sun.com/xml/ns/jaxb/xjc"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://java.sun.com/xml/ns/jaxb http://java.sun.com/xml/ns/jaxb/bindingschema_2_0.xsd">

    <jaxb:globalBindings typesafeEnumMaxMembers="2000">
        <xjc:serializable uid="-1"/>
        <xjc:javaType xmlType="xs:date"
                      name="java.time.LocalDate"
                      adapter="com.example.xml.adapter.DateAdapter"/>
        <xjc:javaType xmlType="xs:time"
                      name="java.time.LocalTime"
                      adapter="com.example.xml.adapter.TimeAdapter"/>
        <xjc:javaType xmlType="xs:dateTime"
                      name="java.time.LocalDateTime"
                      adapter="com.example.xml.adapter.DateTimeAdapter"/>
    </jaxb:globalBindings>
</jaxb:bindings>
```

That's it! Now the generated classes will have time, date, and dateTime mapped to Java time package.

### 3. Fix Enums

First Look at the problem statement, below is the enumeration in xsd schema
```xml
<xs:simpleType name = "roundingDirection">
    <xs:restriction base = "xs:string">
        <xs:enumeration value = "up"/>
        <xs:enumeration value = "half up"/>
        <xs:enumeration value = "down"/>
        <xs:enumeration value = "half down"/>
        <xs:enumeration value = "nearest"/>
    </xs:restriction>
</xs:simpleType>
```

and JAXB generate following enum class file from the above schema

```java
public enum RoundingDirection {

    @XmlEnumValue("up")
    UP("up"),
    @XmlEnumValue("half up")
    HALF_UP("half up"),
    @XmlEnumValue("down")
    DOWN("down"),
    @XmlEnumValue("half down")
    HALF_DOWN("half down"),
    @XmlEnumValue("nearest")
    NEAREST("nearest");
}
```
Using the above generated enum class file, following XML:-
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<accountSummary>
    <interest rounding = "half up">27.55</interest>
</accountSummary>
```
will be converted to json:-
```json
{
  "interest" : {
    "value" : 27.55,
    "rounding" : "HALF_UP"
  }
}
```
Jackson use the `name()` method of enum by default during conversion. If we want enum value to be used in the conversion, we need custom deserializer.

We need to do two things to solve this:-
1. First we will use library `org.jvnet.jaxb2_commons:jaxb2-basics` and pass argument `arg(line: '-XenumValue')` in jaxb gradle task. All generated enum classes implements `EnumValue` class.
2. Second we write custom deserializer for `EnumValue` to use `enumValue()` instead of default `name()` while deserializing to json. Register this serializer in `ObjectMapper`. 

```java
public class EnumValueDeserializer extends JsonSerializer<EnumValue> {

    @Override
    public void serialize(EnumValue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.enumValue().toString());
    }
}
```

```java
ObjectMapper objectMapper = new ObjectMapper();

SimpleModule module = new SimpleModule();
module.addSerializer(EnumValue.class, new EnumValueDeserializer());
objectMapper.registerModule(module);

objectMapper.registerModule(new JavaTimeModule());
objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
```

