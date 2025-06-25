package controller;

import model.CreditContract;
import model.CreditContracts;
import view.ClientCreditsView;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class ClientCreditsController {
    private final ClientCreditsView view;
    private final CreditContracts model;
    private final int clientId;

    public ClientCreditsController(ClientCreditsView view, CreditContracts model, int clientId) {
        this.view = view;
        this.model = model;
        this.clientId = clientId;
        update();
        model.addListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }

    private void update() {
        List<CreditContract> list = model.getAll();
        List<CreditContract> filteredList = new ArrayList<>();

        for (CreditContract c : list) {
            if (c.getClientId() == clientId) {
                filteredList.add(c);
            }
        }
        view.getCredits().setItems(FXCollections.observableArrayList(filteredList));
    }
}
