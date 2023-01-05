package Youcode.project.Dto;

import lombok.Data;

@Data
public class HotelWithManager {


   private Long id;
   private String ville;
   private String name;
   private String description;
   private Long managerId;


}
