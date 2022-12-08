package dtos;

import com.google.gson.Gson;

import java.util.List;

public class DataDTO {
    CharityDTO charityDTOS;
    public DataDTO(){}
    public DataDTO(List<String> nonprofits){
            this.charityDTOS= null;
            Gson gson = new Gson();
             CharityDTO charityDTO = gson.fromJson(String.valueOf(nonprofits.get(0)), CharityDTO.class);
             this.charityDTOS = charityDTO;
        }
}
