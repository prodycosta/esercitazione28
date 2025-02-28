package esercitazione28.service.Ordine;

import esercitazione28.object.model.DettaglioOrdine;
import esercitazione28.object.model.Ordine;
import esercitazione28.object.model.Prodotto;
import esercitazione28.repository.DettaglioOrdineRepository;
import esercitazione28.repository.OrdineRepository;
import esercitazione28.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private DettaglioOrdineRepository dettaglioOrdineRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public Ordine createOrdine(Ordine ordine) {
        // Non impostiamo LocalDate.now(), usiamo la data fornita nel payload.
        // Se necessario, puoi comunque verificare che la data non sia nulla e gestirla in altro modo.
        if(ordine.getTotale() == null) {
            ordine.setTotale(0.0);
        }
        return ordineRepository.save(ordine);
    }

    @Override
    public List<Ordine> getAllOrdini() {
        return ordineRepository.findAll();
    }

    @Override
    public Ordine updateStato(Long id, String stato) {
        //recupero ordine dal repository
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ordine non trovato con id: " + id));
        ordine.setStato(stato);//nuovo stato
        return ordineRepository.save(ordine);
    }
//coordina operazione a livello di business
@Override
public DettaglioOrdine addDettaglio(Long ordineId, DettaglioOrdine dettaglio) {
    // Recupera l'ordine esistente
    Ordine ordine = ordineRepository.findById(ordineId)
            .orElseThrow(() -> new NoSuchElementException("Ordine non trovato con id: " + ordineId));

    // Verifica che il dettaglio contenga un prodotto valido
    if (dettaglio.getProdotto() == null || dettaglio.getProdotto().getId() == null) {
        throw new IllegalArgumentException("Il prodotto del dettaglio non è valido");
    }
    Long prodottoId = dettaglio.getProdotto().getId();
    // Carica il prodotto completo dal repository
    Prodotto prodotto = prodottoRepository.findById(prodottoId)
            .orElseThrow(() -> new NoSuchElementException("Prodotto non trovato con id: " + prodottoId));
    dettaglio.setProdotto(prodotto);

    // Aggiunge il dettaglio all'ordine. Il metodo addDettaglio dell'entità Ordine gestisce
    // la relazione bidirezionale e aggiorna il totale.
    ordine.addDettaglio(dettaglio);

    // Salva l'ordine aggiornato, il quale persisterà anche il dettaglio grazie a Cascade.ALL
    ordineRepository.save(ordine);

    // Non serve salvare nuovamente il dettaglio separatamente per evitare duplicazioni
    return dettaglio;
}

    @Override
    public List<Ordine> getOrdiniByDateRange(LocalDate start, LocalDate end) {
        return ordineRepository.findByDataBetween(start, end);
    }
}


