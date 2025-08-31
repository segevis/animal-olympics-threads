package Competitions;

import Animals.Animal;
import Animals.TerrestrialAnimal;
import Graphics.CompetitionPanel;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament {

    public RegularTournament(Animal[][] animals, CompetitionPanel panel) {
        super(animals, panel);
    }

    @Override
    protected void setup(Animal[][] animals , CompetitionPanel panel) {

        AtomicBoolean startFlag = new AtomicBoolean(false);
        Scores scores = new Scores();
        int sizeOfLandRoute = (competitionPanel.getBackgroundImage().getWidth())*2 + competitionPanel.getBackgroundImage().getHeight()*2;
        AnimalThread animalThread;

        for (Animal[] group : animals)
        {
            AtomicBoolean finishFlag = new AtomicBoolean(false);

            if(group[0] instanceof TerrestrialAnimal && group.length == 1)
            {
                animalThread = new AnimalThread(group[0],sizeOfLandRoute, startFlag, finishFlag);
            }
            else
            {
                animalThread = new AnimalThread(group[0],competitionPanel.getBackgroundImage().getWidth() , startFlag, finishFlag);
            }
            new Thread(animalThread).start();
            String groupName = "Group_" + group.hashCode();
            Referee referee = new Referee(groupName, group[0].getName(), scores);
            new Thread(referee).start();
        }

        tournamentThread = new TournamentThread(scores, startFlag, animals.length);
        new Thread(tournamentThread).start();
    }
}
