package ru.sergei.komarov.java.patterns.lab3.observer.events.listeners;

import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import ru.sergei.komarov.java.patterns.lab3.observer.models.ObservableSmile;

import java.util.Objects;

public class SmileMouthStateListener implements SmileEventListener {

    private Line straightMouth;
    private QuadCurve smiledMouth;

    public SmileMouthStateListener(Line straightMouth, QuadCurve smiledMouth) {
        this.straightMouth = straightMouth;
        this.smiledMouth = smiledMouth;
    }

    @Override
    public void update(String eventType, ObservableSmile state) {
        if (straightMouth == null || smiledMouth == null) {
            throw new NullPointerException("JavaFX components must not be null.");
        }

        if (Objects.equals("mouth", eventType) && state != null) {
            boolean isSmiled = state.isSmiled();
            straightMouth.setVisible(!isSmiled);
            smiledMouth.setVisible(isSmiled);
        }
    }
}
