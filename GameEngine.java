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

        int sleepCount = 0; int ketchupCount = 0; int herbCount = 0;
        int noiseCount = 0; int filterCount = 0; int donutCount = 0;
        int totalBitterness = 0; int totalSweetness = 0; int totalCyberPulse = 0;

        for (String ing : ingredients) {
            switch (ing) {
                case "安価な睡眠薬": sleepCount++; totalBitterness += 3; totalCyberPulse += 1; break;
                case "同期用ケチャップ": ketchupCount++; totalSweetness += 3; break;
                case "ドス黒いハーブ": herbCount++; totalBitterness += 2; totalSweetness += 1; break;
                case "高周波ノイズ液": noiseCount++; totalCyberPulse += 4; totalBitterness += 1; break;
                case "高性能医療フィルター": filterCount++; totalBitterness -= 2; totalCyberPulse += 2; break; 
                case "サイバー・グリッチ・ドーナツ": donutCount++; totalSweetness += 5; totalCyberPulse += 2; break;
            }
        }

        String drinkName = "ただの濁った泥水";
        if (sleepCount == 2 && herbCount == 1) {
            drinkName = "ディープスリープ・ブレンド";
        } else if (noiseCount == 2 && ketchupCount == 1) {
            drinkName = "サイバー・バグ・サイダー";
        } else if (ketchupCount == 3) {
            drinkName = "血のインシデント・ラテ";       // 🍅 ねねの通常正解レシピ
        } else if (donutCount > 0) {
            drinkName = "超糖類ハック・コンボ";       // 🍩 ショップ購入時の超正解レシピ
        } else if (filterCount > 0) {
            drinkName = "純化ナノ・カクテル";
        } else if (totalBitterness >= 5 && totalSweetness >= 3) {
            drinkName = "共依存ビター・ココア";
        }

        this.lastBrewedDrink = new BrewedDrink(drinkName, totalBitterness, totalSweetness, totalCyberPulse);
        status.applyIngredients(ingredients);

        boolean isSatisfied = false;
        if (status.getCastId().equals("AMU")) {
            isSatisfied = drinkName.equals("ディープスリープ・ブレンド") || status.isAmuSatisfied();
        } else if (status.getCastId().equals("RINO")) {
            isSatisfied = drinkName.equals("サイバー・バグ・サイダー") || status.isRinoSatisfied();
        } else if (status.getCastId().equals("NENE")) {
            // 🎯 ねねの合格ジャッジ：甘口隠しレシピが完成しているか、ステータス条件をクリアすれば大成功
            isSatisfied = drinkName.equals("血のインシデント・ラテ") 
                       || drinkName.equals("超糖類ハック・コンボ") 
                       || status.isNeneSatisfied();
        }

        if (isSatisfied) {
            System.out.println("【判定システム】" + status.getCastId() + " 大大成功: " + drinkName);
            scenarioEngine.routeBranch("SUCCESS");
        } else {
            System.out.println("【判定システム】" + status.getCastId() + " 失敗.");
            scenarioEngine.routeBranch("FAILURE");
        }
    }

    public BrewedDrink getLastBrewedDrink() { return this.lastBrewedDrink; }
}