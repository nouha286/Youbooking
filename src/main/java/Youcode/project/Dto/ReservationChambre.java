package Youcode.project.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationChambre {
    private Long clientId;
    private Long chambreId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
