package com.example.demo;

import java.util.List;

public class GameEngine {
    private ScenarioEngine scenarioEngine;
    private BrewedDrink lastBrewedDrink;

    public GameEngine(ScenarioEngine scenarioEngine) {
        this.scenarioEngine = scenarioEngine;
    }

    public void processServing(CastStatus status, List<String> ingredients) {
        if (ingredients == null || ingredients.size() < 3) {
            this.lastBrewedDrink = new BrewedDrink("ただの濁った泥水", 0, 0, 0);
            scenarioEngine.routeBranch("FAILURE");
            return;
        }

        int totalBitterness = 0;
        int totalSweetness = 0;
        int totalCyberPulse = 0;

        for (String ing : ingredients) {
            switch (ing) {
                case "安価な睡眠薬":
                    totalBitterness += 3; totalCyberPulse += 1; break;
                case "同期用ケチャップ":
                    totalSweetness += 3; break;
                case "ドス黒いハーブ":
                    totalBitterness += 2; totalSweetness += 1; break;
                case "高周波ノイズ液":
                    totalCyberPulse += 4; totalBitterness += 1; break;
                // 🛒 ショップ解禁アイテムの味覚効果
                case "高性能医療フィルター":
                    totalBitterness -= 2; totalCyberPulse += 2; break; 
                case "サイバー・グリッチ・ドーナツ":
                    totalSweetness += 5; totalCyberPulse += 2; break;
            }
        }

        // 🧪 ショップ購入アイテムが絡む、極上隠しレシピの判定
        String drinkName = "ただの濁った泥水";
        
        if (ingredients.contains("高性能医療フィルター")) {
            drinkName = "純化ナノ・カクテル";
        } else if (ingredients.contains("サイバー・グリッチ・ドーナツ")) {
            drinkName = "超糖類ハック・コンボ";
        } else if (totalBitterness >= 6 && totalBitterness <= 8 && totalCyberPulse == 1) {
            drinkName = "ディープスリープ・ブレンド";
        } else if (totalCyberPulse >= 8 && totalSweetness == 3) {
            drinkName = "サイバー・バグ・サイダー";
        } else if (totalSweetness == 9) {
            drinkName = "血のインシデント・ラテ";
        }

        this.lastBrewedDrink = new BrewedDrink(drinkName, totalBitterness, totalSweetness, totalCyberPulse);
        status.applyIngredients(ingredients);

        // 新アイテムでの調合も、2日目のりのの精神を100%満足（大成功）させるトリガーに認定！
        boolean isSatisfied = false;
        if (status.getCastId().equals("AMU")) {
            isSatisfied = drinkName.equals("ディープスリープ・ブレンド");
        } else if (status.getCastId().equals("RINO")) {
            isSatisfied = drinkName.equals("サイバー・バグ・サイダー") || drinkName.equals("純化ナノ・カクテル") || drinkName.equals("超糖類ハック・コンボ");
        }

        if (isSatisfied) {
            scenarioEngine.routeBranch("SUCCESS");
        } else {
            scenarioEngine.routeBranch("FAILURE");
        }
    }

    public BrewedDrink getLastBrewedDrink() { return this.lastBrewedDrink; }
}