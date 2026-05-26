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

        // 🧪 1. 投入された各素材の個数を正確にカウント（順不同に対応するため）
        int sleepCount = 0;
        int ketchupCount = 0;
        int herbCount = 0;
        int noiseCount = 0;
        int filterCount = 0;
        int donutCount = 0;

        int totalBitterness = 0;
        int totalSweetness = 0;
        int totalCyberPulse = 0;

        for (String ing : ingredients) {
            switch (ing) {
                case "安価な睡眠薬":
                    sleepCount++; totalBitterness += 3; totalCyberPulse += 1; break;
                case "同期用ケチャップ":
                    ketchupCount++; totalSweetness += 3; break;
                case "ドス黒いハーブ":
                    herbCount++; totalBitterness += 2; totalSweetness += 1; break;
                case "高周波ノイズ液":
                    noiseCount++; totalCyberPulse += 4; totalBitterness += 1; break;
                case "高性能医療フィルター":
                    filterCount++; totalBitterness -= 2; totalCyberPulse += 2; break; 
                case "サイバー・グリッチ・ドーナツ":
                    donutCount++; totalSweetness += 5; totalCyberPulse += 2; break;
            }
        }

        // 🧪 2. 素材の組み合わせ数（レシピ）の厳密な名前ジャッジ
        String drinkName = "ただの濁った泥水";
        
        if (sleepCount == 2 && herbCount == 1) {
            drinkName = "ディープスリープ・ブレンド";  // 🎯 あむの正解レシピ
        } else if (noiseCount == 2 && ketchupCount == 1) {
            drinkName = "サイバー・バグ・サイダー";    // 🎯 りのの正解レシピ
        } else if (filterCount > 0) {
            drinkName = "純化ナノ・カクテル";          // ⚙️ ショップ隠しレシピ
        } else if (donutCount > 0) {
            drinkName = "超糖類ハック・コンボ";        // 🍩 ショップ隠しレシピ
        } else if (ketchupCount == 3) {
            drinkName = "血のインシデント・ラテ";
        } else if (totalBitterness >= 5 && totalSweetness >= 3) {
            drinkName = "共依存ビター・ココア";
        }

        // 味覚データを保持
        this.lastBrewedDrink = new BrewedDrink(drinkName, totalBitterness, totalSweetness, totalCyberPulse);
        
        // 精神ステータスへ数値を適用
        status.applyIngredients(ingredients);

        // 🎯 3. キャストごとの大成功条件のガードを強固に修正
        boolean isSatisfied = false;
        
        if (status.getCastId().equals("AMU")) {
            // 【あむの合格条件】レシピ名が完全一致しているか、もしくはメーターの計算結果がクリア基準を満たしていれば合格！
            isSatisfied = drinkName.equals("ディープスリープ・ブレンド") || status.isAmuSatisfied();
        } else if (status.getCastId().equals("RINO")) {
            // 【りのの合格条件】
            isSatisfied = drinkName.equals("サイバー・バグ・サイダー") 
                       || drinkName.equals("純化ナノ・カクテル") 
                       || drinkName.equals("超糖類ハック・コンボ") 
                       || status.isRinoSatisfied();
        }

        // 4. ルート分岐へ送信
        if (isSatisfied) {
            System.out.println("【判定システム】" + status.getCastId() + " の調合に大成功。レシピ: " + drinkName);
            scenarioEngine.routeBranch("SUCCESS");
        } else {
            System.out.println("【判定システム】" + status.getCastId() + " の調合は失敗。泥水判定。");
            scenarioEngine.routeBranch("FAILURE");
        }
    }

    public BrewedDrink getLastBrewedDrink() { return this.lastBrewedDrink; }
}