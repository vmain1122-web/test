package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private final ScenarioEngine scenarioEngine = new ScenarioEngine();
    private final GameEngine gameEngine = new GameEngine(scenarioEngine);
    
    // 👥 各キャストのステータス管理（りのはノイズ100%で参戦！）
    private final CastStatus amuStatus = new CastStatus("AMU", 80, 60, 20);
    private final CastStatus rinoStatus = new CastStatus("RINO", 30, 100, 10);
    
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
        // 現在のタイムラインの進捗から、どちらのキャストにお給仕しているか判断する
        DialogueFrame currentFrame = scenarioEngine.getCurrentFrame();
        CastStatus activeStatus = currentFrame.getDay() == 1 ? amuStatus : rinoStatus;
        
        // ジャッジ実行
        gameEngine.processServing(activeStatus, ingredients);
        
        // 判定された結末から売上金を精算
        DialogueFrame nextFrame = scenarioEngine.getCurrentFrame();
        if (nextFrame.getStatus().equals("STABLE") || nextFrame.getStatus().equals("OVERCLOCKED")) {
            this.earnedToday = 2000; // 大成功！
        } else {
            this.earnedToday = 1000; // 最低保証！
        }
        
        this.wallet += this.earnedToday;
        return buildResponse();
    }

    private GameStateResponse buildResponse() {
        DialogueFrame currentFrame = scenarioEngine.getCurrentFrame();
        CastStatus activeStatus = currentFrame.getDay() == 1 ? amuStatus : rinoStatus;

        return new GameStateResponse(
            currentFrame,
            activeStatus.getSleepDebt(),
            activeStatus.getWaveNoise(),
            activeStatus.getSyncRate(),
            this.wallet,
            this.earnedToday
        );
    }

    public static class GameStateResponse {
        public DialogueFrame frame;
        public int sleepDebt;
        public int waveNoise;
        public int syncRate;
        public int wallet;
        public int earnedToday;

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