package Competitions;

public class Referee implements Runnable {

    private final String groupName; // שם הקבוצה
    private final String animalName; // שם החיה
    private final Scores scores; // מצביע ל-Scores

    public Referee(String groupName, String animalName, Scores scores) {

        this.groupName = groupName;
        this.animalName = animalName;
        this.scores = scores;
    }

    @Override
    public void run() {

        synchronized (groupName)
        {
            try
            {
                groupName.wait(); // המתנה עד שהחיה תגיע לסיום
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }
            scores.add(animalName); // הוספת שם החיה לתוצאות
        }
    }
}