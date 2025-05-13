package app.model.user;

import jakarta.persistence.*;
import lombok.Getter;

import app.model.authorization.Role;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(nullable = false)
    private String history;

    @Override
    public String toString() {
        return STR."User{id=\{id}, firstName='\{firstName}', lastName='\{lastName}', email='\{email}', password='\{password}', phoneNumber=\{phoneNumber}, salt='\{salt}', role='\{role}'}";
    }
}