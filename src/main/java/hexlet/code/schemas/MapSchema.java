package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private final String sizeof = "sizeof";
    private final String required = "required";
    private final String shape = "shape";
    private Map<String, BaseSchema> shapeSchemas;

    public MapSchema required() {
        super.addChecks(required, x -> x instanceof Map);
        return this;
    }

    public MapSchema sizeof(int n) {
        super.addChecks(sizeof, x -> x instanceof Map && ((Map<?, ?>) x).size() == n);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        shapeSchemas = new HashMap<>(schemas);
        super.addChecks(shape, x -> x instanceof Map && isValidMap((Map<String, Object>) x, shapeSchemas));
        return this;
    }

    public Boolean isValidMap(Map<String, Object> map, Map<String, BaseSchema> schemas) {
        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (!shapeSchemas.containsKey(key)) {
                return false;
            }
            BaseSchema schema = schemas.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
