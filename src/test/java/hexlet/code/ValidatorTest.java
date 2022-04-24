package hexlet.code;

import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void testValidator() {
        Validator v = new Validator();
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
}
