package com.example.demo;

import java.util.List;

public class GameEngine {
    private ScenarioEngine scenarioEngine;

    public GameEngine(ScenarioEngine scenarioEngine) {
        this.scenarioEngine = scenarioEngine;
    }

    public void processServing(CastStatus status, List<String> ingredients) {
        if (ingredients == null || ingredients.size() < 3) {
            scenarioEngine.routeBranch("FAILURE");
            return;
        }

        // 1. 材料の成分を精神に適用
        status.applyIngredients(ingredients);

        // 2. キャストごとに異なるクリア条件でジャッジ
        boolean isSatisfied = false;
        if (status.getCastId().equals("AMU")) {
            isSatisfied = status.isAmuSatisfied();
        } else if (status.getCastId().equals("RINO")) {
            isSatisfied = status.isRinoSatisfied();
        }

        // 3. ルート分岐
        if (isSatisfied) {
            System.out.println("【判定】" + status.getCastId() + " の脳波調律・同調に大成功しました！");
            scenarioEngine.routeBranch("SUCCESS");
        } else {
            System.out.println("【判定】" + status.getCastId() + " の調合に失敗。脳が拒絶しています。");
            scenarioEngine.routeBranch("FAILURE");
        }
    }
}