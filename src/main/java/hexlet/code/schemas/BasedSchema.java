package hexlet.code.schemas;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
public class BasedSchema {
    private final List<Predicate<Object>> valid = new ArrayList<>();

    /**
     * @param data
     * @return boolean
     */
    public boolean isValid(Object data) {
        for (Predicate<Object> predicate : this.valid) {
            if (!predicate.test(data)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param predicate
     */
    public void check(Predicate<Object> predicate) {
        this.valid.add(predicate);
    }
}
