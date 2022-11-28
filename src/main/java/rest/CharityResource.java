package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CharityDTO;
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
import java.util.ArrayList;
import java.util.List;


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
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/" + category + "?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=50");
        NonProfitDTO nonProfitDTO = gson.fromJson(nonprofit, NonProfitDTO.class);
        return gson.toJson(nonProfitDTO);

    }

  @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{category}+{name}")
    public String blacklistCharity(@PathParam("category") String category, @PathParam("name") String name) throws IOException {
        Gson gson = new Gson();
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/" + category + "?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=5");
        NonProfitDTO nonProfitDTO = gson.fromJson(nonprofit, NonProfitDTO.class);
        List<String> blacklist = new ArrayList<>();
        blacklist.add(name);
        List<CharityDTO> removeThese = new ArrayList<>();
      for (CharityDTO c: nonProfitDTO.getNonprofits()) {
          for (int i = 0; i < blacklist.size(); i++) {
              if (c.getSlug().equals(blacklist.get(i))){
                  removeThese.add(c);
              }
          }
      }
      for (int i = 0; i < removeThese.size(); i++) {
          int finalI = i;
          nonProfitDTO.getNonprofits().removeIf(c ->(c.equals(removeThese.get(finalI))));
      }

      return gson.toJson(nonProfitDTO);
    }



}
