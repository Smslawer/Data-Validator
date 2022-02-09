### Hexlet tests and linter status:
[![Actions Status](https://github.com/Smslawer/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/Smslawer/java-project-lvl3/actions)
[![Actions validate](https://github.com/Smslawer/java-project-lvl3/workflows/actions-validate/badge.svg)](https://github.com/Smslawer/java-project-lvl3/actions)
<a href="https://codeclimate.com/github/Smslawer/java-project-lvl3/maintainability"><img src="https://api.codeclimate.com/v1/badges/2b501f2713896c14dc57/maintainability" /></a>
<a href="https://codeclimate.com/github/Smslawer/java-project-lvl3/test_coverage"><img src="https://api.codeclimate.com/v1/badges/2b501f2713896c14dc57/test_coverage" /></a>

# Validate project
Data validator is a library that can be used to check the correctness of any data.<br>
There are many such libraries in every language, since almost all programs work with external data that needs to be checked for correctness. <br>
First of all, we are talking about the data of forms filled out by users.

With this library you can validate such data-types as:
- Strings
- Numeric
- Maps
## Start
The entry-point for this library is `Validator v = new Validator();`

### String validation
 - required - any non-empty string
 - minLength - the string is equal to or longer than the specified number
 - contains - the string contains a specific substring
```java
 StringSchema schema = v.string();

        schema.isValid(""); // true
        schema.isValid(null); // true

        schema.required();

        schema.isValid("what does the fox say"); // true
        schema.isValid("hexlet"); // true
        schema.isValid(null); // false
        schema.isValid("");; // false

        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); // false

        schema.isValid("what does the fox say"); // false
        // already false, as one more check contains("whatthe") has been added
```
### Number validation
 - required - any number including zero
 - positive - positive number
 - range - the range in which the numbers should fall, including borders
```java
NumberSchema schema = v.number();

        schema.isValid(null); // true

        schema.required();

        schema.isValid(null); // false
        schema.isValid(10) // true
        schema.isValid("5"); // false

        schema.positive().isValid(10); // true
        schema.isValid(-10); // false

        schema.range(5, 10);

        schema.isValid(5); // true
        schema.isValid(10); // true
        schema.isValid(4); // false
        schema.isValid(11); // false
```
### Map
 - required - the Map data type is required
 - sizeof - the number of key-value pairs in the Map object must be equal to the specified
```java
MapSchema schema = v.map();

        schema.isValid(null); // true

        schema.required();

        schema.isValid(null) // false
        schema.isValid(new HashMap()); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.isValid(data); // true

        schema.sizeof(2);

        schema.isValid(data);  // false
        data.put("key2", "value2");
        schema.isValid(data); // true
```

Also there is a `MapSchema shape(Map<String, BaseSchema> schemas)` method which allows to validate data over all schemas given. Validation goes through the same keys in map with data and a map with schemas.
```java

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().range(8, 14));
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 10);
boolean isKolyaValid = schema.isValid(human1);// true
```