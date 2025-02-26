package esercitazione28.object.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private String stato; // IN_ATTESA, SPEDITO, CONSEGNATO
    private Double totale = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DettaglioOrdine> dettagli = new ArrayList<>();

    // Metodo di utilità per aggiungere un dettaglio
    public void addDettaglio(DettaglioOrdine dettaglio) {
        // Imposta la relazione bidirezionale
        dettagli.add(dettaglio);
        dettaglio.setOrdine(this);

        // Calcola automaticamente il prezzo totale in base al prezzo unitario del prodotto e alla quantità
        // Assumendo che il prezzoTotale debba essere: prodotto.prezzo * dettaglio.quantita
        if(dettaglio.getProdotto() != null && dettaglio.getQuantita() != null) {
            Double prezzoCalcolato = dettaglio.getProdotto().getPrezzo() * dettaglio.getQuantita();
            dettaglio.setPrezzoTotale(prezzoCalcolato);
            // Aggiorna il totale dell'ordine
            this.totale += prezzoCalcolato;
        }
    }
}

