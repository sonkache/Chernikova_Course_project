package model;

import java.util.ArrayList;
import java.util.List;

public class CreditContracts {
    private final List<CreditContract> creditContracts = new ArrayList<>();
    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    public void add(CreditContract creditContract) {
        creditContracts.add(creditContract);
        notifyListeners();
    }

    public void setAll(List<CreditContract> creditContract) {
        creditContracts.clear();
        creditContracts.addAll(creditContract);
        notifyListeners();
    }

    public List<CreditContract> getAll() {
        return new ArrayList<>(creditContracts);
    }


}
