package controller;

import model.CreditContracts;
import view.ClientCreditsView;

public class ClientCreditsController {
    private final ClientCreditsView view;
    private final CreditContracts contractsModel;

    public ClientCreditsController(ClientCreditsView view, CreditContracts contractsModel, int clientId) {
        this.view = view;
        this.contractsModel = contractsModel;
        contractsModel.addListener(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
        update();
    }

    private void update() {
        view.setCredits(contractsModel.getAll());
    }
}
