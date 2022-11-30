package dtos;

import entities.Favorites;

public class FavoritesDTO {
    int id;
    int userid;
    String slug;
    public FavoritesDTO(Favorites favorites){
        this.id = favorites.getId();
        this.userid = favorites.getUserid();
        this.slug = favorites.getSlug();
    }
    public Favorites toFavorite(){
        return new Favorites(this.userid,this.slug);
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
