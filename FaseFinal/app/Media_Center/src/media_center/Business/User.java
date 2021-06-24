/*
 * DSS Media Center
 * Grupo 53
 */
package media_center.Business;

/**
 *
 * @author pacifico
 */
public class User {
    private String username;
    private String password;
    private String nome;
    private String email;
    private boolean is_admin;

    public User(String username, String password, String nome, String email, boolean is_admin) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.email = email;
        this.is_admin = is_admin;
    }
    
    public User() {
        this.username = "";
        this.password = "";
        this.nome = "";
        this.email = "";
        this.is_admin = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", nome=" + nome + ", email=" + email + ", is_admin=" + is_admin + '}';
    }

}
