package pod.tsu.spring.secureapi.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("UnitTest")
class RoleTest {

    @ParameterizedTest
    @ValueSource(strings = {"admin", "tester", "developer"})
    @DisplayName("Builder tested using valueSource")
    public void builder_tested_withValueSource(String name) {

        Role role = Role.builder().name(name).build();
        assertThat(role.getName()).isEqualTo(name);

    }

}