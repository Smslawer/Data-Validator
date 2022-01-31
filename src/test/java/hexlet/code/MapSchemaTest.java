package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private static MapSchema schema;

    /**
     * Make new Validator before each test.
     */
    @BeforeEach
    public void setValidator() {
        Validator valid = new Validator();
        schema = valid.map();
    }

    @Test
    void testWithoutParams() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testRequired() {
        schema.required();

        assertTrue(schema.isValid(new HashMap()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testSizeOf() {
        schema.required();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.sizeof(2).isValid(data));  // false

        data.put("key2", "value2");

        assertTrue(schema.sizeof(2).isValid(data)); // true
    }

    @Test
    void testShape() {
        Validator valid = new Validator();
        MapSchema mapSchema = valid.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", valid.string().required());
        schemas.put("age", valid.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", Integer.parseInt("100"));
        assertTrue(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(mapSchema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(mapSchema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", Integer.parseInt("-5"));
        assertFalse(mapSchema.isValid(human4));
    }
}
