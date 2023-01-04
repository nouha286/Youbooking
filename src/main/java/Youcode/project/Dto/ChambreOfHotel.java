package Youcode.project.Dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChambreOfHotel {

    private Long hotelId;
    private String categorie;
    private String description;
    private Double fraiParNuit;

}
