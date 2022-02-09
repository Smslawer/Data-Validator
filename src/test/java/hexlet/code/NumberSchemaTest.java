package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private static NumberSchema schema;

    /**
     * Make new Validator before each test.
     */
    @BeforeEach
    public void setValidator() {
        Validator valid = new Validator();
        schema = valid.number();
    }

    @Test
    void testWithoutParams() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testAnyNumber() {
        schema.required();

        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(52347));

        assertFalse(schema.isValid("not valid string"));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsPositive() {
        schema.required();

        schema.positive();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(527));

        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRange() {
        schema.required();

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(10));

        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
