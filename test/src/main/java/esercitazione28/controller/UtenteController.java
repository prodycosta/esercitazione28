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

    @PostMapping
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente) {
        Utente created = utenteService.createUtente(utente);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Utente>> getAllUtenti() {
        return ResponseEntity.ok(utenteService.getAllUtenti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        return ResponseEntity.ok(utenteService.getUtenteById(id));
    }

    @GetMapping("/{id}/totale-speso")
    public ResponseEntity<Double> getTotaleSpeso(@PathVariable Long id) {
        return ResponseEntity.ok(utenteService.getTotaleSpeso(id));
    }
}




