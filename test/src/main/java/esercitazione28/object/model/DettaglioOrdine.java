package esercitazione28.object.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DettaglioOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "prezzo_totale")
    private Double prezzoTotale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id", nullable = false)
    private Ordine ordine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;
}
