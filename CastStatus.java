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

    public boolean isAmuSatisfied() {
        return this.sleepDebt <= 30 && this.waveNoise <= 30 && this.syncRate >= 45;
    }

    public boolean isRinoSatisfied() {
        return this.waveNoise >= 80 && this.syncRate >= 60;
    }

    // 🎯 新設：ねねのクリア条件
    public boolean isNeneSatisfied() {
        // 【ねねのクリア条件】
        // 依存同調率が75%以上、かつ脳波ノイズが40%以下まで「甘味」で満たされ、
        // 逆にマスター側から完全に肯定されて心がとろければ大成功！
        return this.syncRate >= 75 && this.waveNoise <= 40;
    }

    public void applyIngredients(List<String> ingredients) {
        for (String ing : ingredients) {
            switch (ing) {
                case "安価な睡眠薬":
                    if (this.castId.equals("RINO")) {
                        this.sleepDebt -= 10; this.waveNoise -= 30;
                    } else if (this.castId.equals("NENE")) {
                        this.sleepDebt -= 20;
                        this.waveNoise += 15; // ねねに睡眠薬を使うと、現実逃避の妄想ノイズが逆に増える
                    } else {
                        this.sleepDebt -= 30; this.waveNoise -= 10;
                    }
                    break;
                case "同期用ケチャップ":
                    if (this.castId.equals("NENE")) {
                        this.syncRate += 30;  // ケチャップの甘味はねねの好物
                        this.waveNoise -= 10;
                    } else {
                        this.syncRate += 25; this.sleepDebt += 5;
                    }
                    break;
                case "ドス黒いハーブ":
                    if (this.castId.equals("RINO")) {
                        this.waveNoise -= 40; this.syncRate -= 15;
                    } else if (this.castId.equals("NENE")) {
                        this.waveNoise -= 5;  // オーガニックな苦味はねねの口に合わない
                        this.syncRate -= 10;
                    } else {
                        this.waveNoise -= 20; this.syncRate -= 5;
                    }
                    break;
                case "高周波ノイズ液":
                    if (this.castId.equals("RINO")) {
                        this.waveNoise += 30; this.syncRate += 20;
                    } else {
                        this.waveNoise += 40; this.sleepDebt -= 10;
                    }
                    break;
                // 🛒 ショップ解禁アイテムの味覚効果
                case "高性能医療フィルター":
                    totalCalculatedEffect(0, -20, 10); break;
                case "サイバー・グリッチ・ドーナツ":
                    if (this.castId.equals("NENE")) {
                        this.syncRate += 35; this.waveNoise -= 20; // 圧倒的な甘味で全肯定脳がバグる
                    } else {
                        this.syncRate += 10;
                    }
                    break;
            }
        }
        // 0〜100のガード
        this.sleepDebt = Math.max(0, Math.min(100, this.sleepDebt));
        this.waveNoise = Math.max(0, Math.min(100, this.waveNoise));
        this.syncRate = Math.max(0, Math.min(100, this.syncRate));
    }

    private void totalCalculatedEffect(int s, int n, int sy) {
        this.sleepDebt += s; this.waveNoise += n; this.syncRate += sy;
    }

    public String getCastId() { return castId; }
    public int getSleepDebt() { return sleepDebt; }
    public int getWaveNoise() { return waveNoise; }
    public int getSyncRate() { return syncRate; }
}