package Pans.Api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "kolo_user")
public class KoloUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kolo_user", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "events_adder", nullable = false)
    private Boolean eventsAdder = false;

    @NotNull
    @Column(name = "manage_user", nullable = false)
    private Boolean manageUser = false;

    @NotNull
    @Column(name = "permission_admin", nullable = false)
    private Boolean permissionAdmin = false;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "kolo_id_kola", nullable = false)
    private Kolo koloIdKola;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id_user", nullable = false)
    private User userIdUser;

    @NotNull
    @Column(name = "chat_writer", nullable = false)
    private Boolean chatWriter = false;

    @NotNull
    @Column(name = "confirm", nullable = false)
    private Boolean confirm = false;

    @Column(name = "badges")
    private Integer badges;

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public Integer getBadges() {
        return badges;
    }

    public void setBadges(Integer badges) {
        this.badges = badges;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEventsAdder() {
        return eventsAdder;
    }

    public void setEventsAdder(Boolean eventsAdder) {
        this.eventsAdder = eventsAdder;
    }

    public Boolean getManageUser() {
        return manageUser;
    }

    public void setManageUser(Boolean manageUser) {
        this.manageUser = manageUser;
    }

    public Boolean getPermissionAdmin() {
        return permissionAdmin;
    }

    public void setPermissionAdmin(Boolean permissionAdmin) {
        this.permissionAdmin = permissionAdmin;
    }

    public Kolo getKoloIdKola() {
        return koloIdKola;
    }

    public void setKoloIdKola(Kolo koloIdKola) {
        this.koloIdKola = koloIdKola;
    }

    public User getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(User userIdUser) {
        this.userIdUser = userIdUser;
    }

    public Boolean getChatWriter() {
        return chatWriter;
    }

    public void setChatWriter(Boolean chatWriter) {
        this.chatWriter = chatWriter;
    }

}