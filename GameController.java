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
    
    private int wallet = 0; 
    private int earnedToday = 0;
    private String unlockedItem = "NONE"; // 🛒 ショップで購入したアイテムを記憶 ("NONE", "FILTER", "DONUT")

    @GetMapping("/state")
    public GameStateResponse getCurrentState() {
        return buildResponse(new BrewedDrink("-", 0, 0, 0));
    }

    @PostMapping("/advance")
    public GameStateResponse advanceText() {
        scenarioEngine.advance();
        return buildResponse(new BrewedDrink("-", 0, 0, 0));
    }

    @PostMapping("/serve")
    public GameStateResponse serveDrink(@RequestBody List<String> ingredients) {
        DialogueFrame currentFrame = scenarioEngine.getCurrentFrame();
        CastStatus activeStatus = currentFrame.getDay() == 1 ? amuStatus : rinoStatus;
        
        gameEngine.processServing(activeStatus, ingredients);
        
        DialogueFrame nextFrame = scenarioEngine.getCurrentFrame();
        if (nextFrame.getStatus().equals("STABLE") || nextFrame.getStatus().equals("OVERCLOCKED")) {
            this.earnedToday = 2000;
        } else {
            this.earnedToday = 1000;
        }
        
        this.wallet += this.earnedToday;
        return buildResponse(gameEngine.getLastBrewedDrink());
    }

    // 🛒 新設：ショップで購入処理を実行するAPI
    @PostMapping("/buy")
    public GameStateResponse buyItem(@RequestBody Map<String, String> request) {
        String item = request.get("item");
        if (this.wallet >= 1500) {
            this.wallet -= 1500;
            this.unlockedItem = item; // "FILTER" または "DONUT"
        }
        return buildResponse(new BrewedDrink("-", 0, 0, 0));
    }

    private GameStateResponse buildResponse(BrewedDrink drink) {
        DialogueFrame currentFrame = scenarioEngine.getCurrentFrame();
        CastStatus activeStatus = currentFrame.getDay() == 1 ? amuStatus : rinoStatus;

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

    public static class GameStateResponse {
        public DialogueFrame frame;
        public int sleepDebt;
        public int waveNoise;
        public int syncRate;
        public int wallet;
        public int earnedToday;
        public String drinkName;
        public int bitterness;
        public int sweetness;
        public int cyberPulse;
        public String unlockedItem; // 📤 HTMLへ引き渡す購入状態パケット

        public GameStateResponse(DialogueFrame frame, int sleep, int noise, int sync, int wallet, int earned, String dName, int b, int s, int c, String item) {
            this.frame = frame;
            this.sleepDebt = sleep;
            this.waveNoise = noise;
            this.syncRate = sync;
            this.wallet = wallet;
            this.earnedToday = earned;
            this.drinkName = dName;
            this.bitterness = b;
            this.sweetness = s;
            this.cyberPulse = c;
            this.unlockedItem = item;
        }
    }
}