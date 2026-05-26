package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ScenarioEngine {
    private List<DialogueFrame> timeline = new ArrayList<>();
    private int currentPointer = 0;

    public ScenarioEngine() {
        // === 🔋 DAY 01：あむ / AMU (INDEX 0〜6) ===
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「…はろー。ねぇマスター、聞いてる？ あむの脳内のタイムライン、さっきから謎の文字列で埋め尽くされてるの……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「これ絶対誰かの嫌がらせ。…ねぇ、いつもの『脳溶け』が強いやつ。あれ飲ませて、脳みそミュートにして？」", "#ff2a74"));
        timeline.add(new DialogueFrame("wait_drink", 1, "RANK 01", "あむ / AMU", "WAITING", "（あむは虚ろな目でカップを見つめている。ドリンクを要求している…）", "#ff2a74"));
        
        // 分岐ルートA：大成功 (INDEX 3〜4)
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「…ん、とろとろしてきた。頭のなかのパチパチするノイズ、マスターの液体が全部消してくれたみたい……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「ありがと、マスター。これで明日の昼のメン地下アイドルの対バンライブ、笑顔で乗り切れる気がする……バイバイ」", "#ff2a74"));
        
        // 分岐ルートB：失敗 (INDEX 5〜6)
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「……なにこれ。こんな濁った泥水じゃ外界の電波遮断できないよ。マスター、あむの脳みそがハッキングされて壊れても、どうでもいいんだ……？」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「もういい。今日のチェキの売り上げで別の怪しいクスリ買うから。マスターのバカ。出禁にしてあげる」", "#ff2a74"));

        // === ⚡ DAY 02：りの / RINO (INDEX 7〜14) ===
        // 導入パート
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「マスターおはよーっ！ピピッと22世紀からダウンリンク完了！…って、うそ。今のナシ。脳内のタスクマネージャーが真っ赤っか！あはは！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「なんか昨日変なパケット受信しちゃってさー、メモリリークが止まんないの！脳みそがアツアツのグラタンになっちゃいそう！」", "#00f3ff"));
        timeline.add(new DialogueFrame("wait_drink", 2, "RANK 02", "りの / RINO", "WAITING", "「ねーマスター、脳波をギュインギュインに過充電（オーバークロック）させる超高周波な電波ドリンク、1杯おなしゃす！」", "#00f3ff"));

        // 分岐ルートA：りの大成功 (INDEX 10〜11)
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "OVERCLOCKED", "「キ、キタキタキタキタァーーーッ！！！脳細胞が120GHzで駆動してる！！宇宙の果てのソースコードまで全部読めるよぉ！！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "OVERCLOCKED", "「最高だよマスター！バグってたメモリが全部電波に変換されて飛んでった！お礼にりのの秘密の暗号キー、教えてあげるね！」", "#00f3ff"));

        // 分岐ルートB：りの失敗 (INDEX 12〜13)
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "CRASHED", "「あれ、あれれ……？ 画面が、真っ暗……。システムカーネルが……シャット、ダウン……しちゃ……う……」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "CRASHED", "「ブルースクリーン、綺麗だなあ……。マスター、りのを壊した責任、ちゃんと取ってよね……？ ぷつん」", "#00f3ff"));

        // 体験版終了 (INDEX 14)
        timeline.add(new DialogueFrame("end", 2, "SYSTEM", "END", "FINISHED", "MAID_CORE Alpha-Version のログはここで途切れているようだ。お給仕お疲れ様でした。", "#555555"));
    }

    public DialogueFrame getCurrentFrame() {
        if (currentPointer >= timeline.size()) {
            return timeline.get(timeline.size() - 1);
        }
        return timeline.get(currentPointer);
    }

    public void advance() {
        if (currentPointer == 4 || currentPointer == 6) {
            currentPointer = 7; // あむの結末から2日目（りの）へ合流
        } else if (currentPointer == 11 || currentPointer == 12) {
            currentPointer = 14; // りのの結末からエンディングへ
        } else {
            currentPointer++;
        }
    }

    public void routeBranch(String result) {
        if (currentPointer < 7) {
            // 1日目（あむ）の分岐
            if (result.equals("SUCCESS")) currentPointer = 3;
            else currentPointer = 5;
        } else {
            // 2日目（りの）の分岐
            if (result.equals("SUCCESS")) currentPointer = 10;
            else currentPointer = 12;
        }
    }
}