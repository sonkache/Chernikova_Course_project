package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private final TextField loginField = new TextField();
    private final PasswordField passField = new PasswordField();
    private final Button bLogin = new Button("Войти");
    private final Button bRegister = new Button("Регистрация");

    public void start(Stage stage) {
        Label lblLogin = new Label("Логин:");
        Label lblPassword = new Label("Пароль:");

        String labelStyle = "-fx-text-fill: #000000; -fx-font-size: 16px; -fx-font-style: italic;";
        lblLogin   .setStyle(labelStyle);
        lblPassword.setStyle(labelStyle);

        String fieldStyle = "-fx-border-color: #0277bd; -fx-border-width: 1; -fx-background-color: white; -fx-font-size: 14px;";
        loginField.setStyle(fieldStyle);
        passField.setStyle(fieldStyle);

        String btnStyle = " -fx-background-color: white; -fx-border-color: #0277bd; -fx-border-width: 2; -fx-text-fill: #0277bd; -fx-font-size: 14px; -fx-font-style: italic;";
        bLogin.setStyle(btnStyle);
        bRegister.setStyle(btnStyle);

        HBox buttonsBox = new HBox(10, bLogin, bRegister);
        buttonsBox.setStyle("-fx-alignment: center;");

        VBox root = new VBox(10, lblLogin, loginField, lblPassword, passField, buttonsBox);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #e1f5fe);");

        Scene scene = new Scene(root, 400, 350);
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
    }

    public TextField getLogin(){ return loginField; }
    public PasswordField getPassField(){ return passField; }
    public Button getLoginButton(){ return bLogin; }
    public Button getRegisterButton(){ return bRegister; }

    public void error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK).showAndWait();
    }
}
