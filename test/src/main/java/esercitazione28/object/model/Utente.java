package esercitazione28.object.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ordine> ordini = new ArrayList<>();

    // Metodo di utilità per aggiungere un ordine e gestire la relazione bidirezionale
    public void addOrdine(Ordine ordine) {
        ordini.add(ordine);
        ordine.setUtente(this);
    }

    // Metodo di utilità per rimuovere un ordine
    public void removeOrdine(Ordine ordine) {
        ordini.remove(ordine);
        ordine.setUtente(null);
    }
}

