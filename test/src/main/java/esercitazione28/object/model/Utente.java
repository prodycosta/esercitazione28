package esercitazione28.object.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    public void addOrdine(Ordine ordine) {
        ordini.add(ordine);
        ordine.setUtente(this);
    }

    public void removeOrdine(Ordine ordine) {
        ordini.remove(ordine);
        ordine.setUtente(null);
    }
}


