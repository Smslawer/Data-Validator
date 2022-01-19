package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    @Test
    void helloWorldTest() {
        assertThat("Hello, world!").isEqualTo(App.print());
    }
}
