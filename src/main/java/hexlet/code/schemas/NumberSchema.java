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

        switch (getType()) {
            case required:
                if (!isNotInteger(object)) {
                    isResult = true;
                }
                break;
            case positive:
                if (object == null) {
                    isResult = true;
                    break;
                }
                if (isNotInteger(object)) {
                    isResult = false;
                    break;
                }
                if ((Integer) object > 0) {
                    isResult = true;
                    break;
                }
                isResult = false;
                break;
            case range:
                if (isNotInteger(object)) {
                    isResult = false;
                    break;
                }
                if ((Integer) object >= getMin() && (Integer) object <= max) {
                    isResult = true;
                    break;
                }
                isResult = false;
                break;
            default:
                isResult = true;
        }
        return isResult;
    }

    public boolean isNotInteger(Object object) {
        return !(object instanceof Integer);
    }
}
