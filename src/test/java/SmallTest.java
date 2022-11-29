import com.google.gson.Gson;
import dtos.CharityDTO;
import dtos.NonProfitDTO;
import utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmallTest {
    public static void main(String[] args) throws IOException {
        boolean set = false;
        Gson gson = new Gson();
        String placeholder = "science";
        // this is our api key: 2b719ff3063ef1714c32edbfdd7af870, we're going to use it at every fetch with this API, if it doesn't work, generate a new one here: https://www.every.org/developer
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/"+placeholder+"?apiKey=2b719ff3063ef1714c32edbfdd7af870&take=5");
        NonProfitDTO nonProfitDTO = gson.fromJson(nonprofit,NonProfitDTO.class);
        String bl1 = "electionscience";
        String bl2 = "spacescience";
        List<CharityDTO> danger = new ArrayList<>();
        for (CharityDTO c: nonProfitDTO.getNonprofits()) {
            System.out.println(c.getSlug());
            if (c.getSlug().equals(bl1)|| c.getSlug().equals(bl2)){
                danger.add(c);
            }
        }
        System.out.println(danger.size());
        for (int i = 0; i < danger.size(); i++) {
            int finalI = i;
            nonProfitDTO.getNonprofits().removeIf(c ->(c.equals(danger.get(finalI))));
        }
        for (CharityDTO c: nonProfitDTO.getNonprofits()) {
            System.out.println(c.getSlug());
        }
    }

}
