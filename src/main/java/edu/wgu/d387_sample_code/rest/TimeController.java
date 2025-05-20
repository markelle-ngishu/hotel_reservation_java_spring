package edu.wgu.d387_sample_code.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/time")
@CrossOrigin
public class TimeController {

    private static final String START_TIME = "05:30PM EST";
    private static final String TIME_FORMAT = "hh:mma";
    private static final List<String> TIME_ZONES = Arrays.asList("EST", "MST", "UTC");
    private final SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);

    @GetMapping("/presentation")
    public ResponseEntity<List<String>> fetchPresentationTimes() {
        List<String> presentationTimes = new ArrayList<>();

        try {
            Date parsedTime = formatter.parse(START_TIME);

            TIME_ZONES.forEach(zone -> {
                formatter.setTimeZone(TimeZone.getTimeZone(zone));
                String formattedTime = formatter.format(parsedTime);
                presentationTimes.add(formattedTime);
            });

        } catch (Exception e) {
            System.err.println("Time parsing error: " + e.getMessage());
            presentationTimes.add("Error: " + e.getMessage());
        }

        return ResponseEntity.ok(presentationTimes);
    }
}
