package com.example.demo; // ★これを1行目に追加！
public class BrewedDrink {
    private String name;        // ドリンク名（例：強制同期オム・ラテ）
    private int odLevel;        // 依存度
    private int sweetLevel;     // 脳溶け度
    private int crazyLevel;     // 狂気度

    public BrewedDrink(String name, int odLevel, int sweetLevel, int crazyLevel) {
        this.name = name;
        this.odLevel = odLevel;
        this.sweetLevel = sweetLevel;
        this.crazyLevel = crazyLevel;
    }

    // Getters
    public String getName() { return name; }
    public int getOdLevel() { return odLevel; }
    public int getSweetLevel() { return sweetLevel; }
    public int getCrazyLevel() { return crazyLevel; }
}