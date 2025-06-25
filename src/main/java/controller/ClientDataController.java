package controller;

import model.Clients;
import view.ClientDataView;

public class ClientDataController {
    private final ClientDataView view;
    private final Clients model;
    private final int clientId;

    public ClientDataController(ClientDataView view, Clients model, int clientId) {
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
        for (int i = 0; i < model.getAll().size(); i++) {
            if (model.getAll().get(i).getId() == clientId) {
                var client = model.getAll().get(i);
                view.setName(client.getName());
                view.setProperty(client.getPropertyType());
                view.setAddress(client.getAddress());
                view.setPhone(client.getPhoneNumber());
                view.setContact(client.getContactPerson());
                break;
            }
        }
    }
}
