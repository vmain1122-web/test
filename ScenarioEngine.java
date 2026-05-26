package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ScenarioEngine {
    private List<DialogueFrame> timeline = new ArrayList<>();
    private int currentPointer = 0;

    public ScenarioEngine() {
        // === 🔋 DAY 01：あむ / AMU (INDEX 0〜7) ===
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「…はろー。ねぇマスター、聞いてる？ あむの脳内のタイムライン、さっきから謎の文字列で埋め尽くされてるの……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「これ絶対メン地下のオタクの生霊。あむのアンチスレのログが、脳幹に直接ダウンリンクされてるみたいで頭が割れそう……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「…ねぇ、いつもの『脳溶け』が強いやつ作って。…えっとね、安価な睡眠薬のあのザラザラした苦味をベースとメインにたっぷり入れて……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "SEVERE_SLEEP_DEPRIVATION", "「でも、それだけだとただの泥水だから。トッピングにドス黒いハーブを添えて、脳みそを優しくミュートにしてほしいな……？」", "#ff2a74"));
        timeline.add(new DialogueFrame("wait_drink", 1, "RANK 01", "あむ / AMU", "WAITING", "（あむは虚ろな目でカップを見つめている。【睡眠薬×2 ＋ ハーブ×1】の『ディープスリープ・ブレンド』を求めているようだ…）", "#ff2a74"));
        
        // 分岐ルートA：大成功 (INDEX 5〜6)
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「…ん、これこれ。喉を通るこの不穏な苦味……頭のなかのパチパチするネットのノイズが、マスターの液体で全部溶けていく……」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "STABLE", "「完璧。最高に脳が甘やかされてる。これで明日の対バンライブ、最前列のキモ・オタクにも笑顔で対応できる気がする。ありがと、バイバイ」", "#ff2a74"));
        
        // 分岐ルートB：失敗 (INDEX 7〜8)
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「……なによこれ。苦味のバランスが全然足りないし、脳みそが全然ミュートにならない。マスター、あむが電波で壊れてもどうでもいいんだ？」", "#ff2a74"));
        timeline.add(new DialogueFrame("talk", 1, "RANK 01", "あむ / AMU", "ANGRY", "「もういい。今日のチェキ代で闇市に行って、もっと致死量の高いクスリ買ってくるから。マスターのバカ。出禁にしてあげる」", "#ff2a74"));

        // === ⚡ DAY 02：りの / RINO (INDEX 9〜17) ===
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「マスターおはよーっ！ピピッと22世紀からダウンリンク完了！…って、うそ。今のナシ！あはは、脳内のタスクマネージャーが真っ赤っか！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「なんか昨日、裏路地で怪しいWi-Fiに変なパケット受信しちゃってさー！ゴミデータが溢れてメモリリークが止まんないの！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「このままだとCPUが熱暴走して、りのの脳みそがアツアツのドリアになっちゃう！だから、脳波を限界突破（オーバークロック）させて！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "MEMORY_LEAKING", "「高周波ノイズ液をこれでもかってくらいガッツリ2回注いで、仕上げに同期用ケチャップを混ぜて、電子回路をマスターのハートに強制同期させてよ！」", "#00f3ff"));
        timeline.add(new DialogueFrame("wait_drink", 2, "RANK 02", "りの / RINO", "WAITING", "「ねーマスター！脳波がギュインギュインになる特製の『サイバー・バグ・サイダー』、1杯おなしゃす！！」", "#00f3ff"));

        // 分岐ルートA：りの大成功 (INDEX 14〜15)
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "OVERCLOCKED", "「キ、キタキタキタキタァーーーッ！！！脳細胞が120GHzで駆動してる！！宇宙の果てのソースコードまで脳裏に直接ストリーミングされてるよぉ！！」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "OVERCLOCKED", "「最高だよマスター！バグってたメモリが全部綺麗に電波に変換されて飛んでった！お礼にりのの秘密の暗号キー（秘密鍵）、パケット通信で送っとくね！」", "#00f3ff"));

        // 分岐ルートB：りの失敗 (INDEX 16〜17)
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "CRASHED", "「あれ、あれれ……？ 視界にノイズが走って、画面が真っ暗……。システムカーネルが……シャット、ダウン、しちゃ……う……」", "#00f3ff"));
        timeline.add(new DialogueFrame("talk", 2, "RANK 02", "りの / RINO", "CRASHED", "「エラーコード：404。ブルースクリーンって、ネオンみたいで綺麗だなあ……。マスター、りのを壊した責任、ちゃんと取ってよね……？ ぷつん」", "#00f3ff"));

        // 体験版終了 (INDEX 18)
        timeline.add(new DialogueFrame("end", 2, "SYSTEM", "END", "FINISHED", "MAID_CORE Alpha-Version のログはここで途切れているようだ。お給仕お疲れ様でした。", "#555555"));
    }

    public DialogueFrame getCurrentFrame() {
        if (currentPointer >= timeline.size()) {
            return timeline.get(timeline.size() - 1);
        }
        return timeline.get(currentPointer);
    }

    public void advance() {
        // ストーリー増量に伴い、分岐後の合流ポインタを修正
        if (currentPointer == 6 || currentPointer == 8) {
            currentPointer = 9;  // 1日目の結末から2日目の導入（りの）へ合流
        } else if (currentPointer == 15 || currentPointer == 17) {
            currentPointer = 18; // 2日目の結末からエンディングへ
        } else {
            currentPointer++;
        }
    }

    public void routeBranch(String result) {
        if (currentPointer < 9) {
            // 1日目（あむ）の分岐先
            if (result.equals("SUCCESS")) currentPointer = 5;
            else currentPointer = 7;
        } else {
            // 2日目（りの）の分岐先
            if (result.equals("SUCCESS")) currentPointer = 14;
            else currentPointer = 16;
        }
    }
}