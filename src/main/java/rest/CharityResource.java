package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CharityDTO;
import dtos.NonProfitDTO;
import entities.Blacklist;
import dtos.DataDTO;
import facades.BlacklistFacade;
import javassist.NotFoundException;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;


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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{category}+{slug}")
    public String blacklistCharityFromCategory(@PathParam("category") String category, @PathParam("slug") String slug) throws IOException, NotFoundException {
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/" + category + "?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
        NonProfitDTO nonProfitDTO = GSON.fromJson(nonprofit, NonProfitDTO.class);
        Blacklist blacklist = new Blacklist(slug);
        FACADE.blacklistCharity(blacklist);
        NonProfitDTO sendThis = FACADE.removeBlacklistedItems(nonProfitDTO);
        return GSON.toJson(sendThis);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("ban+{slug}")
    public String blacklistCharity(@PathParam("slug") String slug) throws IOException, NotFoundException {
        String charity = HttpUtils.fetchData("https://partners.every.org/v0.2/nonprofit/"+slug+"?apiKey=2b719ff3063ef1714c32edbfdd7af870");
        CharityDTO charityDTO = GSON.fromJson(charity, CharityDTO.class);
        Blacklist blacklist = new Blacklist(slug);
        FACADE.blacklistCharity(blacklist);
        return GSON.toJson(charityDTO);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get+{slug}")
    public String getSpecificSlug(@PathParam("slug") String slug) throws IOException, NotFoundException {
        String data = HttpUtils.fetchData("https://partners.every.org/v0.2/nonprofit/"+slug+"?apiKey=2b719ff3063ef1714c32edbfdd7af870");
        DataDTO theDataDTO = GSON.fromJson(data, DataDTO.class);
        String sendthis = GSON.toJson(theDataDTO);
        sendthis = sendthis+data;
        return sendthis;
    }
}
