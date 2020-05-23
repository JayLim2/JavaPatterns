package ru.sergei.komarov.java.patterns.lab3.observer.events.listeners;

import ru.sergei.komarov.java.patterns.lab3.observer.models.ObservableSmile;

public interface SmileEventListener {
    void update(String eventType, ObservableSmile state);
}
