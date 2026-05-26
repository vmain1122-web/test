package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private final ScenarioEngine scenarioEngine = new ScenarioEngine();
    private final CastStatus amuStatus = new CastStatus("AMU", 80, 60, 20);
    private final GameEngine gameEngine = new GameEngine(scenarioEngine);

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
        gameEngine.processServing(amuStatus, ingredients);
        return buildResponse();
    }

    private GameStateResponse buildResponse() {
        return new GameStateResponse(
            scenarioEngine.getCurrentFrame(),
            amuStatus.getSleepDebt(),
            amuStatus.getWaveNoise(),
            amuStatus.getSyncRate()
        );
    }

    // ★クラスの「内側」に配置し直すことで、Javaが確実に見つけられるように修正
    public static class GameStateResponse {
        public DialogueFrame frame;
        public int sleepDebt;
        public int waveNoise;
        public int syncRate;

        public GameStateResponse(DialogueFrame frame, int sleep, int noise, int sync) {
            this.frame = frame;
            this.sleepDebt = sleep;
            this.waveNoise = noise;
            this.syncRate = sync;
        }
    }
}