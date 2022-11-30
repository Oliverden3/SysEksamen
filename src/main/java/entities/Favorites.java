package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Favorites")
public class Favorites {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "favorites_id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "slug")
    private String slug;
    public Favorites() {}

    public Favorites(int userid, String slug) {
        this.userid = userid;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getSlug() {
        return slug;
    }
}