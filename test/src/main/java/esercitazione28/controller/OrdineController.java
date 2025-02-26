package esercitazione28.controller;

import esercitazione28.object.dto.OrdineDTO;
import esercitazione28.object.dto.DettaglioOrdineDTO;
import esercitazione28.object.model.Ordine;
import esercitazione28.object.model.DettaglioOrdine;
import esercitazione28.service.Ordine.OrdineService;
import esercitazione28.tools.ConversionTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    // Endpoint per creare un nuovo ordine utilizzando conversioni DTO ↔ Entity
    @PostMapping
    public ResponseEntity<OrdineDTO> createOrdine(@RequestBody OrdineDTO ordineDTO) {
        // Convertiamo il DTO in entità
        Ordine ordine = ConversionTools.convertToEntity(ordineDTO);
        Ordine created = ordineService.createOrdine(ordine);
        // Convertiamo l'entità creata in DTO per la risposta
        OrdineDTO createdDTO = ConversionTools.convertToDto(created);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    // Endpoint per recuperare tutti gli ordini, restituisce una lista di OrdineDTO
    @GetMapping
    public ResponseEntity<List<OrdineDTO>> getAllOrdini() {
        List<Ordine> ordini = ordineService.getAllOrdini();
        List<OrdineDTO> ordiniDTO = ordini.stream()
                .map(ConversionTools::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ordiniDTO);
    }

    // Endpoint per aggiornare lo stato di un ordine
    @PutMapping("/{id}/stato")
    public ResponseEntity<OrdineDTO> updateStato(@PathVariable Long id, @RequestParam String stato) {
        Ordine updated = ordineService.updateStato(id, stato);
        OrdineDTO updatedDTO = ConversionTools.convertToDto(updated);
        return ResponseEntity.ok(updatedDTO);
    }

    // Endpoint per aggiungere un dettaglio (prodotto) a un ordine esistente
    @PostMapping("/{ordineId}/dettagli")
    public ResponseEntity<DettaglioOrdineDTO> addDettaglio(@PathVariable Long ordineId,
                                                           @RequestBody DettaglioOrdineDTO dettaglioDTO) {
        // Convertiamo il DTO in entità
        DettaglioOrdine dettaglio = ConversionTools.convertToEntity(dettaglioDTO);
        DettaglioOrdine createdDettaglio = ordineService.addDettaglio(ordineId, dettaglio);
        // Convertiamo l'entità creata in DTO per la risposta
        DettaglioOrdineDTO createdDettaglioDTO = ConversionTools.convertToDto(createdDettaglio);
        return new ResponseEntity<>(createdDettaglioDTO, HttpStatus.CREATED);
    }
}




