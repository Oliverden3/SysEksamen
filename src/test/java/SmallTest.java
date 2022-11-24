import com.google.gson.Gson;
import dtos.NonProfitDTO;
import utils.HttpUtils;

import java.io.IOException;

public class SmallTest {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        String placeholder = "science";
        // this is our api key: 2b719ff3063ef1714c32edbfdd7af870, we're going to use it at every fetch with this API, if it doesn't work, generate a new one here: https://www.every.org/developer
        String nonprofit = HttpUtils.fetchData("https://partners.every.org/v0.2/search/"+placeholder+"?apiKey=2b719ff3063ef1714c32edbfdd7af870");
        NonProfitDTO nonProfitDTO = gson.fromJson(nonprofit,NonProfitDTO.class);
        System.out.println(nonprofit);
        nonProfitDTO.printall();
    }
}
