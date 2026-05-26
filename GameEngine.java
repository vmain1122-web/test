package com.example.demo;

import java.util.List;

public class GameEngine {
    private ScenarioEngine scenarioEngine;
    private BrewedDrink lastBrewedDrink; // 直前に淹れたドリンクを保持

    public GameEngine(ScenarioEngine scenarioEngine) {
        this.scenarioEngine = scenarioEngine;
    }

    public void processServing(CastStatus status, List<String> ingredients) {
        if (ingredients == null || ingredients.size() < 3) {
            this.lastBrewedDrink = new BrewedDrink("ただの濁った泥水", 0, 0, 0);
            scenarioEngine.routeBranch("FAILURE");
            return;
        }

        // 🧪 1. 材料から「味覚パラメーター」を裏で動的合算する（コーヒートーク仕様）
        int totalBitterness = 0;
        int totalSweetness = 0;
        int totalCyberPulse = 0;

        for (String ing : ingredients) {
            switch (ing) {
                case "安価な睡眠薬":
                    totalBitterness += 3; totalCyberPulse += 1;
                    break;
                case "同期用ケチャップ":
                    totalSweetness += 3;
                    break;
                case "ドス黒いハーブ":
                    totalBitterness += 2; totalSweetness += 1;
                    break;
                case "高周波ノイズ液":
                    totalCyberPulse += 4; totalBitterness += 1;
                    break;
            }
        }

        // 🧪 2. 味覚パラメーターの比率から、ドリンク名を特殊判定
        String drinkName = "ただの濁った泥水";
        if (totalBitterness >= 6 && totalBitterness <= 8 && totalCyberPulse == 1) {
            drinkName = "ディープスリープ・ブレンド";
        } else if (totalCyberPulse >= 8 && totalSweetness == 3) {
            drinkName = "サイバー・バグ・サイダー";
        } else if (totalSweetness == 9) {
            drinkName = "血のインシデント・ラテ";
        } else if (totalBitterness >= 5 && totalSweetness >= 3) {
            drinkName = "共依存ビター・ココア";
        }

        // インスタンス化して保存
        this.lastBrewedDrink = new BrewedDrink(drinkName, totalBitterness, totalSweetness, totalCyberPulse);

        // 3. キャストの精神ステータスに成分を適用
        status.applyIngredients(ingredients);

        // 4. クリア条件ジャッジ
        boolean isSatisfied = false;
        if (status.getCastId().equals("AMU")) {
            isSatisfied = drinkName.equals("ディープスリープ・ブレンド") || status.isAmuSatisfied();
        } else if (status.getCastId().equals("RINO")) {
            isSatisfied = drinkName.equals("サイバー・バグ・サイダー") || status.isRinoSatisfied();
        }

        if (isSatisfied) {
            scenarioEngine.routeBranch("SUCCESS");
        } else {
            scenarioEngine.routeBranch("FAILURE");
        }
    }

    public BrewedDrink getLastBrewedDrink() {
        return this.lastBrewedDrink;
    }
}