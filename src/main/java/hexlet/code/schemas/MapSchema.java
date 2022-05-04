package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private int size;
    private final String sizeof = "sizeof";
    private final String required = "required";
    private final String shape = "shape";
    private Map<String, BaseSchema> shapeSchemas;

    public MapSchema sizeof(int n) {
        setType(sizeof);
        size = n;
        return this;
    }

    public boolean isValid(Object object) {
        Map<String, String> map;
        switch (getType()) {
            case required:
                return isMap(object);
            case sizeof:
                if (!(isMap(object))) {
                    return false;
                }
                map = objectToMap(object);
                return map.size() == size;
            case shape:
                if (!(isMap(object))) {
                    return false;
                }
                map = objectToMap(object);
                for (Map.Entry entry : map.entrySet()) {
                    String key = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (!shapeSchemas.containsKey(key)) {
                        return false;
                    }
                    BaseSchema schema = shapeSchemas.get(key);
                    if (!schema.isValid(value)) {
                        return false;
                    }
                }
            default:
                return true;
        }
    }

    public boolean isMap(Object object) {
        return object instanceof Map;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        shapeSchemas = new HashMap<>(schemas);
        setType(shape);
    }

    public Map objectToMap(Object object) {
        if (!(isMap(object))) {
            return null;
        }
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(object, Map.class);
        return map;
    }
}
