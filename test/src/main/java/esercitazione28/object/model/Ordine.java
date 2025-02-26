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
    // Stato può assumere i valori: "IN_ATTESA", "SPEDITO", "CONSEGNATO"
    private String stato;
    private Double totale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DettaglioOrdine> dettagli = new ArrayList<>();

    // Metodo di utilità per aggiungere un dettaglio e aggiornare il totale
    public void addDettaglio(DettaglioOrdine dettaglio) {
        dettagli.add(dettaglio);
        dettaglio.setOrdine(this);
        if (totale == null) {
            totale = 0.0;
        }
        totale += dettaglio.getPrezzoTotale();
    }

    // Metodo di utilità per rimuovere un dettaglio e aggiornare il totale
    public void removeDettaglio(DettaglioOrdine dettaglio) {
        dettagli.remove(dettaglio);
        dettaglio.setOrdine(null);
        if (totale != null) {
            totale -= dettaglio.getPrezzoTotale();
        }
    }
}

