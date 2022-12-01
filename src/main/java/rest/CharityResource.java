package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CharityDTO;
import dtos.NonProfitDTO;
import entities.Blacklist;
import facades.BlacklistFacade;
import javassist.NotFoundException;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Path("charity")
public class CharityResource {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BlacklistFacade FACADE = BlacklistFacade.getBlacklistFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{category}")
    public String getAllFromCategory(@PathParam("category") String category) throws IOException, NotFoundException {
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/" + category + "?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
        NonProfitDTO nonProfitDTO = GSON.fromJson(nonprofit, NonProfitDTO.class);
        NonProfitDTO sendThis = FACADE.removeBlacklistedItems(nonProfitDTO);
        return GSON.toJson(sendThis);
    }

  @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{category}+{slug}")
    public String blacklistCharity(@PathParam("category") String category, @PathParam("slug") String slug) throws IOException, NotFoundException {
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/" + category + "?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=5");
        NonProfitDTO nonProfitDTO = GSON.fromJson(nonprofit, NonProfitDTO.class);
        Blacklist blacklist = new Blacklist(slug);
        FACADE.blacklistCharity(blacklist);
        NonProfitDTO sendThis = FACADE.removeBlacklistedItems(nonProfitDTO);
      return GSON.toJson(sendThis);
    }

}
