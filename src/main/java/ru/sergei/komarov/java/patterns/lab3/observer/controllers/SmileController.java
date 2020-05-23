package ru.sergei.komarov.java.patterns.lab3.observer.controllers;

import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import ru.sergei.komarov.java.patterns.lab3.observer.events.listeners.SmileEventListener;
import ru.sergei.komarov.java.patterns.lab3.observer.events.listeners.SmileEyesStateListener;
import ru.sergei.komarov.java.patterns.lab3.observer.events.listeners.SmileMouthStateListener;
import ru.sergei.komarov.java.patterns.lab3.observer.events.listeners.SmileNoseStateListener;
import ru.sergei.komarov.java.patterns.lab3.observer.events.managers.SmileEventManager;
import ru.sergei.komarov.java.patterns.lab3.observer.models.ObservableSmile;

import static ru.sergei.komarov.java.patterns.lab3.observer.events.types.SmileEventTypes.*;

public class SmileController {

    @FXML
    private Circle leftEye;
    @FXML
    private Circle leftEyePupil;
    @FXML
    private Circle rightEye;
    @FXML
    private Circle rightEyePupil;
    @FXML
    private Line closedLeftEye;
    @FXML
    private Line closedRightEye;
    @FXML
    private Rectangle nose;
    @FXML
    private Line straightMouth;
    @FXML
    private QuadCurve smiledMouth;

    private ObservableSmile observableSmile;

    private SmileEventManager eventManager;

    @FXML
    public void initialize() {
        //Smile state
        observableSmile = new ObservableSmile(true, true, false, "indigo");

        //Smile events manager
        eventManager = new SmileEventManager(LEFT_EYE, RIGHT_EYE, NOSE, MOUTH);

        //Eye state listeners
        SmileEventListener eyeListener = new SmileEyesStateListener(leftEye, leftEyePupil, rightEye, rightEyePupil, closedLeftEye, closedRightEye);
        eventManager.subscribe(LEFT_EYE, eyeListener);
        eventManager.subscribe(RIGHT_EYE, eyeListener);

        //Nose state listeners
        SmileEventListener noseListener = new SmileNoseStateListener(nose);
        eventManager.subscribe(NOSE, noseListener);

        //Mouth state listeners
        SmileEventListener mouthListener = new SmileMouthStateListener(straightMouth, smiledMouth);
        eventManager.subscribe(MOUTH, mouthListener);
    }

    @FXML
    public void onEyeClicked(MouseEvent event) {
        EventTarget target = event.getTarget();
        boolean isLeftEye = target == leftEye || target == leftEyePupil || target == closedLeftEye;
        boolean isRightEye = target == rightEye || target == rightEyePupil || target == closedRightEye;
        observableSmile.setLeftEyeOpened(target == closedLeftEye);
        observableSmile.setRightEyeOpened(target == closedRightEye);
        if (isLeftEye) {
            update(LEFT_EYE, observableSmile);
        } else if (isRightEye) {
            update(RIGHT_EYE, observableSmile);
        }
    }

    @FXML
    public void onNoseClicked() {
        String color = observableSmile.getNoseColor();
        if (color != null && !color.isEmpty()) {
            switch (color) {
                case "red":
                    color = "indigo";
                    break;
                case "indigo":
                    color = "yellow";
                    break;
                case "yellow":
                    color = "red";
                    break;
                default:
                    color = "gray";
            }
            observableSmile.setNoseColor(color);
        }
        update(NOSE, observableSmile);
    }

    @FXML
    public void onMouthClicked() {
        observableSmile.setSmiled(!observableSmile.isSmiled());
        update(MOUTH, observableSmile);
    }

    private void update(String eventType, ObservableSmile state) {
        if (eventManager != null) {
            eventManager.notify(eventType, state);
        }
    }

}
