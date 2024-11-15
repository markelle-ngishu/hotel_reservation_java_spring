package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.locale.ReadWelcome;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/resources")
@CrossOrigin
public class ResourcesController {

    // Create 2 executors
    private Executor ex = Executors.newFixedThreadPool(2);

    @GetMapping("welcome")
    public ResponseEntity<List<String>>getWelcomeMessage() {
        List<String> msgs = new ArrayList<String>();

        // Read en_US
        ex.execute(() -> {
            ReadWelcome readEN = new ReadWelcome("en", "US");
            msgs.add(readEN.getWelcomeMessage());
        });

        ex.execute(() -> {
            ReadWelcome readFR = new ReadWelcome("fr", "CA");
            msgs.add(readFR.getWelcomeMessage());
        });

        return ResponseEntity.ok(msgs);

    }
}
