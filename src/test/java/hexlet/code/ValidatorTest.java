package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class ValidatorTest {
    private Validator v;
    private final int four = 4;
    private final int five = 5;
    private final int seven = 7;
    private final int ten = 10;
    private final int two = 2;
    private final int eight = 8;
    private final int eleven = 11;
    private final int hundred = 100;
    private final int minusTen = -10;
    private final int minusFive = -5;
    private final int minusOne = -1;

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

        schemaStr.minLength(four);
        assertFalse(schemaStr.isValid(null));
        assertTrue(schemaStr.isValid("what does the fox say"));
        assertFalse(schemaStr.minLength(seven).isValid("Hello"));
        schemaStr.minLength(five);
        assertTrue(schemaStr.isValid("Hello"));

        assertTrue(schemaStr.contains("what").isValid("what does the fox say"));
        schemaStr.contains("wwwhat");
        assertFalse(schemaStr.isValid("what does the fox say"));
    }

    @Test
    public void testNumberSchema() {
        NumberSchema schema = v.number();

        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(five));
        assertTrue(schema.isValid("4"));

        schema.range(five, ten);
        assertTrue(schema.isValid(five));
        assertTrue(schema.isValid(eight));
        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid(minusOne));
        assertFalse(schema.isValid(eleven));
        assertFalse(schema.isValid(four));
        assertFalse(schema.range(eight, ten).isValid(five));
        assertFalse(schema.isValid("4"));
    }

    @Test
    public void testNumberSchemaRequest() {
        NumberSchema schema = v.number();

        assertTrue(schema.isValid("5"));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(five));
        schema.required();
        assertTrue(schema.isValid(five));
        assertFalse(schema.isValid("5"));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testNumberSchemaPositive() {
        NumberSchema schema = v.number();

        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(five));
        assertTrue(schema.positive().isValid(ten));
        assertFalse(schema.isValid(minusTen));
        assertTrue(schema.positive().isValid(null));
        assertFalse(schema.isValid("4"));
    }


    @Test
    public void testMapSchema() {
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid("Map")); // false
        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap())); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(two);

        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testMapSchemaDeep() {
        MapSchema schema = v.map();

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", hundred);
        assertTrue(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null); // true
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", minusFive);
        assertFalse(schema.isValid(human4)); // false

        schemas.put("name", v.string().required().contains("ya"));
        human4.put("age", minusFive);
        assertFalse(schema.isValid(human4)); // false
    }
}
