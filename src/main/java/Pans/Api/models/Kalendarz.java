package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "kalendarz")
public class Kalendarz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kalendarz", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_events", nullable = false)
    private Event idEvents;

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

}