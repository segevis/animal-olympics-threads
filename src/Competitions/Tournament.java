package Competitions;

import Animals.Animal;
import Graphics.CompetitionPanel;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Tournament {

    protected TournamentThread tournamentThread;
    protected CompetitionPanel competitionPanel;

    public Tournament(Animal[][] animals, CompetitionPanel panel) {
        this.competitionPanel = panel;
        setup(animals, panel);
    }

    protected abstract void setup(Animal[][] animals, CompetitionPanel panel);

    public TournamentThread getTournamentThread() {
        return tournamentThread;
    }

}
