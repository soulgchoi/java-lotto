package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    private static final String MONEY_UNIT_ERROR = "[ERROR] 금액을 1000단위로 입력해주세요";
    private static final String MONEY_INT_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    private static final String MONEY_RANGE_ERROR = "[ERROR] 금액을 1000원 이상 입력해주세요";

    @Test
    @DisplayName("입력된 값이 숫자가 아닐 때 에러 발생")
    void money_isInteger() {
        String falseMoney = "로또값";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_INT_ERROR);
    }

    @Test
    @DisplayName("입력된 값이 1000보다 작을 때 에러 발생")
    void money_isMoreThanThousand() {
        String falseMoney = "500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_RANGE_ERROR);
    }

    @Test
    @DisplayName("입력된 값이 1000으로 나누어 떨어지지 않을 때 에러 발생")
    void money_noChange() {
        String falseMoney = "1500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_UNIT_ERROR);
    }

    @Test
    @DisplayName("당첨금에 따라 수익률을 맞게 계산하는지 확인")
    void earning_rate() {
        Map<Rank, Integer> sampleCount = new HashMap<>();
        sampleCount.put(Rank.FIFTH, 1);
        Money money = new Money("14000");

        Money.findEarning(sampleCount);
        assertThat(Money.getEarning()).isEqualTo(5000);

        String earningRate = money.findEarningRate();
        assertThat(earningRate).isEqualTo("0.36");
    }
}
