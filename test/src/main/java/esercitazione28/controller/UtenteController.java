package esercitazione28.controller;

import esercitazione28.object.model.Utente;
import esercitazione28.service.Utente.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // Endpoint per creare un nuovo utente
    @PostMapping
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente) {
        Utente created = utenteService.createUtente(utente);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Endpoint per ottenere la lista di tutti gli utenti
    @GetMapping
    public ResponseEntity<List<Utente>> getAllUtenti() {
        List<Utente> utenti = utenteService.getAllUtenti();
        return ResponseEntity.ok(utenti);
    }

    // Endpoint per recuperare un utente specifico
    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        Utente utente = utenteService.getUtenteById(id);
        return ResponseEntity.ok(utente);
    }

    @GetMapping("/{id}/totale-speso")
    public ResponseEntity<Double> getTotaleSpeso(@PathVariable Long id) {
        Double totale = utenteService.getTotaleSpeso(id);
        return ResponseEntity.ok(totale);
    }
}



