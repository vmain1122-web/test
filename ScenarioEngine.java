package com.example.demo; // ★これを1行目に追加！
import java.util.ArrayList;
import java.util.List;

public class ScenarioEngine {
    private List<DialogueFrame> timeline = new ArrayList<>();
    private int currentPointer = 0;

    public ScenarioEngine() {
        // --- 1日目：導入パート ---
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「…はろー。ねぇマスター、聞いてる？ あむの脳内のタイムライン、さっきから謎の文字列で埋め尽くされてるの……」", "#a30000"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「これ絶対誰かの嫌がらせ。…ねぇ、いつもの『脳溶け』が強いやつ。あれ飲ませて、脳みそミュートにして？」", "#a30000"));
        timeline.add(new DialogueFrame("wait_drink", 1, "RANK 01", "あむ / AMU", "WAITING", "（あむは虚ろな目でカップを見つめている。ドリンクを要求している…）", "#a30000"));
        
        // --- 分岐ルートA：大成功（インデックス 3〜4） ---
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「…ん、とろとろしてきた。頭のなかのパチパチが消えた。すりガラスの向こう側に行ったみたいに、静か……」", "#a30000"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「ありがと、マスター。これで明日の昼のメン地下アイドルの対バンライブ、笑顔で乗り切れる気がする……バイバイ」", "#a30000"));
        
        // --- 分岐ルートB：失敗（インデックス 5〜6） ---
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「……なにこれ。こんな濁った泥水じゃ外界の電波遮断できないよ。マスター、あむの脳みそがハッキングされて壊れても、どうでもいいんだ……？」", "#a30000"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「もういい。今日のチェキの売り上げで別の怪しいクスリ買うから。マスターのバカ。出禁にしてあげる」", "#a30000"));

        // --- 共通イベント：2日目（インデックス 7〜） ---
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「マスターおはよーっ！ピピッと22世紀からダウンリンク完了！…って、うそ。今のナシ。脳内のタスクマネージャーが真っ赤っか！あはは！」", "#00a3a3"));
    }

    public DialogueFrame getCurrentFrame() {
        if (currentPointer >= timeline.size()) {
            return new DialogueFrame("end", 0, "SYSTEM", "END", "FINISHED", "体験版のログはここで途切れているようだ。", "#444444");
        }
        return timeline.get(currentPointer);
    }

    public void advance() {
        DialogueFrame current = getCurrentFrame();
        if ("wait_drink".equals(current.getType())) return;

        // 大成功（4）または失敗（6）のセリフを読み終えたら、2日目（7）へワープ
        if (currentPointer == 4 || currentPointer == 6) {
            currentPointer = 7;
        } else {
            currentPointer++;
        }
    }

    public void routeBranch(String result) {
        if ("SUCCESS".equals(result)) {
            currentPointer = 3;
        } else {
            currentPointer = 5;
        }
    }
}