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

        // 1. レシピ名の判定ロジック（コーヒートーク風）
        String drinkName = determineDrinkName(ingredients);
        System.out.println("【調合完了】生み出された液体: 「" + drinkName + "」");

        // 2. 材料の成分を精神に適用
        status.applyIngredients(ingredients);

        // 3. キャストごとのレシピ完全一致、またはステータス条件でジャッジ
        boolean isSatisfied = false;
        if (status.getCastId().equals("AMU")) {
            // あむは「ディープスリープ・ブレンド」が作れていれば大成功！
            isSatisfied = drinkName.equals("ディープスリープ・ブレンド") || status.isAmuSatisfied();
        } else if (status.getCastId().equals("RINO")) {
            // りのは「サイバー・バグ・サイダー」が作れていれば大成功！
            isSatisfied = drinkName.equals("サイバー・バグ・サイダー") || status.isRinoSatisfied();
        }

        // 4. ルート分岐
        if (isSatisfied) {
            scenarioEngine.routeBranch("SUCCESS");
        } else {
            scenarioEngine.routeBranch("FAILURE");
        }
    }

    // 🧪 3つの材料の組み合わせから、特別なドリンク名を抽出するメソッド
    private String determineDrinkName(List<String> ingredients) {
        int sleepCount = 0;
        int ketchupCount = 0;
        int herbCount = 0;
        int noiseCount = 0;

        for (String ing : ingredients) {
            if (ing.equals("安価な睡眠薬")) sleepCount++;
            if (ing.equals("同期用ケチャップ")) ketchupCount++;
            if (ing.equals("ドス黒いハーブ")) herbCount++;
            if (ing.equals("高周波ノイズ液")) noiseCount++;
        }

        // レシピ判定
        if (sleepCount == 2 && herbCount == 1) {
            return "ディープスリープ・ブレンド";
        }
        if (noiseCount == 2 && ketchupCount == 1) {
            return "サイバー・バグ・サイダー";
        }
        if (ketchupCount == 3) {
            return "血のインシデント・ラテ";
        }

        return "ただの濁った泥水";
    }
}