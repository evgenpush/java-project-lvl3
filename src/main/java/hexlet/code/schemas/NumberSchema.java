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
        switch (getType()) {
            case required:
                if (!isNotInteger(object)) {
                    return true;
                }
            case positive:
                if (object == null) {
                    return true;
                }
                if (isNotInteger(object)) {
                    return false;
                }
                if ((Integer) object > 0) {
                    return true;
                }
                return false;
            case range:
                if (isNotInteger(object)) {
                    return false;
                }
                if ((Integer) object >= getMin() && (Integer) object <= max) {
                    return true;
                }
                return false;
            default:
                return true;
        }
    }

    public boolean isNotInteger(Object object) {
        return !(object instanceof Integer);
    }
}
