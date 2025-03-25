package esercitazione28.service.Ordine;

import esercitazione28.object.model.DettaglioOrdine;
import esercitazione28.object.model.Ordine;
import esercitazione28.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Override
    public Ordine createOrdine(Ordine ordine) {
        if (ordine.getTotale() == null) {
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
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ordine non trovato con id: " + id));
        ordine.setStato(stato);
        return ordineRepository.save(ordine);
    }

    @Override
    public DettaglioOrdine addDettaglio(Long ordineId, DettaglioOrdine dettaglio) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new NoSuchElementException("Ordine non trovato con id: " + ordineId));

        if (dettaglio.getProdotto() != null && dettaglio.getQuantita() != null) {
            Double prezzoCalcolato = dettaglio.getProdotto().getPrezzo() * dettaglio.getQuantita();
            dettaglio.setPrezzoTotale(prezzoCalcolato);
            ordine.setTotale(ordine.getTotale() + prezzoCalcolato);
        }

        dettaglio.setOrdine(ordine);
        ordine.getDettagli().add(dettaglio);
        ordineRepository.save(ordine);

        return dettaglio;
    }

    @Override
    public List<Ordine> getOrdiniByDateRange(LocalDate start, LocalDate end) {
        return ordineRepository.findByDataBetween(start, end);
    }

    @Override
    public Double getTotaleSpeso(Long id) {
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ordine non trovato con id: " + id));
        return ordine.getTotale();
    }
}




