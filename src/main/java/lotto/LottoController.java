package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private static final String DELIMITER = ",";
    private static Lottos lottos;

    public void startLotto() {
        lottos = new Lottos(LottoView.requestMoney());
        LottoView.buyLotto(lottos.getCount());
        lottos.generateLottos();
        LottoView.printLottos(lottos);
        Lotto winLotto = new Lotto(changeToArrayList());
    }

    public void makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        String bonusInput = LottoView.requestBonusBallNumber();
        WinningLotto winningLotto = new WinningLotto(winningInput, bonusInput);
    }
}
