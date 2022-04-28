package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    public Validator v;

    @BeforeEach
    public void init() {
        v = new Validator();
    }

    @Test
    public void testStringSchema() {
        StringSchema schemaStr = v.string();

        assertTrue(schemaStr.isValid(""));
        assertTrue(schemaStr.isValid(null));
        schemaStr.required();
        assertTrue(schemaStr.isValid("what does the fox say"));
        assertFalse(schemaStr.isValid(""));
        assertFalse(schemaStr.isValid(null));
        assertTrue(schemaStr.isValid("Hello"));

        assertTrue(schemaStr.contains("what").isValid("what does the fox say"));
        schemaStr.contains("wwwhat");
        assertFalse(schemaStr.isValid("what does the fox say"));

        schemaStr.minLength(4);
        assertTrue(schemaStr.isValid("what does the fox say"));
        assertFalse(schemaStr.minLength(7).isValid("Hello"));
        schemaStr.minLength(5);
        assertTrue(schemaStr.isValid("Hello"));
        assertTrue(schemaStr.isValid(null));
    }

    @Test
    public void testNumberSchema() {
        NumberSchema schema = v.number();

        assertTrue(schema.isValid("5"));
        assertTrue(schema.isValid(null));
        schema.required();
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid("5"));

        assertTrue(schema.positive().isValid(10));
        assertFalse(schema.isValid(-10));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(8));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(4));
    }


}
