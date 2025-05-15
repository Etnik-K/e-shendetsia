package app.model.admin;

import app.model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "admin_table")
public class Admin {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}