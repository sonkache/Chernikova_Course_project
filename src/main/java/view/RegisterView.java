package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterView {
    private TextField nameField = new TextField();
    private TextField propertyField = new TextField();
    private TextField addressField = new TextField();
    private TextField phoneField = new TextField();
    private TextField contactField = new TextField();
    private TextField loginField = new TextField();
    private PasswordField passField = new PasswordField();
    private Button bSubmit = new Button("Зарегистрироваться");

    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setPadding(new javafx.geometry.Insets(20));

        grid.add(new Label("Имя/Название:"),0, 0);
        grid.add(nameField,1, 0);
        grid.add(new Label("Тип собственности:"),0, 1);
        grid.add(propertyField,1, 1);
        grid.add(new Label("Адрес:"),0, 2);
        grid.add(addressField,1, 2);
        grid.add(new Label("Телефон:"),0, 3);
        grid.add(phoneField,1, 3);
        grid.add(new Label("Контактное лицо:"),0, 4);
        grid.add(contactField,1, 4);
        grid.add(new Label("Логин:"),0, 5);
        grid.add(loginField,1, 5);
        grid.add(new Label("Пароль:"),0, 6);
        grid.add(passField,1, 6);
        grid.add(bSubmit,1, 7);

        stage.setTitle("Регистрация");
        stage.setScene(new Scene(grid, 400, 400));
        stage.show();
    }

    public TextField getNameField(){
        return nameField;
    }

    public TextField getPropertyField(){
        return propertyField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public TextField getPhoneField(){
        return phoneField;
    }

    public TextField getContactField(){
        return contactField;
    }

    public TextField getLoginField(){
        return loginField;
    }

    public PasswordField getPassField(){
        return passField;
    }

    public Button getSubmitButton(){
        return bSubmit;
    }

    public void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
