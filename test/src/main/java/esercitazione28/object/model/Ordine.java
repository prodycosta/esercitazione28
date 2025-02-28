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
    private String stato;
    private Double totale = 0.0;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DettaglioOrdine> dettagli = new ArrayList<>();
    //incapsula la logica di dominio relativa all'ordine
//Ogni volta che aggiungi un dettaglio all'ordine, viene gestito autonamente
    // Metodo per aggiungere un dettaglio al'ordine
    public void addDettaglio(DettaglioOrdine dettaglio) {
        dettagli.add(dettaglio);
        dettaglio.setOrdine(this);
        //se dettaglio contiene prodotto e qualitá allora calcola prezzo totale
        if(dettaglio.getProdotto() != null && dettaglio.getQuantita() != null) {
            Double prezzoCalcolato = dettaglio.getProdotto().getPrezzo() * dettaglio.getQuantita();
            dettaglio.setPrezzoTotale(prezzoCalcolato);
            this.totale += prezzoCalcolato;
        }
    }
}

