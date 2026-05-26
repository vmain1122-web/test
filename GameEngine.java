import java.util.List;

public class GameEngine {

    public String processServing(DrinkRequest request) {
        String castId = request.getCastId();
        List<String> mix = request.getIngredients();

        if ("AMU".equals(castId)) {
            return judgeAmu(mix);
        } else if ("RINO".equals(castId)) {
            return judgeRino(mix);
        }
        
        return "「……誰これ。知らないパケット」";
    }

    private String judgeAmu(List<String> mix) {
        long sleepingPills = mix.stream().filter(i -> i.equals("格安睡眠導入剤")).count();
        boolean hasKetchup = mix.contains("同期用ケチャップ");

        // 大成功：精神が一時的に安定（共依存ルート）
        if (sleepingPills >= 2 && hasKetchup) {
            return "「…あは、脳みそがトロトロしてきた。マスターの顔が2人にみえる……。これなら、明日のクソみたいな外の世界の現実も、全部耐えられる気がする……」";
        }
        
        // オーバードーズ：致死量（バッドエンド・出禁）
        if (sleepingPills == 3) {
            return "「……あ、れ……？ 視界が、真っ黒、に……。マスター……あむ、もう、起きられないかも……」（あむはそのままカウンターに倒れ伏した。救急車のサイレンがネオ秋葉原に響く。GAME OVER）";
        }

        // 失敗：ただのカス
        return "「ちょっと、睡眠薬が足りないんだけど。あむを眠らせないでどうする気？ 正気でオタクの相手しろってこと？ 鬼、悪魔、非実在青少年……」";
    }

    private String judgeRino(List<String> mix) {
        // りの（Cyber-Maid-X）用の判定ロジックもここに拡張可能
        return "「マスター、脳のニューロンバグらせて、一緒に22世紀まで飛ばしちゃお？」";
    }
}