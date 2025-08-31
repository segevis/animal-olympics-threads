package Competitions;

import Animals.Animal;
import Mobility.Point;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {

    private final Animal participant;
    private final double neededDistance;
    private final AtomicBoolean startFlag;
    private final AtomicBoolean finishFlag;

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag, AtomicBoolean finishFlag) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {

        while (!startFlag.get()) {
            synchronized (startFlag) {
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        double realDistance = 0;

        while (true) {
            synchronized (participant) {
                participant.move(); // ניעת החיה קדימה
                participant.getPanel().repaint();
                realDistance += participant.getSpeed();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (realDistance >= neededDistance - (participant.getSize() * 2)) {
                break;
            }
        }
        synchronized (finishFlag) {
            finishFlag.set(true);
            finishFlag.notify();
        }
    }
}
