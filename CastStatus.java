package com.example.demo;
import java.util.List; // ★これが必要でした！

public class CastStatus {
    private String castId;
    private int sleepDebt;   // 睡眠負債 (高いと危険)
    private int waveNoise;   // 脳波ノイズ (高いと危険)
    private int syncRate;    // 依存同調率 (本作では高いとルート進展)

    public CastStatus(String castId, int sleepDebt, int waveNoise, int syncRate) {
        this.castId = castId;
        this.sleepDebt = sleepDebt;
        this.waveNoise = waveNoise;
        this.syncRate = syncRate;
    }

    // ドリンクの材料を受け取って、数値をフーカーヘイズ風に増減させるメソッド
    public void applyIngredients(List<String> ingredients) {
        for (String ing : ingredients) {
            switch (ing) {
                case "安価な睡眠薬":
                    this.sleepDebt -= 30;  // ガッツリ眠らせる
                    this.waveNoise -= 10;
                    break;
                case "同期用ケチャップ":
                    this.syncRate += 25;   // 心の距離を強制同期
                    this.sleepDebt += 5;
                    break;
                case "ドス黒いハーブ":
                    this.waveNoise -= 20;  // リラックスさせてノイズ除去
                    this.syncRate -= 5;
                    break;
                case "高周波ノイズ液":
                    this.waveNoise += 40;  // 脳みそをバグらせる
                    this.sleepDebt -= 10;
                    break;
            }
        }
        // 数値が0〜100の間に収まるようにガード
        this.sleepDebt = Math.max(0, Math.min(100, this.sleepDebt));
        this.waveNoise = Math.max(0, Math.min(100, this.waveNoise));
        this.syncRate = Math.max(0, Math.min(100, this.syncRate));
    }

    // 判定チェック：あむのその日のクリア条件を満たしているか？
    public boolean isAmuSatisfied() {
        return this.sleepDebt <= 50 && this.syncRate >= 40 && this.waveNoise < 70;
    }

    // Getters
    public int getSleepDebt() { return sleepDebt; }
    public int getWaveNoise() { return waveNoise; }
    public int getSyncRate() { return syncRate; }
}