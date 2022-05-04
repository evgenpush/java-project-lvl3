package hexlet.code.schemas;

public abstract class BaseSchema {
    private String type = "";
    private int min;
    private final String required = "required";

    /**
     * This implementation ...
     @return this.
     */
    public BaseSchema required() {
        type = required;
        return this;
    }

    /** @param m
     */
    public void setMin(int m) {
        this.min = m;
    }
    /**
     * This implementation ...
     @return min.
     */
    public Integer getMin() {
        return min;
    }
    /**
     * @param t
     */
    public void setType(String t) {
        this.type = t;

    }
    /**
     * This implementation ...
     @return type.
     */
    public String getType() {
        return type;
    }

    public abstract boolean isValid(Object object);
}
