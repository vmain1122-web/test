package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

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
            drink.getCyberPulse()
        );
    }

    public static class GameStateResponse {
        public DialogueFrame frame;
        public int sleepDebt;
        public int waveNoise;
        public int syncRate;
        public int wallet;
        public int earnedToday;
        // 📥 追加された味覚データパケット
        public String drinkName;
        public int bitterness;
        public int sweetness;
        public int cyberPulse;

        public GameStateResponse(DialogueFrame frame, int sleep, int noise, int sync, int wallet, int earned, String dName, int b, int s, int c) {
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
        }
    }
}