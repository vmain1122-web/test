package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ScenarioEngine {
    private List<DialogueFrame> timeline = new ArrayList<>();
    private int currentPointer = 0;

    public ScenarioEngine() {
        // === DAY 01：あむ / AMU (INDEX 0〜8) ===
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「…はろー。ねぇマスター、聞いてる？ あむの脳内のタイムライン、さっきから謎の文字列で埋め尽くされてるの……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「これ絶対メン地下のオタクの生霊。あむのアンチスレのログが、脳幹に直接ダウンリンクされてるみたいで頭が割れそう……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「…ねぇ、いつもの『脳溶け』が強いやつ作って。…えっとね、安価な睡眠薬のあのザラザラした苦味をベースとメインにたっぷり入れて……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「微調整のトッピングにドス黒いハーブを添えて、脳みそを優しくミュートにしてほしいな……？」", "#ff2a74"));
        timeline.add(new DialogueFrame("wait_drink", 1, "RANK 01", "あむ / AMU", "WAITING", "（あむは虚ろな目でカップを見つめている。【睡眠薬×2 ＋ ハーブ×1】を求めているようだ…）", "#ff2a74"));
        // 成功A
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「…ん、これこれ。頭のなかのパチパチするネットのノイズが、マスターの液体で全部溶けていく……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「完璧。ありがと、バイバイ。明日のチェキ会も笑顔で乗り切るね」", "#ff2a74"));
        // 失敗B
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「……なによこれ。脳みそが全然ミュートにならない。マスターのバカ。出禁にしてあげる」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「もういい。別の怪しいクスリ買って帰るから」", "#ff2a74"));

        // === DAY 02：りの / RINO (INDEX 9〜17) ===
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「マスターおはよーっ！ピピッと22世紀からダウンリンク完了！…って、うそ。タスクマネージャーが真っ赤っか！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「ゴミデータが溢れてメモリリークが止まんないの！脳みそがアツアツのドリアになっちゃう！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「だから、高周波ノイズ液をこれでもかってくらいガッツリ2回注いで！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「仕上げに同期用ケチャップを混ぜて、電子回路をマスターのハートに強制同期させてよ！」", "#00f3ff"));
        timeline.add(new DialogueFrame("wait_drink", 2, "RANK 02", "りの / RINO", "WAITING", "「ねーマスター！脳波がギュインギュインになる特製の『サイバー・バグ・サイダー』、1杯おなしゃす！！」", "#00f3ff"));
        // 成功A
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "OVERCLOCKED", "「キ、キタキタキタキタァーーーッ！！！脳細胞が120GHzで駆動してる！！宇宙のソースコードまで全部ストリーミングされてるよぉ！！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "OVERCLOCKED", "「お礼にりのの秘密の暗号キー、パケット通信で送っとくね！ばいばーい！」", "#00f3ff"));
        // 失敗B
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "CRASHED", "「あれ、あれれ……？ 視界にノイズが走って、システムカーネルが……シャット、ダウン、しちゃ……う……」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "CRASHED", "「エラーコード：404。マスター、りのを壊した責任、ちゃんと取ってよね……？ ぷつん」", "#00f3ff"));

        // === 🧪 DAY 03：ねね / NENE (INDEX 18〜26) ===
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "EMPTY_HEART", "「おかえりなさい、マスター。ふふ、今日もたくさんお仕事頑張って、心も身体もボロボロですね……可哀想に……」", "#ffe600"));
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "EMPTY_HEART", "「廃墟式少女庵の夜はね、現実の辛いタスクなんて誰も責めたりしません。ねねがマスターの全部、ぜーんぶ肯定して溶かしてあげますから」", "#ffe600"));
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "EMPTY_HEART", "「…でもね、みんなの心を吸い尽くして全肯定してると、ねねの心もすっからかんに乾いちゃうんです。だからね……」", "#ffe600"));
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "EMPTY_HEART", "「ねねの脳みそも、ドロドロの甘いお砂糖で満たしてほしいな。同期用ケチャップを3つこれでもかってくらい真っ赤に注ぎ込むか……」", "#ffe600"));
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "EMPTY_HEART", "「もし1日目の夜に闇ショップで『🍩グリッチドーナツ』を買ってあるなら、それをベースに仕込んで、致死量の甘味ハックをねねにキメさせて？」", "#ffe600"));
        timeline.add(new DialogueFrame("wait_drink", 3, "RANK 03", "ねね / NENE", "WAITING", "（ねねは妖しく微笑んでカップを見つめている。【ケチャップ×3】または【ドーナツ】を絡めた圧倒的甘味を欲している…）", "#ffe600"));
        // 成功A (INDEX 24〜25)
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "TOXIC_SWEET", "「あああ、頭の芯までお砂糖が染み渡る……あまくて、幸せ……脳細胞が全部、全肯定の海に溶けていっちゃう……」", "#ffe600"));
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "TOXIC_SWEET", "「ねぇマスター、もう明日もお仕事行かなくていいよ？ ねねの優しさの猛毒だけで、一生ベッドの中で生きていこうね……？ ふふ」", "#ffe600"));
        // 失敗B (INDEX 26〜27)
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "BROKEN_HEART", "「……なにこれ、全然甘くない。苦くて冷たいよ。ねねに『現実を生きろ』って突き放すつもり……？ 酷いよマスター……」", "#ffe600"));
        timeline.add(new DialogueFrame("talk", 3, "RANK 03", "ねね / NENE", "BROKEN_HEART", "「マスターの自己肯定感、全部吸い尽くして干からびさせてあげる。もう優しくなんて、してあげない。バイバイ」", "#ffe600"));

        // 体験版終了 (INDEX 28)
        timeline.add(new DialogueFrame("end", 3, "SYSTEM", "END", "FINISHED", "MAID_CORE 裏庭ポータルアーカイブのログはここで途切れているようだ。お給仕お疲れ様でした。", "#555555"));
    }

    public DialogueFrame getCurrentFrame() {
        if (currentPointer >= timeline.size()) {
            return timeline.get(timeline.size() - 1);
        }
        return timeline.get(currentPointer);
    }

    public void advance() {
        if (currentPointer == 6 || currentPointer == 8) {
            currentPointer = 9;  // 1日目 ➔ 2日目
        } else if (currentPointer == 15 || currentPointer == 17) {
            currentPointer = 18; // 2日目 ➔ 3日目
        } else if (currentPointer == 25 || currentPointer == 27) {
            currentPointer = 28; // 3日目 ➔ エンディング
        } else {
            currentPointer++;
        }
    }

    public void routeBranch(String result) {
        if (currentPointer < 9) {
            if (result.equals("SUCCESS")) currentPointer = 5;
            else currentPointer = 7;
        } else if (currentPointer < 18) {
            if (result.equals("SUCCESS")) currentPointer = 14;
            else currentPointer = 16;
        } else {
            // 🎯 3日目（ねね）の分岐先
            if (result.equals("SUCCESS")) currentPointer = 24;
            else currentPointer = 26;
        }
    }
}