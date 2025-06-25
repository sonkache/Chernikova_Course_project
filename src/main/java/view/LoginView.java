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


        root.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #e1f5fe);");

        Label loginLabel = (Label) root.getChildren().get(0);
        loginLabel.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 16px;");

        Label passwordLabel = (Label) root.getChildren().get(2);
        passwordLabel.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 16px;");

        bLogin.setStyle("-fx-background-color: white; -fx-text-fill: #0277bd; -fx-border-color: #0277bd; -fx-font-size: 14px;");
        bRegister.setStyle("-fx-background-color: white; -fx-text-fill: #0277bd; -fx-border-color: #0277bd; -fx-font-size: 14px;");

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
