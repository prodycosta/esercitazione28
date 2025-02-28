package esercitazione28.service.Utente;

import esercitazione28.object.model.Utente;
import java.util.List;

public interface UtenteService {
    Utente createUtente(Utente utente);
    List<Utente> getAllUtenti();
    Utente getUtenteById(Long id);
    Double getTotaleSpeso(Long utenteId);
}
