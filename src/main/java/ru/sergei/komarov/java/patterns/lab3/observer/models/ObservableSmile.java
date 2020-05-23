package ru.sergei.komarov.java.patterns.lab3.observer.models;

public class ObservableSmile {

    private boolean isLeftEyeOpened;
    private boolean isRightEyeOpened;
    private boolean isSmiled;
    private String noseColor;

    public ObservableSmile(boolean isLeftEyeOpened, boolean isRightEyeOpened, boolean isSmiled,
                           String noseColor) {

        this.isLeftEyeOpened = isLeftEyeOpened;
        this.isRightEyeOpened = isRightEyeOpened;
        this.isSmiled = isSmiled;
        this.noseColor = noseColor;
    }

    public boolean isLeftEyeOpened() {
        return isLeftEyeOpened;
    }

    public void setLeftEyeOpened(boolean leftEyeOpened) {
        isLeftEyeOpened = leftEyeOpened;
    }

    public boolean isRightEyeOpened() {
        return isRightEyeOpened;
    }

    public void setRightEyeOpened(boolean rightEyeOpened) {
        isRightEyeOpened = rightEyeOpened;
    }

    public boolean isSmiled() {
        return isSmiled;
    }

    public void setSmiled(boolean smiled) {
        isSmiled = smiled;
    }

    public String getNoseColor() {
        return noseColor;
    }

    public void setNoseColor(String noseColor) {
        this.noseColor = noseColor;
    }
}
