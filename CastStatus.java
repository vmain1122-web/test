package com.example.demo;

import java.util.List;

public class CastStatus {
    private String castId;
    private int sleepDebt;   // 睡眠負債
    private int waveNoise;   // 脳波ノイズ
    private int syncRate;    // 依存同調率

    public CastStatus(String castId, int sleepDebt, int waveNoise, int syncRate) {
        this.castId = castId;
        this.sleepDebt = sleepDebt;
        this.waveNoise = waveNoise;
        this.syncRate = syncRate;
    }

    // 🎯 あむ・りの の調合判定用メソッド
    public boolean isAmuSatisfied() {
        return this.sleepDebt <= 30 && this.waveNoise <= 30 && this.syncRate >= 45;
    }

    public boolean isRinoSatisfied() {
        // 【りののクリア条件】
        // 電波アンドロイドなので「脳波ノイズが80%以上」かつ「依存同調率が60%以上」で脳が完全にハック（バグ）されれば大成功！
        return this.waveNoise >= 80 && this.syncRate >= 60;
    }

    public void applyIngredients(List<String> ingredients) {
        for (String ing : ingredients) {
            switch (ing) {
                case "安価な睡眠薬":
                    if (this.castId.equals("RINO")) {
                        this.sleepDebt -= 10; // アンドロイドにはあまり眠気薬が効かない
                        this.waveNoise -= 30; // 代わりに電子のノイズが強制シャットダウンされる
                    } else {
                        this.sleepDebt -= 30;
                        this.waveNoise -= 10;
                    }
                    break;
                case "同期用ケチャップ":
                    this.syncRate += 25;
                    this.sleepDebt += 5;
                    break;
                case "ドス黒いハーブ":
                    if (this.castId.equals("RINO")) {
                        this.waveNoise -= 40; // オーガニックハーブが電子脳を強烈に冷却する
                        this.syncRate -= 15;
                    } else {
                        this.waveNoise -= 20;
                        this.syncRate -= 5;
                    }
                    break;
                case "高周波ノイズ液":
                    if (this.castId.equals("RINO")) {
                        this.waveNoise += 30; // ノイズ液を燃料にして脳波がオーバークロックする！
                        this.syncRate += 20; // 同調率も爆上がり
                    } else {
                        this.waveNoise += 40;
                        this.sleepDebt -= 10;
                    }
                    break;
            }
        }
        // ガード処理
        this.sleepDebt = Math.max(0, Math.min(100, this.sleepDebt));
        this.waveNoise = Math.max(0, Math.min(100, this.waveNoise));
        this.syncRate = Math.max(0, Math.min(100, this.syncRate));
    }

    // Getters
    public String getCastId() { return castId; }
    public int getSleepDebt() { return sleepDebt; }
    public int getWaveNoise() { return waveNoise; }
    public int getSyncRate() { return syncRate; }
}