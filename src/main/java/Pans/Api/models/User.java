package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private Person persons;

    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPersons() {
        return persons;
    }

    public void setPersons(Person persons) {
        this.persons = persons;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}