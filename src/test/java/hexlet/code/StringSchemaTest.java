package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private static StringSchema schema;

    /**
     * Make new Validator before each test.
     */
    @BeforeEach
    public void setValidator() {
        Validator valid = new Validator();
        schema = valid.string();
    }

    @Test
    void testWithoutParams() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testRequired() {
        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testContains() {
        schema.required();

        assertTrue(schema.contains("what").isValid("what does the fox say"));

        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertTrue(schema.isValid("what whatthe does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void testMinLength() {
        schema.required();

        assertTrue(schema.minLength(Integer.parseInt("5")).isValid("what does the fox say"));

        assertFalse(schema.minLength(Integer.parseInt("50")).isValid("what does the fox say"));
    }
}
