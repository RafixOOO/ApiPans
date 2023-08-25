package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_persons", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "imie", nullable = false, length = Integer.MAX_VALUE)
    private String imie;

    @NotNull
    @Column(name = "nazwisko", nullable = false, length = Integer.MAX_VALUE)
    private String nazwisko;

    @NotNull
    @Column(name = "emailconfirm", nullable = false)
    private Integer emailconfirm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getEmailconfirm() {
        return emailconfirm;
    }

    public void setEmailconfirm(Integer emailconfirm) {
        this.emailconfirm = emailconfirm;
    }

}