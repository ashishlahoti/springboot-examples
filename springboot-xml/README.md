# springboot-xml

## Overview
Consider a use case, where you are given an XML Schema `.xsd` file, based on which you should convert an XML to JSON format. There is no straightforward solution for this. We need to do following:-

1. First we need to generate Java class files from XSD schema `.xsd` file
2. Use Jackson for deserialization (XML to Java Object) and serialization (Java Object to JSON), which result into XML to JSON conversion.

We want to automate this as much as possible so that if there is any update in XML schema, it can be adapted with minimal change. We will create a JAXB task (gradle or maven) here, which is tied with project build phase and responsible for generating Java class files from given schema `.xsd` file.

When we converted the XML to JSON file using the generated Java class files, we find mainly two issues which were not meeting our requirement and solved them:-

1. Date and Time in XSD `xs:date` and `xs:time` are converted to timestamp format instead of date and time format
2. Enum value in XSD `<xs:enumeration value = "half down"/>` having space is converted to "HALF_UP" instead of "half up"

## Explanation
Follow the post https://codingnconcepts.com/java/convert-xsd-to-json-using-jaxb/ for detailed explanation