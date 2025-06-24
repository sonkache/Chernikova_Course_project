package model;

import java.util.ArrayList;
import java.util.List;

public class Payments {
    private final List<Payment> payments = new ArrayList<>();
    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    public void add(Payment payment) {
        payments.add(payment);
        notifyListeners();
    }

    public void setAll(List<Payment> payment) {
        payments.clear();
        payments.addAll(payment);
        notifyListeners();
    }

    public List<Payment> getAll() {
        return new ArrayList<>(payments);
    }
}
