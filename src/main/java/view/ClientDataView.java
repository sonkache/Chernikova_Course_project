package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ClientDataView {
    private final GridPane root;
    private final TextField nameField;
    private final TextField propertyField;
    private final TextField addressField;
    private final TextField phoneField;
    private final TextField contactField;
    private final Button saveButton;

    public ClientDataView() {
        root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        ColumnConstraints labelCol = new ColumnConstraints();
        labelCol.setHgrow(Priority.NEVER);
        ColumnConstraints inputCol = new ColumnConstraints();
        inputCol.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().addAll(labelCol, inputCol);

        int row = 0;
        root.add(new Label("Имя:"), 0, row);
        nameField = new TextField();
        root.add(nameField, 1, row++);

        root.add(new Label("Тип имущества:"), 0, row);
        propertyField = new TextField();
        root.add(propertyField, 1, row++);

        root.add(new Label("Адрес:"), 0, row);
        addressField = new TextField();
        root.add(addressField, 1, row++);

        root.add(new Label("Телефон:"), 0, row);
        phoneField = new TextField();
        root.add(phoneField, 1, row++);

        root.add(new Label("Контактное лицо:"), 0, row);
        contactField = new TextField();
        root.add(contactField, 1, row++);

        saveButton = new Button("Сохранить");
        root.add(saveButton, 1, row);
    }

    public Parent getData() {
        return root;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public String getName() {
        return nameField.getText();
    }

    public String getProperty() {
        return propertyField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public String getContact() {
        return contactField.getText();
    }

    public void setName(String value) {
        nameField.setText(value);
    }

    public void setProperty(String value) {
        propertyField.setText(value);
    }

    public void setAddress(String value) {
        addressField.setText(value);
    }

    public void setPhone(String value) {
        phoneField.setText(value);
    }

    public void setContact(String value) {
        contactField.setText(value);
    }

    public void showMessage(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}