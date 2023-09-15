package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "kolo")
public class Kolo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kola", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nazwa", nullable = false, length = Integer.MAX_VALUE)
    private String nazwa;

    @NotNull
    @Column(name = "opis", nullable = false, length = Integer.MAX_VALUE)
    private String opis;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status = false;

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

}