package hexlet.code.schemas;

public class StringSchema {
    private String type = "";
    private final String required = "required";
    private final String minLength = "minLength";
    private final String contains = "contains";
    private int min;
    private String subStr;

    public StringSchema contains(String str) {
        type = contains;
        subStr = str;
        return this;
    }

    public StringSchema required() {
        type = required;
        return this;
    }

    public StringSchema minLength(int min) {
        this.min = min;
        type = minLength;
        return this;
    }

    public boolean isValid(String str) {

        switch (type) {
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
                return str.length() >= min;
        }
        return true;
    }
}
