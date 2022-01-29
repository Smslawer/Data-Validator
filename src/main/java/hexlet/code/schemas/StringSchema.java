package hexlet.code.schemas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StringSchema extends BasedSchema {

    public final StringSchema required() {
        check(Objects::nonNull);
        check(value -> value instanceof String);
        check(value -> !(((String) value).trim().isEmpty()));
        return this;
    }

    public final StringSchema contains(String isContain) {
        check(value -> value == null
                || value instanceof String
                && ((String) value).contains(isContain));
        return this;
    }

    public final StringSchema minLength(int length) {
        check(value -> value == null
                || value instanceof String
                && ((String) value).trim().length() >= length);
        return this;
    }
}
