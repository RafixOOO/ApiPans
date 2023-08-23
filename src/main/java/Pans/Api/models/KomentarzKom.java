package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "komentarz_kom")
public class KomentarzKom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_komentarz", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_komentarz", nullable = false)
    private Komentarz komentarz;

    @NotNull
    @Column(name = "id_kom", nullable = false)
    private Integer idKom;

    @NotNull
    @Column(name = "opis", nullable = false, length = Integer.MAX_VALUE)
    private String opis;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private Person idPerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Komentarz getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(Komentarz komentarz) {
        this.komentarz = komentarz;
    }

    public Integer getIdKom() {
        return idKom;
    }

    public void setIdKom(Integer idKom) {
        this.idKom = idKom;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

}