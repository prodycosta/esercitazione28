package esercitazione28.service.Prodotto;

import esercitazione28.object.model.Prodotto;

import java.util.List;

public interface ProdottoService {
    Prodotto createProdotto(Prodotto prodotto);
    List<Prodotto> getAllProdotti();
}

