package app.model.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private String role;

    public User() {}

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getSalt() {
        return this.salt;
    }

    public String getRole() {return this.role;}
}
