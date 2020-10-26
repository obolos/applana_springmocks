package ru.appline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.appline.logic.CardinalPoints;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class CompasController {
    private static final CardinalPoints cardinalPoints = CardinalPoints.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/initPoints", consumes = "application/json", produces = "application/json")
    public Map<String, int[]> initCardinalPoints(@RequestBody Map<String, String> coords) {
        cardinalPoints.initPoints(coords);
        return cardinalPoints.getPoints();
    }

    @GetMapping(value = "/getPoint", consumes = "application/json", produces = "application/json")
    public Map<String, String> getPoint(@RequestBody Map<String, Integer> point) {
        return cardinalPoints.getPoint(point.get("Degree"));
    }
}
