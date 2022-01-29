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

        assertTrue(schema.isValid(Integer.parseInt("0")));
        assertTrue(schema.isValid(Integer.parseInt("52347")));

        assertFalse(schema.isValid("not valid string"));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsPositive() {
        schema.required();

        assertTrue(schema.positive().isValid(Integer.parseInt("5")));
        assertTrue(schema.positive().isValid(Integer.parseInt("527")));

        assertFalse(schema.positive().isValid(-1));
        assertFalse(schema.positive().isValid(0));
    }

    @Test
    void testRange() {
        schema.required();

        assertTrue(schema.range(Integer.parseInt("5"), Integer.parseInt("10"))
                .isValid(Integer.parseInt("5")));
        assertTrue(schema.range(Integer.parseInt("5"), Integer.parseInt("10"))
                .isValid(Integer.parseInt("7")));
        assertTrue(schema.range(Integer.parseInt("5"), Integer.parseInt("10"))
                .isValid(Integer.parseInt("10")));

        assertFalse(schema.range(Integer.parseInt("5"), Integer.parseInt("10"))
                .isValid(Integer.parseInt("4")));
        assertFalse(schema.range(Integer.parseInt("5"), Integer.parseInt("10"))
                .isValid(Integer.parseInt("11")));
    }
}
