package dtos;

import com.google.gson.Gson;

import java.util.List;

public class DataDTO {
    NonProfitDTO nonProfitDTOS;
    public DataDTO(String nonprofit, String nonprofitTags){
            Gson gson = new Gson();
             NonProfitDTO nonProfitDTO = gson.fromJson(nonprofit, NonProfitDTO.class);
             this.nonProfitDTOS = nonProfitDTO;
        }

    public NonProfitDTO getNonProfitDTOS() {
        return nonProfitDTOS;
    }
}
