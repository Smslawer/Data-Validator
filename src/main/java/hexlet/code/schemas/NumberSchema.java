package hexlet.code.schemas;


import java.util.Objects;

public class NumberSchema extends BaseSchema {
    public final void required() {
        addCheck(Objects::nonNull);
        addCheck(value -> value instanceof Integer);
    }

    public final NumberSchema positive() {
        addCheck(value -> value == null
                || value instanceof Integer
                && (Integer) value > 0);
        return this;
    }

    public final NumberSchema range(int startValue, int endValue) {
        addCheck(value -> value == null
                || value instanceof Integer
                && (Integer) value >= startValue
                && (Integer) value <= endValue);
        return this;
    }

}
