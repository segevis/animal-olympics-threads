package Competitions;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class TournamentThread implements Runnable {

    private final Scores scores; // מחזיק את התוצאות הסופיות של כל קבוצה
    private final AtomicBoolean startSignal; // דגל מיוחד המתחיל את כל החיות
    private final int groups; // מספר הקבוצות המתחרות

    public TournamentThread(Scores scores, AtomicBoolean startSignal, int groups) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
    }

    @Override
    public void run() {

        synchronized (startSignal) {
            startSignal.set(true); // התחלת כל החיות על ידי הגדרת הדגל ל-true
            startSignal.notifyAll(); // מתחיל את כל התחרות
        }

        while (scores.getAll().size() < groups) {

            Map<String, Date> currentScores = scores.getAll();

            try {
                Thread.sleep(1000); // המתנה בין עדכונים
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
