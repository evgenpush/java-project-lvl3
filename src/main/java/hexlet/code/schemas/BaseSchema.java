package hexlet.code.schemas;

public class BaseSchema {
    private String type = "";
    private int min;
    public final String required = "required";

    public BaseSchema required() {
        type = required;
        return this;
    }
    public void setMin(int m) {
        this.min = m;
    }

    public Integer getMin() {
        return min;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
