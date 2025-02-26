package esercitazione28.object.dto;

import lombok.Data;

@Data
public class DettaglioOrdineDTO {
    private Long id;
    private Integer quantita;
    private Double prezzoTotale;
    // Riferimenti agli id di Ordine e Prodotto
    private Long ordineId;
    private Long prodottoId;
}
