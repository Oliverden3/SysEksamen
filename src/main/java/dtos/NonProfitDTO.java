package dtos;

import com.google.gson.Gson;

import java.util.List;

public class NonProfitDTO {
    public List<CharityDTO> nonprofits;

   public NonProfitDTO(List<String> nonprofits) {
        this.nonprofits = null;
        Gson gson = new Gson();
        for(int i = 0; i < nonprofits.size(); i++ ){
           CharityDTO charityDTO =  gson.fromJson(String.valueOf(nonprofits.get(i)), CharityDTO.class);
            this.nonprofits.add(charityDTO);
        }
    }

    public List<CharityDTO> getNonprofits() {
        return nonprofits;
    }
    public void printall(){
        for (CharityDTO c: nonprofits
             ) {
            System.out.println("NAME: " + c.getName());
            System.out.println(c.getDescription());
        }
    }

    @Override
    public String toString() {
        return "done";
    }
}
