import java.util.List;

public class GameEngine {

    public String processServing(DrinkRequest request) {
        String castId = request.getCastId();
        List<String> ingredients = request.getIngredients();

        // 1. まず投入された3つの素材から「カスのドリンク」を醸造する
        BrewedDrink drink = brew(ingredients);
        
        System.out.println("【醸造完了】提供品: " + drink.getName() + " [OD:" + drink.getOdLevel() + "]");

        // 2. キャストごとに、そのドリンクを飲んだ反応を分岐
        if ("AMU".equals(castId)) {
            return judgeAmu(drink);
        }
        
        return "「……窓の外から、知らないバイナリが視線送ってきてる」";
    }

    // 本家コーヒートーク風のレシピ・ネーミング辞書
    private BrewedDrink brew(List<String> ing) {
        if (ing == null || ing.size() < 3) {
            return new BrewedDrink("ただの産業廃棄物", 0, 0, 0);
        }

        String base = ing.get(0);
        String main = ing.get(1);
        String top = ing.get(2);

        // レシピ判定パターン1：ドス黒いハーブ×2 ＋ 睡眠薬 ＝ 『遮断用・毒電波ハーブティー』
        if ("ドス黒いハーブ".equals(base) && "ドス黒いハーブ".equals(main) && "安価な睡眠薬".equals(top)) {
            return new BrewedDrink("遮断用・毒電波ハーブティー", 4, 1, 6);
        }

        // レシピ判定パターン2：ケチャップ×2 ＋ 睡眠薬 ＝ 『強制同期オム・ラテ』
        if ("同期用ケチャップ".equals(base) && "同期用ケチャップ".equals(main) && "安価な睡眠薬".equals(top)) {
            return new BrewedDrink("強制同期オム・ラテ", 5, 6, 2);
        }

        // レシピ判定パターン3：睡眠薬×3 ＝ 『オーバードーズ・ナイトメア』
        if ("安価な睡眠薬".equals(base) && "安価な睡眠薬".equals(main) && "安価な睡眠薬".equals(top)) {
            return new BrewedDrink("オーバードーズ・ナイトメア", 10, 0, 5);
        }

        // 該当しないカスの組み合わせ
        return new BrewedDrink("濁った謎のドブ泥水", 2, 2, 2);
    }

    // あむの個別シナリオ判定
    private String judgeAmu(BrewedDrink drink) {
        // あむの欲しかった「遮断用・毒電波ハーブティー」だった場合
        if ("遮断用・毒電波ハーブティー".equals(drink.getName())) {
            return "「…ん、これ。頭のなかのパチパチが消えた。すりガラスの向こう側に行ったみたいに、静か……。ねぇマスター、あむをずっとこのカウンターの檻の中に閉じ込めておいてね？」";
        }

        // 睡眠薬を限界まで盛られた場合
        if (drink.getOdLevel() >= 10) {
            return "「……あ、れ……？ 視界に強い赤色のグリッチが……。マスター、あむ、同期解除されちゃう……バイバ、イ……」（あむの脳波ロストを確認。GAME OVER）";
        }

        // それ以外の適当なものを出された場合
        return "「『" + drink.getName() + "』？ なにこれ。こんな濁った泥水じゃ外界の電波遮断できないよ。マスター、あむの脳みそがハッキングされて壊れても、どうでもいいんだ……？」";
    }
}