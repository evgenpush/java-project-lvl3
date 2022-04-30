package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private final String minLength = "minLength";
    private final String contains = "contains";
    private final String required = "required";
    private String subStr;
    public StringSchema contains(String str) {
        setType(contains);
        subStr = str;
        return this;
    }

    public StringSchema minLength(int min) {
        setMin(min);
        setType(minLength);
        return this;
    }

    public boolean isValid(String str) {

        switch (getType()) {
            case contains:
                if (str == null) {
                    return false;
                }
                return str.contains(subStr);
            case required:
                if (str == null) {
                    return false;
                }
                return str.length() > 0;
            case minLength:
                if (str == null) {
                    return true;
                }
                return str.length() >= getMin();
            default:
                return true;
        }
    }
}
