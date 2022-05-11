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
        Boolean isResult = false;
        Boolean isInt = isInteger(object);
        switch (getType()) {
            case required:
                isResult = isInt;
                break;
            case positive:
                isResult = object == null;
                if (isResult) {
                    break;
                }
                if (!isInt) {
                    break;
                }
                isResult = (Integer) object > 0;
                break;
            case range:
                if (!isInt) {
                    break;
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
