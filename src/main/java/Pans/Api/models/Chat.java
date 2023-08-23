package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_chat", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "text", nullable = false, length = Integer.MAX_VALUE)
    private String text;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_kola", nullable = false)
    private Kolo idKola;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private Person idPerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Kolo getIdKola() {
        return idKola;
    }

    public void setIdKola(Kolo idKola) {
        this.idKola = idKola;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

}