package ru.appline.logic;

import java.io.Serializable;
import java.util.*;

public class CardinalPoints implements Serializable {
    private static final CardinalPoints instance = new CardinalPoints();
    private final Map<String, int[]> points;

    private CardinalPoints() {
        points = new HashMap<>();
    }

    public static CardinalPoints getInstance() {
        return instance;
    }

//    {
//        "Nortn": "316-45",
//        "East":  "46-135",
//        "South": "136-225",
//        "West": "226-315"
//    }

    public void initPoints(Map<String, String> coords) {

        for (Map.Entry<String, String> entry : coords.entrySet()) {
            int[] arr = new int[0];
            String[] coords2 = entry.getValue().split("-");
            int a = Integer.parseInt(coords2[0]);
            int b = Integer.parseInt(coords2[1]);

            if (a > b) {
                arr = new int[a-b+2];
                int count = 0;
                for (; a >= b; a--) {
                    arr[count] = a;
                    count++;
                }

            } else if (a < b) {
                arr = new int[b-a+2];
                int count = 0;
                for (;b >= a; b--) {
                    arr[count] = b;
                    count++;
                }
            }
            points.put(entry.getKey(), arr);
            }

        }

//    В качестве GET-запроса необходимо реализовать вывод стороны света по запрашиваемому градусу, например:
//    Запрос:
//    {
//        "Degree": 56
//    }
//    Ответ:
//    {
//        "Side": "East"
//    }
    public Map<String, String> getPoint(int id) {
        Map<String, String> message = new HashMap<>();
        message.put("Side", "Nothing found!");

        for (Map.Entry<String, int[]> entry : points.entrySet()) {
            for (int k: entry.getValue()) {
                if (k == id) {
                    message.put("Side", entry.getKey());
                }
            }
        }

        return message;

    }

    public Map<String, int[]> getPoints() {
        return points;
    }
}
