package app.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int phoneNumber;

    public User() {}

    public User(String name, String email, String password, String surname, int phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    Long getId(){
        return id;
    }
    String getName(){
        return name;
    }
    String getSurname(){
        return surname;
    }
    String getEmail(){
        return email;
    }
    int getPhoneNumber(){
        return phoneNumber;
    }
}
