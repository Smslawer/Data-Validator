package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    /**
     * @param data
     * @return boolean
     */
    public boolean isValid(Object data) {
        for (Predicate<Object> predicate : this.predicates) {
            if (!predicate.test(data)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param predicate
     */
    protected void addCheck(Predicate<Object> predicate) {
        this.predicates.add(predicate);
    }
}
