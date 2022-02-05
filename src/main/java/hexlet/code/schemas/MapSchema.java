package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema {
    public final void required() {
        addCheck(Objects::nonNull);
        addCheck(value -> value instanceof Map<?, ?>);
    }

    public final MapSchema sizeof(int size) {
        addCheck(value -> value == null
                || value instanceof Map<?, ?>
                && ((Map<?, ?>) value).size() == size);
        return this;
    }

    public final void shape(Map<String, BaseSchema> schema) {
        addCheck(key ->
                ((Map<?, ?>) key).entrySet()
                        .stream()
                        .allMatch(value -> schema.get(value.getKey()).isValid(value.getValue())));
    }
}
