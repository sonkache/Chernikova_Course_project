package model;

import java.util.ArrayList;
import java.util.List;

public class CreditTypes {
    private final List<CreditType> creditTypes = new ArrayList<>();
    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    public void add(CreditType creditType) {
        creditTypes.add(creditType);
        notifyListeners();
    }

    public void setAll(List<CreditType> creditType) {
        creditTypes.clear();
        creditTypes.addAll(creditType);
        notifyListeners();
    }

    public List<CreditType> getAll() {
        return new ArrayList<>(creditTypes);
    }
}
