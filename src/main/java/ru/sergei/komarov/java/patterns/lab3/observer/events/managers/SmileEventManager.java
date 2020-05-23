package ru.sergei.komarov.java.patterns.lab3.observer.events.managers;

import ru.sergei.komarov.java.patterns.lab3.observer.events.listeners.SmileEventListener;
import ru.sergei.komarov.java.patterns.lab3.observer.models.ObservableSmile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmileEventManager {

    Map<String, List<SmileEventListener>> listeners = new HashMap<>();

    public SmileEventManager(String... operations) {
        for (String operation : operations) {
            listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, SmileEventListener eventListener) {
        List<SmileEventListener> listenersByType = listeners.get(eventType);
        if (listenersByType != null) {
            listenersByType.add(eventListener);
        }
    }

    public void unsubscribe(String eventType, SmileEventListener eventListener) {
        List<SmileEventListener> listenersByType = listeners.get(eventType);
        if (listenersByType != null) {
            listenersByType.remove(eventListener);
        }
    }

    public void notify(String eventType, ObservableSmile state) {
        List<SmileEventListener> listenersByType = listeners.get(eventType);
        if (listenersByType != null) {
            for (SmileEventListener listener : listenersByType) {
                listener.update(eventType, state);
            }
        }
    }
}
