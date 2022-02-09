package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema extends BaseSchema {

    public final StringSchema required() {
        addCheck(Objects::nonNull);
        addCheck(value -> value instanceof String);
        addCheck(value -> !(((String) value).trim().isEmpty()));
        return this;
    }

    public final StringSchema contains(String isContain) {
        addCheck(value -> value == null
                || value instanceof String
                && ((String) value).contains(isContain));
        return this;
    }

    public final StringSchema minLength(int length) {
        addCheck(value -> value == null
                || value instanceof String
                && ((String) value).trim().length() >= length);
        return this;
    }
}
