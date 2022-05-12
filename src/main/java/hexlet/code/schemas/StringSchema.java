package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private final String minLength = "minLength";
    private final String contains = "contains";
    private final String required = "required";

    public StringSchema required() {
        super.addChecks(required, x -> x instanceof String && ((String) x).length() > 0);
        return this;
    }

    public StringSchema contains(String str) {
        super.addChecks(contains, x -> x instanceof String && ((String) x).contains(str));
        return this;
    }

    public StringSchema minLength(int min) {
        super.addChecks(minLength, x -> x instanceof String && ((String) x).length() >= min);
        return this;
    }
}
