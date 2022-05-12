package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {

    private Map<String, Predicate> checks = new HashMap<>();

    protected final void addChecks(String type, Predicate check) {
        checks.put(type, check);
    }

    /** @param object
     * @return valid test.
     */
    public Boolean isValid(Object object) {
        for (Map.Entry<String, Predicate> entry : checks.entrySet()) {
            if (!entry.getValue().test(object)) {
                return false;
            }
        }
        return true;
    }
}
