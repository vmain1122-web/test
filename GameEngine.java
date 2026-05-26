package com.example.demo; // ★これを1行目に追加！
import java.util.List;

public class GameEngine {
    private ScenarioEngine scenarioEngine;

    public GameEngine(ScenarioEngine scenarioEngine) {
        this.scenarioEngine = scenarioEngine;
    }

    // コントローラーからキャストのステータスと材料を受け取るように修正
    public void processServing(CastStatus status, List<String> ingredients) {
        if (ingredients == null || ingredients.size() < 3) {
            scenarioEngine.routeBranch("FAILURE");
            return;
        }

        // 1. 材料の成分をキャストの精神に適用（フーカーヘイズ要素）
        status.applyIngredients(ingredients);

        // 2. キャストの満足度（クリア条件）をジャッジ
        if (status.isAmuSatisfied()) {
            System.out.println("【有線判定】お給仕大成功：あむの脳波同調に成功しました。");
            scenarioEngine.routeBranch("SUCCESS");
        } else {
            System.out.println("【有線判定】お給仕失敗：ただのカスの液体です。");
            scenarioEngine.routeBranch("FAILURE");
        }
    }
}