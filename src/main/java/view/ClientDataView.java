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

        HBox name = new HBox(10, new Label("Имя:"), this.name);
        HBox property = new HBox(10, new Label("Тип собственности:"), this.property);
        HBox address = new HBox(10, new Label("Адрес:"), this.address);
        HBox phone = new HBox(10, new Label("Телефон:"), this.phone);
        HBox contact = new HBox(10, new Label("Контактное лицо:"), this.contact);
        root.getChildren().addAll(name, property, address, phone, contact);
    }

    public Node getData(){
        return root;
    }

    public void setName(String s){
        name.setText(s);
    }

    public void setProperty(String s){
        property.setText(s);
    }

    public void setAddress(String s){
        address.setText(s);
    }

    public void setPhone(String s){
        phone.setText(s);
    }

    public void setContact(String s){
        contact.setText(s);
    }
}
