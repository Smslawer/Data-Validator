package hexlet.code.schemas;


import java.util.Objects;

public class NumberSchema extends BaseSchema {
    public final void required() {
        check(Objects::nonNull);
        check(value -> value instanceof Integer);
    }

    public final NumberSchema positive() {
        check(value -> value == null
                || value instanceof Integer
                && (Integer) value > 0);
        return this;
    }

    public final NumberSchema range(int startValue, int endValue) {
        check(value -> value == null
                || value instanceof Integer
                && (Integer) value >= startValue
                && (Integer) value <= endValue);
        return this;
    }

}
