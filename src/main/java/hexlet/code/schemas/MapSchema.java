package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema {
    public final void required() {
        check(Objects::nonNull);
        check(value -> value instanceof Map<?, ?>);
    }

    public final MapSchema sizeof(int size) {
        check(value -> value == null
                || value instanceof Map<?, ?>
                && ((Map<?, ?>) value).size() == size);
        return this;
    }

    public final void shape(Map<String, BaseSchema> schema) {
        check(key ->
                ((Map<?, ?>) key).entrySet()
                        .stream()
                        .allMatch(value -> schema.get(value.getKey()).isValid(value.getValue())));
    }
}
