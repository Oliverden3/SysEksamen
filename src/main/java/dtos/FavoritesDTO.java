package dtos;

import entities.Favorites;
import entities.User;

import java.util.ArrayList;
import java.util.List;

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

    public static List<FavoritesDTO> getFavoriteDTOs(List<Favorites> favorites) {
        List<FavoritesDTO> favoritesDTOList = new ArrayList<>();
        favorites.forEach(favorite -> {
            favoritesDTOList.add(new FavoritesDTO(favorite));
        });
        return favoritesDTOList;
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
