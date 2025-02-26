package esercitazione28.controller;

import esercitazione28.object.model.DettaglioOrdine;
import esercitazione28.object.model.Ordine;
import esercitazione28.service.Ordine.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    // Endpoint POST per creare un nuovo ordine associato a un utente specifico.
    // Il JSON inviato deve includere almeno un riferimento all'utente (es. {"utente": {"id": 1}})
    @PostMapping
    public ResponseEntity<Ordine> createOrdine(@RequestBody Ordine ordine) {
        Ordine created = ordineService.createOrdine(ordine);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Endpoint GET per recuperare tutti gli ordini registrati.
    @GetMapping
    public ResponseEntity<List<Ordine>> getAllOrdini() {
        List<Ordine> ordini = ordineService.getAllOrdini();
        return ResponseEntity.ok(ordini);
    }

    // Endpoint PUT per aggiornare lo stato di un ordine.
    // Esempio di richiesta: PUT /api/ordini/1/stato?stato=SPEDITO
    @PutMapping("/{id}/stato")
    public ResponseEntity<Ordine> updateStato(@PathVariable Long id, @RequestParam String stato) {
        Ordine updated = ordineService.updateStato(id, stato);
        return ResponseEntity.ok(updated);
    }

    // Endpoint POST per aggiungere un dettaglio (prodotto) a un ordine esistente.
    // Il JSON inviato deve includere "quantita" e il riferimento al prodotto, per esempio:
    // {
    //    "quantita": 3,
    //    "prodotto": { "id": 1 }
    // }
    @PostMapping("/{ordineId}/dettagli")
    public ResponseEntity<DettaglioOrdine> addDettaglio(@PathVariable Long ordineId,
                                                        @RequestBody DettaglioOrdine dettaglio) {
        DettaglioOrdine createdDettaglio = ordineService.addDettaglio(ordineId, dettaglio);
        return new ResponseEntity<>(createdDettaglio, HttpStatus.CREATED);
    }
}





