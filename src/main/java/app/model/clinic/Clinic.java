package app.model.clinic;

import jakarta.persistence.*;

@Entity
@Table(name = "clinics")

public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String drejtori;

    @Column(nullable = false)
    public String address;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public int phone;

    @Column(nullable = false)
    public String website;

    Long getId(){
        return id;
    }
    public String getDrejtori(){
        return drejtori;
    }
    public String getAddress(){
        return address;
    }
    public String getEmail(){
        return email;
    }
    public int getPhone(){
        return phone;
    }
    public String getWebsite(){
        return website;
    }

    public Clinic(){}

    public Clinic(Long id, String drejtori, String address, String email, int phone, String website){
        this.id = id;
        this.drejtori = drejtori;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }
}
