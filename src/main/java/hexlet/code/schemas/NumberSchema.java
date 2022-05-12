package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private final String positive = "positive";
    private final String range = "range";
    private final String required = "required";

    public NumberSchema required() {
        super.addChecks(required, x -> x instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        super.addChecks(positive, x -> x == null || (x instanceof Integer && (Integer) x > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        super.addChecks(range, x -> x instanceof Integer && (Integer) x >= min && (Integer) x <= max);
        return this;
    }
}
