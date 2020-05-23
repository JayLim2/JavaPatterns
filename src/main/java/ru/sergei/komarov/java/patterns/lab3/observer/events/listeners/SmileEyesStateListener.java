package ru.sergei.komarov.java.patterns.lab3.observer.events.listeners;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import ru.sergei.komarov.java.patterns.lab3.observer.models.ObservableSmile;

public class SmileEyesStateListener implements SmileEventListener {

    private Circle leftEye;
    private Circle leftEyePupil;
    private Circle rightEye;
    private Circle rightEyePupil;
    private Line closedLeftEye;
    private Line closedRightEye;

    public SmileEyesStateListener(Circle leftEye, Circle leftEyePupil,
                                  Circle rightEye, Circle rightEyePupil,
                                  Line closedLeftEye, Line closedRightEye) {

        this.leftEye = leftEye;
        this.leftEyePupil = leftEyePupil;
        this.rightEye = rightEye;
        this.rightEyePupil = rightEyePupil;
        this.closedLeftEye = closedLeftEye;
        this.closedRightEye = closedRightEye;
    }

    @Override
    public void update(String eventType, ObservableSmile state) {
        if (leftEye == null || leftEyePupil == null || closedLeftEye == null) {
            throw new NullPointerException("JavaFX components (for Left Eye) must not be null.");
        }

        if (rightEye == null || rightEyePupil == null || closedRightEye == null) {
            throw new NullPointerException("JavaFX components (for Right Eye) must not be null.");
        }

        if (eventType != null && state != null) {
            boolean isLeftEyeOpened = state.isLeftEyeOpened();
            boolean isRightEyeOpened = state.isRightEyeOpened();
            switch (eventType) {
                case "leftEye":
                    leftEye.setVisible(isLeftEyeOpened);
                    leftEyePupil.setVisible(isLeftEyeOpened);
                    closedLeftEye.setVisible(!isLeftEyeOpened);
                    break;
                case "rightEye":
                    rightEye.setVisible(isRightEyeOpened);
                    rightEyePupil.setVisible(isRightEyeOpened);
                    closedRightEye.setVisible(!isRightEyeOpened);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported event type exception. Event type: " + eventType);
            }
        }
    }

}
