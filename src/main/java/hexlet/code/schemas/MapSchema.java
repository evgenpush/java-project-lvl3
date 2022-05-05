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
        Boolean isResult = true;
        switch (getType()) {
            case required:
                isResult = isMap(object);
                break;
            case sizeof:
                if (!(isMap(object))) {
                    isResult = false;
                    break;
                }
                map = objectToMap(object);
                isResult = map.size() == size;
                break;
            case shape:
                if (!(isMap(object))) {
                    isResult = false;
                    break;
                }
                map = objectToMap(object);
                for (Map.Entry entry : map.entrySet()) {
                    String key = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (!shapeSchemas.containsKey(key)) {
                        isResult = false;
                        break;
                    }
                    BaseSchema schema = shapeSchemas.get(key);
                    if (!schema.isValid(value)) {
                        isResult = false;
                        break;
                    }
                }
                break;
            default:
                isResult = true;
        }
        return isResult;
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
