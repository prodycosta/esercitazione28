package esercitazione28.controller;

import esercitazione28.object.model.DettaglioOrdine;
import esercitazione28.object.model.Ordine;
import esercitazione28.service.Ordine.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    // Endpoint per creare un nuovo ordine associato a un utente specifico
    @PostMapping
    public ResponseEntity<Ordine> createOrdine(@RequestBody Ordine ordine) {
        Ordine created = ordineService.createOrdine(ordine);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Endpoint per recuperare tutti gli ordini registrati
    @GetMapping
    public ResponseEntity<List<Ordine>> getAllOrdini() {
        List<Ordine> ordini = ordineService.getAllOrdini();
        return ResponseEntity.ok(ordini);
    }

    // Endpoint per aggiornare lo stato di un ordine
    @PutMapping("/{id}/stato")
    public ResponseEntity<Ordine> updateStato(@PathVariable Long id, @RequestParam String stato) {
        Ordine updated = ordineService.updateStato(id, stato);
        return ResponseEntity.ok(updated);
    }

    // Endpoint per aggiungere un dettaglio (prodotto) a un ordine esistente
    @PostMapping("/{ordineId}/dettagli")
    public ResponseEntity<DettaglioOrdine> addDettaglio(@PathVariable Long ordineId,
                                                        @RequestBody DettaglioOrdine dettaglio) {
        DettaglioOrdine createdDettaglio = ordineService.addDettaglio(ordineId, dettaglio);//Chiama il servizio per aggiungere il dettaglio all'ordine specificato
        return new ResponseEntity<>(createdDettaglio, HttpStatus.CREATED);
    }

    @GetMapping("/intervallo")
    public ResponseEntity<List<Ordine>> getOrdiniByDateRange(@RequestParam String start,
                                                             @RequestParam String end) {
        // Converte le stringhe in LocalDate (assicurati di utilizzare il formato ISO: yyyy-MM-dd)
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<Ordine> ordini = ordineService.getOrdiniByDateRange(startDate, endDate);
        return ResponseEntity.ok(ordini);
    }
}





