package esercitazione28.object.dto;

import lombok.Data;
import java.util.List;

@Data
public class UtenteDTO {
    private Long id;
    private String nome;
    private String email;
    // Se necessario, puoi includere anche una lista di ordini
    private List<OrdineDTO> ordini;
}
