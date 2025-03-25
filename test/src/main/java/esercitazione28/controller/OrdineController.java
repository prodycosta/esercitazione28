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

    @PostMapping
    public ResponseEntity<Ordine> createOrdine(@RequestBody Ordine ordine) {
        Ordine created = ordineService.createOrdine(ordine);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ordine>> getAllOrdini() {
        return ResponseEntity.ok(ordineService.getAllOrdini());
    }

    @PutMapping("/{id}/stato")
    public ResponseEntity<Ordine> updateStato(@PathVariable Long id, @RequestParam String stato) {
        return ResponseEntity.ok(ordineService.updateStato(id, stato));
    }

    @PostMapping("/{ordineId}/dettagli")
    public ResponseEntity<DettaglioOrdine> addDettaglio(@PathVariable Long ordineId,
                                                        @RequestBody DettaglioOrdine dettaglio) {
        DettaglioOrdine createdDettaglio = ordineService.addDettaglio(ordineId, dettaglio);
        return new ResponseEntity<>(createdDettaglio, HttpStatus.CREATED);
    }

    @GetMapping("/intervallo")
    public ResponseEntity<List<Ordine>> getOrdiniByDateRange(@RequestParam("startDate") String startDateStr,
                                                             @RequestParam("endDate") String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        return ResponseEntity.ok(ordineService.getOrdiniByDateRange(startDate, endDate));
    }

    @GetMapping("/totale-speso/{id}")
    public ResponseEntity<Double> getTotaleSpeso(@PathVariable Long id) {
        return ResponseEntity.ok(ordineService.getTotaleSpeso(id));
    }
}






