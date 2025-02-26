package esercitazione28.service.Ordine;

import esercitazione28.object.model.DettaglioOrdine;
import esercitazione28.object.model.Ordine;
import esercitazione28.repository.DettaglioOrdineRepository;
import esercitazione28.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrdineServiceImpl implements OrdineService {

    private final OrdineRepository ordineRepository;
    private final DettaglioOrdineRepository dettaglioOrdineRepository;

    @Autowired
    public OrdineServiceImpl(OrdineRepository ordineRepository, DettaglioOrdineRepository dettaglioOrdineRepository) {
        this.ordineRepository = ordineRepository;
        this.dettaglioOrdineRepository = dettaglioOrdineRepository;
    }

    @Override
    public Ordine createOrdine(Ordine ordine) {
        ordine.setData(LocalDate.now());
        ordine.setTotale(0.0);
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
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new NoSuchElementException("Ordine non trovato con id: " + ordineId));
        // Utilizza il metodo addDettaglio definito nel model Ordine per gestire la relazione bidirezionale e aggiornare il totale
        ordine.addDettaglio(dettaglio);
        ordineRepository.save(ordine);
        return dettaglioOrdineRepository.save(dettaglio);
    }
}
