package esercitazione28.service.Utente;

import esercitazione28.object.model.Utente;
import esercitazione28.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public Utente createUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    @Override
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    @Override
    public Utente getUtenteById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Utente non trovato con id: " + id));
    }
}
