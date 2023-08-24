package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "komentarz")
public class Komentarz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_komentarz", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @NotNull
    @Column(name = "opis", nullable = false, length = Integer.MAX_VALUE)
    private String opis;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_events", nullable = false)
    private Event idEvents;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private Person idPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kom")
    private Komentarz idKom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Event getIdEvents() {
        return idEvents;
    }

    public void setIdEvents(Event idEvents) {
        this.idEvents = idEvents;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    public Komentarz getIdKom() {
        return idKom;
    }

    public void setIdKom(Komentarz idKom) {
        this.idKom = idKom;
    }

}