package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class StringSchema extends BaseSchema {
    private final String minLength = "minLength";
    private final String contains = "contains";
    private final String required = "required";
    private String subStr;

    public StringSchema required() {
        super.required();
        return this;
    }

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

    public boolean isValid(Object object) {
        ObjectMapper oMapper = new ObjectMapper();
        String str = oMapper.convertValue(object, String.class);
        Boolean isResult;

        switch (getType()) {
            case contains:
                if (str == null) {
                    isResult = false;
                    break;
                }
                isResult = str.contains(subStr);
                break;
            case required:
                if (str == null) {
                    isResult = false;
                    break;
                }
                isResult = str.length() > 0;
                break;
            case minLength:
                if (str == null) {
                    isResult = false;
                    break;
                }
                isResult = str.length() >= getMin();
                break;
            default:
                isResult = true;
        }
        return isResult;
    }
}
