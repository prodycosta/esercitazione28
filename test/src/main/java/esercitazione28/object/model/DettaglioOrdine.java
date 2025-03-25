package esercitazione28.object.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @ManyToOne
    @JoinColumn(name = "ordine_id", nullable = false)
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;
}

