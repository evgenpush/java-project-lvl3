package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private final String positive = "positive";
    private final String range = "range";
    private final String required = "required";
    private int max;

    public NumberSchema positive() {
        setType(positive);
        return this;
    }

    public NumberSchema range(int min, int m) {
        setType(range);
        this.setMin(min);
        this.max = m;
        return this;
    }

    public boolean isValid(Object object) {
        Boolean isResult;
        switch (getType()) {
            case required:
                if (!isInteger(object)) {
                    return false;
                }
            case positive:
                if (object == null) {
                    return true;
                }
                if (!isInteger(object)) {
                    return false;
                }
                isResult = (Integer) object > 0;
                break;
            case range:
                if (!isInteger(object)) {
                    return false;
                }
                isResult = (Integer) object >= getMin() && (Integer) object <= max;
                break;
            default:
                isResult = true;
        }
        return isResult;
    }

    public boolean isInteger(Object object) {
        return object instanceof Integer;
    }
}
