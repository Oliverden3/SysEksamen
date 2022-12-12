import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CharityDTO;
import dtos.DataDTO;
import dtos.FavoritesDTO;
import dtos.NonProfitDTO;
import entities.AllCategories;
import entities.Favorites;
import facades.UserFacade;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmallTest {
    public static void main(String[] args) throws IOException {
        final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
        final UserFacade FACADE = UserFacade.getUserFacade(EMF);
        final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        int id = 1;
        List<FavoritesDTO> favoritesList = FACADE.getAllFavoritesFromID(id);
        List<CharityDTO> getThese = new ArrayList<>();
        AllCategories allCategories = new AllCategories();
        for (FavoritesDTO f : favoritesList
        ) {
            for (String s: allCategories.getList()) {
                String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/" + s + "?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
                NonProfitDTO nonProfitDTO = GSON.fromJson(nonprofit, NonProfitDTO.class);
                for (CharityDTO c:nonProfitDTO.getNonprofits()) {
                    if (c.getSlug().equals(f.getSlug())){
                        getThese.add(c);
                    }
                }
            }
        }
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/pets?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
        NonProfitDTO nonProfitDTO =GSON.fromJson(nonprofit, NonProfitDTO.class);
        nonProfitDTO.setNonprofits(getThese);
        System.out.println(nonProfitDTO.getNonprofits().size());
    }
}
