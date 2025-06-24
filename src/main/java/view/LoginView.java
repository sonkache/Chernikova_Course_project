package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private TextField login = new TextField();
    private PasswordField password = new PasswordField();
    private Button bLogin = new Button("Войти");
    private Button bRegister = new Button("Регистрация");

    public void start(Stage stage) {
        HBox hbox = new HBox(10, bLogin, bRegister);
        VBox root = new VBox(10, new Label("Логин:"), login, new Label("Пароль:"), password, hbox);
        root.setPadding(new javafx.geometry.Insets(20));
        stage.setTitle("Авторизация");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    public TextField getLogin(){
        return login;
    }

    public PasswordField getPassField(){
        return password;
    }

    public Button getLoginButton(){
        return bLogin;
    }

    public Button getRegisterButton(){
        return bRegister;
    }

    public void error(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
