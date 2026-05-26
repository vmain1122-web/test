package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private final ScenarioEngine scenarioEngine = new ScenarioEngine();
    private final GameEngine gameEngine = new GameEngine(scenarioEngine);
    
    private final CastStatus amuStatus = new CastStatus("AMU", 80, 60, 20);
    private final CastStatus rinoStatus = new CastStatus("RINO", 30, 100, 10);
    private final CastStatus neneStatus = new CastStatus("NENE", 50, 80, 20);
    
    private int wallet = 0; 
    private int earnedToday = 0;
    private String unlockedItem = "NONE";

    // 💾 セーブデータ（チェックポイント）保持用オブジェクト
    private SaveSnapshot daySnapshot = null;

    @GetMapping("/state")
    public GameStateResponse getCurrentState() {
        // 初回起動時（DAY 1の朝）、自動で最初のセーブポイントを作成
        if (daySnapshot == null) {
            makeCheckpoint();
        }
        return buildResponse(new BrewedDrink("-", 0, 0, 0));
    }

    @PostMapping("/advance")
    public GameStateResponse advanceText() {
        int previousDay = scenarioEngine.getCurrentFrame().getDay();
        
        scenarioEngine.advance();
        
        int currentDay = scenarioEngine.getCurrentFrame().getDay();
        // 📅 日付が切り替わった最初のタイミングで、その日の朝のチェックポイントを自動保存！
        if (previousDay != currentDay && currentDay <= 3) {
            makeCheckpoint();
        }
        
        return buildResponse(new BrewedDrink("-", 0, 0, 0));
    }

    @PostMapping("/serve")
    public GameStateResponse serveDrink(@RequestBody List<String> ingredients) {
        DialogueFrame currentFrame = scenarioEngine.getCurrentFrame();
        CastStatus activeStatus = getActiveStatus(currentFrame.getDay());
        
        gameEngine.processServing(activeStatus, ingredients);
        
        DialogueFrame nextFrame = scenarioEngine.getCurrentFrame();
        if (nextFrame.getStatus().equals("STABLE") || nextFrame.getStatus().equals("OVERCLOCKED") || nextFrame.getStatus().equals("TOXIC_SWEET")) {
            this.earnedToday = 2000;
        } else {
            this.earnedToday = 1000;
        }
        
        this.wallet += this.earnedToday;
        return buildResponse(gameEngine.getLastBrewedDrink());
    }

    @PostMapping("/buy")
    public GameStateResponse buyItem(@RequestBody Map<String, String> request) {
        String item = request.get("item");
        if (this.wallet >= 1500) {
            this.wallet -= 1500;
            this.unlockedItem = item;
        }
        return buildResponse(new BrewedDrink("-", 0, 0, 0));
    }

    // 💾 新設：現在の「日の始まり」にデータを巻き戻す（ロード機能）
    @PostMapping("/retry")
    public GameStateResponse retryDay() {
        if (this.daySnapshot != null) {
            this.scenarioEngine.setCurrentPointer(this.daySnapshot.pointer);
            this.wallet = this.daySnapshot.wallet;
            this.unlockedItem = this.daySnapshot.unlockedItem;
            
            // パラメータも朝の状態へ完全復元
            CastStatus activeStatus = getActiveStatus(this.daySnapshot.day);
            activeStatus.setSleepDebt(this.daySnapshot.sleepDebt);
            activeStatus.setWaveNoise(this.daySnapshot.waveNoise);
            activeStatus.setSyncRate(this.daySnapshot.syncRate);
            
            this.earnedToday = 0;
            System.out.println("【⌛ 時間逆行】DAY 0" + this.daySnapshot.day + " の朝に記憶を同期しました。");
        }
        return buildResponse(new BrewedDrink("-", 0, 0, 0));
    }

    // チェックポイントを作成する内部メソッド
    private void makeCheckpoint() {
        int day = scenarioEngine.getCurrentFrame().getDay();
        int pointer = scenarioEngine.getCurrentPointer();
        CastStatus status = getActiveStatus(day);
        
        this.daySnapshot = new SaveSnapshot(
            day, pointer, this.wallet, this.unlockedItem,
            status.getSleepDebt(), status.getWaveNoise(), status.getSyncRate()
        );
        System.out.println("【💾 自動セーブ】DAY 0" + day + " のチェックポイントを作成しました。");
    }

    private CastStatus getActiveStatus(int day) {
        if (day == 2) return rinoStatus;
        if (day == 3) return neneStatus;
        return amuStatus;
    }

    private GameStateResponse buildResponse(BrewedDrink drink) {
        DialogueFrame currentFrame = scenarioEngine.getCurrentFrame();
        CastStatus activeStatus = getActiveStatus(currentFrame.getDay());

        return new GameStateResponse(
            currentFrame,
            activeStatus.getSleepDebt(),
            activeStatus.getWaveNoise(),
            activeStatus.getSyncRate(),
            this.wallet,
            this.earnedToday,
            drink.getName(),
            drink.getBitterness(),
            drink.getSweetness(),
            drink.getCyberPulse(),
            this.unlockedItem
        );
    }

    // 💾 セーブデータを保持するためのスナップショットクラス
    private static class SaveSnapshot {
        int day; int pointer; int wallet; String unlockedItem;
        int sleepDebt; int waveNoise; int syncRate;

        SaveSnapshot(int d, int p, int w, String item, int s, int n, int sy) {
            this.day = d; this.pointer = p; this.wallet = w; this.unlockedItem = item;
            this.sleepDebt = s; this.waveNoise = n; this.syncRate = sy;
        }
    }

    public static class GameStateResponse {
        public DialogueFrame frame;
        public int sleepDebt; public int waveNoise; public int syncRate;
        public int wallet; public int earnedToday;
        public String drinkName; public int bitterness; public int sweetness; public int cyberPulse;
        public String unlockedItem;

        public GameStateResponse(DialogueFrame frame, int sleep, int noise, int sync, int wallet, int earned, String dName, int b, int s, int c, String item) {
            this.frame = frame; this.sleepDebt = sleep; this.waveNoise = noise; this.syncRate = sync;
            this.wallet = wallet; this.earnedToday = earned; this.drinkName = dName;
            this.bitterness = b; this.sweetness = s; this.cyberPulse = c; this.unlockedItem = item;
        }
    }
}