package UserListPortlet.portlet;

public class User {
    private String id;
    private String name;
    private String surname1;
    private String surname2;
    private String email;

    public User(String id, String name, String surname1, String surname2, String email) {
        this.id = id;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public String getEmail() {
        return email;
    }
}
