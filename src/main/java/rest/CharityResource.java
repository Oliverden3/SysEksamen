package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.NonProfitDTO;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.io.IOException;


@Path("charity")
public class CharityResource {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{category}")
    public String getAllFromCategory(@PathParam("category") String category) throws IOException {
        Gson gson = new Gson();
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/"+category+"?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
        NonProfitDTO nonProfitDTO = gson.fromJson(nonprofit,NonProfitDTO.class);
        return gson.toJson(nonProfitDTO);
    }



}
