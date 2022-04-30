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

    public boolean isValid(Object x) {
        switch (getType()) {
            case required:
                if (!isNotInteger(x)) {
                    return true;
                }
            case positive:
                if (isNotInteger(x)) {
                    return false;
                }
                if ((Integer) x > 0) {
                    return true;
                }
                return false;
            case range:
                if (isNotInteger(x)) {
                    return false;
                }
                if ((Integer) x >= getMin() && (Integer) x <= max) {
                    return true;
                }
                return false;
            default:
                return true;
        }
    }

    public boolean isNotInteger(Object a) {
        return !(a instanceof Integer);
    }
}
