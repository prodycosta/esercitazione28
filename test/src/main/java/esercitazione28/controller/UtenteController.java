package esercitazione28.controller;

import esercitazione28.object.dto.UtenteDTO;
import esercitazione28.object.model.Utente;
import esercitazione28.service.Utente.UtenteService;
import esercitazione28.tools.ConversionTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // Endpoint per creare un nuovo utente utilizzando conversioni DTO ↔ Entity
    @PostMapping
    public ResponseEntity<UtenteDTO> createUtente(@RequestBody UtenteDTO utenteDTO) {
        // Convertiamo il DTO in entità
        Utente utente = ConversionTools.convertToEntity(utenteDTO);
        Utente created = utenteService.createUtente(utente);
        // Convertiamo l'entità creata in DTO per la risposta
        UtenteDTO createdDTO = ConversionTools.convertToDto(created);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    // Endpoint per recuperare tutti gli utenti, restituisce una lista di UtenteDTO
    @GetMapping
    public ResponseEntity<List<UtenteDTO>> getAllUtenti() {
        List<Utente> utenti = utenteService.getAllUtenti();
        List<UtenteDTO> utentiDTO = utenti.stream()
                .map(ConversionTools::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(utentiDTO);
    }

    // Endpoint per recuperare un utente specifico
    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getUtenteById(@PathVariable Long id) {
        Utente utente = utenteService.getUtenteById(id);
        UtenteDTO utenteDTO = ConversionTools.convertToDto(utente);
        return ResponseEntity.ok(utenteDTO);
    }
}


