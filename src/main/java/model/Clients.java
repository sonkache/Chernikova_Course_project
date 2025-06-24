package model;

import java.util.ArrayList;
import java.util.List;

public class Clients {
    private final List<Client> clients = new ArrayList<>();
    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    public void add(Client client) {
        clients.add(client);
        notifyListeners();
    }

    public void setAll(List<Client> client) {
        clients.clear();
        clients.addAll(client);
        notifyListeners();
    }

    public List<Client> getAll() {
        return new ArrayList<>(clients);
    }
}
