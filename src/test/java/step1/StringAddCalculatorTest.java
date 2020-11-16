package step1;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import step1.calculator.Inputs;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created By mand2 on 2020-11-16.
 */
public class StringAddCalculatorTest {

    @Test
    @DisplayName("입력한 값이 null이면 0을 입력한다.")
    void is_inputString_null() {
        // given
        String inputStr = null;

        // when
        InputValue inputValue = InputValue.of(inputStr);

        // then
        assertThat(inputValue.getInput()).isEqualTo("0");

    }

    @Test
    @DisplayName("입력한 값이 빈 문자열이면 0을 입력한다.")
    void is_inputString_empty() {
        // given
        String inputStr = "";

        // when
        InputValue inputValue = InputValue.of(inputStr);

        // then
        assertThat(inputValue.getInput()).isEqualTo("0");
    }

    @ParameterizedTest
    @DisplayName("기본 구분자가 입력값에 존재하지 않을 때 false")
    @ValueSource(strings = {"123", "13", "107243"})
    void is_splitter_not_exist(String input) {
        // given
        String inputStr = input;

        // when
        boolean result = StringSplitter.hasBasicSplitter(inputStr);

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @DisplayName("기본 구분자가 입력값에 존재할 때 true")
    @ValueSource(strings = {"12:3", "1,2,3", "10,72:3"})
    void is_splitter_exist(String input) {
        // given
        String inputStr = input;

        // when
        boolean result = StringSplitter.hasBasicSplitter(inputStr);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @DisplayName("입력한 값에 음수가 있을 때 예외를 던진다")
    @ValueSource(strings = {"12,23,-4,179", "12,-9:0", "-24742:54:90"})
    void is_under_zero(String input) {
        InputValue inputValue = InputValue.of(input);

        assertThatThrownBy(() -> Inputs.of(inputValue.getInput()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("입력한 값은 0 이상의 정수여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("기본 구분자가 없을 때 그 숫자를 반환한다")
    @ValueSource(strings = {"12", "4572", "1"})
    void no_splitter_return_just_that_number(String input) {
        // given
        InputValue inputValue = InputValue.of(input);

        // when
        Inputs inputs = Inputs.of(inputValue);

        // then
        assertThat(inputs.value()).isEqualTo(Arrays.asList(NumberUtils.toInt(input)));
    }

    @ParameterizedTest
    @DisplayName("기본 구분자가 입력값에 존재할 때 List 형태로 반환한다")
    @CsvSource(value = {"12:3=[12, 3]", "1,2,3=[1, 2, 3]", "10,72:3=[10, 72, 3]"}, delimiter = '=')
    void is_splitter_exist_returns_list_type(String input, String expected) {
        // given
        InputValue inputValue = InputValue.of(input);

        // when
        Inputs inputs = Inputs.of(inputValue);

        // then
        assertThat(inputs.value().toString()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("입력값에 숫자가 아닌 값이 포함되었을 때 예외를 던진다")
    @ValueSource(strings = {"안녕", "hello", "12:가", "h123", "1,a", "a,1324:4f"})
    void none_int_type_inputs_throws_runtime_exception(String input) {
        // given
        InputValue inputValue = InputValue.of(input);

        assertThatThrownBy(() -> Inputs.of(inputValue.getInput()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("입력한 값은 0 이상의 정수여야 합니다.");
    }

}
