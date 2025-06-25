package controller;

import model.CreditType;
import model.CreditTypes;
import view.ClientCreditTypesView;
import javafx.collections.FXCollections;
import java.util.List;

public class ClientCreditTypesController {
    private final ClientCreditTypesView view;
    private final CreditTypes model;

    public ClientCreditTypesController(ClientCreditTypesView view, CreditTypes model) {
        this.view = view;
        this.model = model;
        update();
        model.addListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }
    private void update() {
        List<CreditType> list = model.getAll();
        view.getTypes().setItems(FXCollections.observableArrayList(list));
    }
}
