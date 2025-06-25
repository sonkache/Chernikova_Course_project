package view;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;

public class AdminClientsView {
    private final TableView<Client> clients = new TableView<>();
    public AdminClientsView() {

        TableColumn<Client, Integer> id = new TableColumn<>("Номер клиента");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Client, String> name = new TableColumn<>("Название/Имя");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Client, String> property = new TableColumn<>("Собственность");
        property.setCellValueFactory(new PropertyValueFactory<>("propertyType"));

        TableColumn<Client, String> address = new TableColumn<>("Адрес");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        clients.getColumns().addAll(id, name, property, address);
    }

    public Node getClient() {
        return clients;
    }

    public TableView<Client> getClients() {
        return clients;
    }
}
