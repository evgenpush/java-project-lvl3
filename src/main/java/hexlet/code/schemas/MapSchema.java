package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private int size;
    private final String sizeof = "sizeof";
    private final String required = "required";

    public MapSchema sizeof(int n) {
        setType(sizeof);
        size = n;
        return this;
    }
    
    public boolean isValid(Object object) {

        switch (getType()) {
            case required:
                return isMap(object);
            case sizeof:
                if (!(isMap(object))) {
                    return false;
                }
                ObjectMapper oMapper = new ObjectMapper();
                Map<String, String> map = oMapper.convertValue(object, Map.class);
                return map.size() == size;
            default:
                return true;
        }
    }

    public boolean isMap(Object object) {
        return object instanceof Map;
    }
}
