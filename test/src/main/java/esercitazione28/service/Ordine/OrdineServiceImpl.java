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
        // Imposta la data corrente e inizializza il totale a 0.0
        ordine.setData(LocalDate.now());
        ordine.setTotale(0.0);
        // È importante che l'oggetto Ordine abbia il riferimento all'utente valorizzato (ad es. {"utente": {"id": 1}})
        return ordineRepository.save(ordine);
    }

    @Override
    public List<Ordine> getAllOrdini() {
        return ordineRepository.findAll();
    }

    @Override
    public Ordine updateStato(Long id, String stato) {
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ordine non trovato con id: " + id));
        ordine.setStato(stato);
        return ordineRepository.save(ordine);
    }

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
        // Carica il prodotto completo dal repository, in modo da avere il prezzo corretto
        Prodotto prodotto = prodottoRepository.findById(prodottoId)
                .orElseThrow(() -> new NoSuchElementException("Prodotto non trovato con id: " + prodottoId));
        dettaglio.setProdotto(prodotto);

        // Aggiunge il dettaglio all'ordine. Il metodo addDettaglio dell'entità Ordine:
        // - Imposta la relazione bidirezionale
        // - Calcola il prezzo totale per il dettaglio (prodotto.prezzo * quantita)
        // - Aggiorna il totale dell'ordine
        ordine.addDettaglio(dettaglio);

        // Salva l'ordine aggiornato
        ordineRepository.save(ordine);
        // Salva e ritorna il dettaglio d'ordine
        return dettaglioOrdineRepository.save(dettaglio);
    }
}


