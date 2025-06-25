package view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClientDataView {
    private VBox root = new VBox(10);
    private Label name = new Label();
    private Label property = new Label();
    private Label address = new Label();
    private Label phone = new Label();
    private Label contact = new Label();

    public ClientDataView() {
        root.setPadding(new javafx.geometry.Insets(20));

        HBox nameBox = new HBox(10, new Label("Имя:"), this.name);
        nameBox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");

        HBox propertyBox = new HBox(10, new Label("Тип собственности:"), this.property);
        propertyBox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");

        HBox addressBox = new HBox(10, new Label("Адрес:"), this.address);
        addressBox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");

        HBox phoneBox = new HBox(10, new Label("Телефон:"), this.phone);
        phoneBox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");

        HBox contactBox = new HBox(10, new Label("Контактное лицо:"), this.contact);
        contactBox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");

        root.getChildren().addAll(nameBox, propertyBox, addressBox, phoneBox, contactBox);

        root.setStyle("-fx-background-color: #e1f5fe; -fx-font-size: 16px; -fx-font-weight: bold;");
    }

    public Node getData() {
        return root;
    }

    public void setName(String s) {
        name.setText(s);
    }

    public void setProperty(String s) {
        property.setText(s);
    }

    public void setAddress(String s) {
        address.setText(s);
    }

    public void setPhone(String s) {
        phone.setText(s);
    }

    public void setContact(String s) {
        contact.setText(s);
    }
}
