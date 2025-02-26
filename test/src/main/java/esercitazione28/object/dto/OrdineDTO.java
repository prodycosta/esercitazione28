package esercitazione28.object.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrdineDTO {
    private Long id;
    private LocalDate data;
    // Valori ammessi: "IN_ATTESA", "SPEDITO", "CONSEGNATO"
    private String stato;
    private Double totale;
    // Riferimento all'utente (id)
    private Long utenteId;
    // Dettagli dell'ordine
    private List<DettaglioOrdineDTO> dettagli;
}
