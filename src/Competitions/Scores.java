package Competitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {

    private final Map<String, Date> scores = Collections.synchronizedMap(new HashMap<>());

    public void add(String name) {
        scores.put(name, new Date()); // מוסיף את שם החיה עם הזמן הנוכחי
    }

    public Map<String, Date> getAll() {
        return scores;
    }
}