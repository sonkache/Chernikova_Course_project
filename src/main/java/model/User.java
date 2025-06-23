package model;

public class User {
    private int id;
    private int clientId;
    private String login;
    private String password;
    private String role;

    public User(int id, int clientId, String login, String password, String role) {
        this.id = id;
        this.clientId = clientId;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getClientId(){
        return clientId;
    }
    public void setClientId(int clientId){
        this.clientId = clientId;
    }

    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }
}
