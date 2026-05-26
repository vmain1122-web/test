package com.example.demo; // ★これを1行目に追加！
public class DialogueFrame {
    private String type;        // "talk" (通常会話) または "wait_drink" (調合待ち)
    private int day;            // 日付 (1 = 1日目)
    private String castId;      // "RANK 01" など
    private String name;        // "あむ / AMU"
    private String status;      // メイドの精神状態
    private String text;        // セリフ本文
    private String uiColor;     // メイドごとのテーマカラーコード (#a30000 など)

    public DialogueFrame(String type, int day, String castId, String name, String status, String text, String uiColor) {
        this.type = type;
        this.day = day;
        this.castId = castId;
        this.name = name;
        this.status = status;
        this.text = text;
        this.uiColor = uiColor;
    }

    // Getters (JSON変換に必要)
    public String getType() { return type; }
    public int getDay() { return day; }
    public String getCastId() { return castId; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getText() { return text; }
    public String getUiColor() { return uiColor; }
}