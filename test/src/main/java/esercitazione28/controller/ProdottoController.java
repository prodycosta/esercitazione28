package esercitazione28.controller;

import esercitazione28.object.model.Prodotto;
import esercitazione28.service.Prodotto.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    // Endpoint POST per aggiungere un nuovo prodotto
    @PostMapping
    public ResponseEntity<Prodotto> createProdotto(@RequestBody Prodotto prodotto) {
        Prodotto created = prodottoService.createProdotto(prodotto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Endpoint per recuperare tutti i prodotti disponibili
    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProdotti() {
        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        return ResponseEntity.ok(prodotti);
    }
}





