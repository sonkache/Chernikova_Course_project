package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterView {
    private final TextField nameField = new TextField();
    private final TextField propertyField = new TextField();
    private final TextField addressField = new TextField();
    private final TextField phoneField = new TextField();
    private final TextField contactField = new TextField();
    private final TextField loginField = new TextField();
    private final PasswordField passField = new PasswordField();
    private final Button bSubmit = new Button("Зарегистрироваться");

    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #e1f5fe);");

        Label[] labels = {new Label("Имя/Название:"), new Label("Тип собственности:"), new Label("Адрес:"), new Label("Телефон:"), new Label("Контактное лицо:"), new Label("Логин:"), new Label("Пароль:")};
        String labelStyle = "-fx-text-fill: #000000; -fx-font-size: 16px; -fx-font-style: italic;";
        for (Label lbl : labels) {
            lbl.setStyle(labelStyle);
        }

        grid.add(labels[0],0,0);
        grid.add(nameField,1,0);
        grid.add(labels[1],0,1);
        grid.add(propertyField,1,1);
        grid.add(labels[2],0,2);
        grid.add(addressField,1,2);
        grid.add(labels[3],0,3);
        grid.add(phoneField,1,3);
        grid.add(labels[4],0,4);
        grid.add(contactField,1,4);
        grid.add(labels[5],0,5);
        grid.add(loginField,1,5);
        grid.add(labels[6],0,6);
        grid.add(passField,1,6);
        grid.add(bSubmit,1,7);

        String fieldStyle = "-fx-font-size: 16px; -fx-border-color: #0277bd; -fx-border-width: 1; -fx-background-color: white;";
        nameField.setStyle(fieldStyle);
        propertyField.setStyle(fieldStyle);
        addressField.setStyle(fieldStyle);
        phoneField.setStyle(fieldStyle);
        contactField.setStyle(fieldStyle);
        loginField.setStyle(fieldStyle);
        passField.setStyle(fieldStyle);
        String btnStyle = "-fx-background-color: white;-fx-border-color: #0277bd; -fx-border-width: 2; -fx-text-fill: #0277bd;-fx-font-size: 14px; -fx-font-style: italic;";
        bSubmit.setStyle(btnStyle);

        Scene scene = new Scene(grid, 450, 450);
        stage.setTitle("Регистрация");
        stage.setScene(scene);
        stage.show();
    }

    public TextField getNameField(){ return nameField; }
    public TextField getPropertyField(){ return propertyField; }
    public TextField getAddressField(){ return addressField; }
    public TextField getPhoneField(){ return phoneField; }
    public TextField getContactField(){ return contactField; }
    public TextField getLoginField(){ return loginField; }
    public PasswordField getPassField(){ return passField; }
    public Button getSubmitButton(){ return bSubmit; }

    public void showMessage(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK).showAndWait();
    }
}
