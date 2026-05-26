package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private final ScenarioEngine scenarioEngine = new ScenarioEngine();
    private final CastStatus amuStatus = new CastStatus("AMU", 80, 60, 20);
    
    // 💵 お財布システムの追加
    private int wallet = 0; 
    private int earnedToday = 0;

    @GetMapping("/state")
    public GameStateResponse getCurrentState() {
        return buildResponse();
    }

    @PostMapping("/advance")
    public GameStateResponse advanceText() {
        scenarioEngine.advance();
        return buildResponse();
    }

    @PostMapping("/serve")
    public GameStateResponse serveDrink(@RequestBody List<String> ingredients) {
        // 淹れた中身をあむのステータスに反映
        amuStatus.applyIngredients(ingredients);
        
        // 要求を満たしているかジャッジ
        if (amuStatus.isAmuSatisfied()) {
            scenarioEngine.routeBranch("SUCCESS");
            this.earnedToday = 2000; // チェキ代＋ドリンク売上大成功！
        } else {
            scenarioEngine.routeBranch("FAILURE");
            this.earnedToday = 0;    // 怒られて売上ゼロ…
        }
        
        this.wallet += this.earnedToday; // お財布にガサ入れ
        return buildResponse();
    }

    private GameStateResponse buildResponse() {
        return new GameStateResponse(
            scenarioEngine.getCurrentFrame(),
            amuStatus.getSleepDebt(),
            amuStatus.getWaveNoise(),
            amuStatus.getSyncRate(),
            this.wallet,
            this.earnedToday
        );
    }

    public static class GameStateResponse {
        public DialogueFrame frame;
        public int sleepDebt;
        public int waveNoise;
        public int syncRate;
        public int wallet;       // 追加
        public int earnedToday;  // 追加

        public GameStateResponse(DialogueFrame frame, int sleep, int noise, int sync, int wallet, int earned) {
            this.frame = frame;
            this.sleepDebt = sleep;
            this.waveNoise = noise;
            this.syncRate = sync;
            this.wallet = wallet;
            this.earnedToday = earned;
        }
    }
}