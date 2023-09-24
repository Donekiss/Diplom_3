package generator;

public class Customer {
    private String email;

    private String password;
    private String name;
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Customer withEmail(String email){
        this.email = email;
        return this;
    }
    public Customer withPassword(String password){
        this.password = password;
        return this;
    }
    public Customer withName(String name){
        this.name = name;
        return this;
    }
    public Customer() {
    }

    public Customer(String email, String name, String password) {
    }
}
