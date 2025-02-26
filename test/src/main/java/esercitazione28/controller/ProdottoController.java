package esercitazione28.controller;

import esercitazione28.object.dto.ProdottoDTO;
import esercitazione28.object.model.Prodotto;
import esercitazione28.service.Prodotto.ProdottoService;
import esercitazione28.tools.ConversionTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    // Endpoint per creare un nuovo prodotto utilizzando conversioni DTO ↔ Entity
    @PostMapping
    public ResponseEntity<ProdottoDTO> createProdotto(@RequestBody ProdottoDTO prodottoDTO) {
        // Convertiamo il DTO in entità
        Prodotto prodotto = ConversionTools.convertToEntity(prodottoDTO);
        Prodotto created = prodottoService.createProdotto(prodotto);
        // Convertiamo l'entità creata in DTO per la risposta
        ProdottoDTO createdDTO = ConversionTools.convertToDto(created);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    // Endpoint per recuperare tutti i prodotti, restituisce una lista di ProdottoDTO
    @GetMapping
    public ResponseEntity<List<ProdottoDTO>> getAllProdotti() {
        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        List<ProdottoDTO> prodottiDTO = prodotti.stream()
                .map(ConversionTools::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(prodottiDTO);
    }
}





