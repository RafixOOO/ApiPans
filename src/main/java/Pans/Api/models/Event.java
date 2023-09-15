package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_event", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @NotNull
    @Column(name = "miejsce", nullable = false, length = Integer.MAX_VALUE)
    private String miejsce;

    @NotNull
    @Column(name = "nazwa", nullable = false, length = Integer.MAX_VALUE)
    private String nazwa;

    @NotNull
    @Column(name = "opis", nullable = false, length = Integer.MAX_VALUE)
    private String opis;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_kolo", nullable = false)
    private Kolo idKolo;

    @Column(name = "image", length = Integer.MAX_VALUE)
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Kolo getIdKolo() {
        return idKolo;
    }

    public void setIdKolo(Kolo idKolo) {
        this.idKolo = idKolo;
    }

}