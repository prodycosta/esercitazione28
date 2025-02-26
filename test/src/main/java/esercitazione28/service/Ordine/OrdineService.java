package esercitazione28.service.Ordine;

import esercitazione28.object.model.DettaglioOrdine;
import esercitazione28.object.model.Ordine;

import java.util.List;

public interface OrdineService {
    Ordine createOrdine(Ordine ordine);
    List<Ordine> getAllOrdini();
    Ordine updateStato(Long id, String stato);
    DettaglioOrdine addDettaglio(Long ordineId, DettaglioOrdine dettaglio);
}

