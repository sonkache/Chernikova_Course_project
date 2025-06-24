package model;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private final List<User> users = new ArrayList<>();
    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    public void add(User user) {
        users.add(user);
        notifyListeners();
    }

    public void setAll(List<User> user) {
        users.clear();
        users.addAll(user);
        notifyListeners();
    }

    public List<User> getAll() {
        return new ArrayList<>(users);
    }
}
