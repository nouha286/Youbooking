package Youcode.project.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationChambre {
    private Long id;
    private Long clientId;
    private Long chambreId;
    private String dateDebut;
    private String dateFin;

}
