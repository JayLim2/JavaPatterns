package ru.sergei.komarov.java.patterns.lab3.observer.events.listeners;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.sergei.komarov.java.patterns.lab3.observer.models.ObservableSmile;

import java.util.Objects;

public class SmileNoseStateListener implements SmileEventListener {

    private Rectangle nose;

    public SmileNoseStateListener(Rectangle nose) {
        this.nose = nose;
    }

    @Override
    public void update(String eventType, ObservableSmile state) {
        if (nose == null) {
            throw new NullPointerException("JavaFX components must not be null.");
        }

        if (Objects.equals("nose", eventType) && state != null) {
            String colorName = state.getNoseColor();
            Color color;
            switch (colorName) {
                case "red":
                    color = Color.rgb(255, 0, 0);
                    break;
                case "indigo":
                    color = Color.rgb(75, 0, 130);
                    break;
                case "yellow":
                    color = Color.rgb(255, 242, 0);
                    break;
                case "gray":
                default:
                    color = Color.rgb(127, 127, 127);
            }
            nose.setFill(color);
        }
    }
}
