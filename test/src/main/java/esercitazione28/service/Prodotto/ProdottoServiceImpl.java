package esercitazione28.service.Prodotto;

import esercitazione28.object.model.Prodotto;
import esercitazione28.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public Prodotto createProdotto(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }

    @Override
    public List<Prodotto> getAllProdotti() {
        return prodottoRepository.findAll();
    }
}


