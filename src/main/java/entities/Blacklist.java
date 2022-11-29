package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Blacklist")
public class Blacklist {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "blacklist_id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "blacklistedSlug")
    private String blacklistedSlug;

    public Blacklist() {
    }

    public Blacklist(String blacklistedSlug){
        this.blacklistedSlug = blacklistedSlug;
    }

    public int getId() {
        return id;
    }

    public String getBlacklistedSlug() {
        return blacklistedSlug;
    }
}
