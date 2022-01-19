package hexlet.code.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
public class StringSchema {
    private String valid;
    private String contain;
    private Integer minLength;
    private boolean req = true;

    public final boolean isValid(String isValid) {
        this.valid = isValid;
        boolean result = true;

        if (isValid == null || isValid.isEmpty()) {
            return req;
        }

        if (contain != null) {
            List<String> splitted = List.of(isValid.split(" "));
            result = splitted.contains(contain);
        }

        if (minLength != null && minLength > 0) {
            return isValid.length() >= minLength;
        }

        return result;
    }

    public final void required() {
        if (valid == null) {
            valid = "";
        }
        if (valid.isEmpty()) {
            this.req = false;
        }
    }

    public final StringSchema contains(String isContain) {
        this.contain = isContain;
        return this;
    }

    public final StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

}
