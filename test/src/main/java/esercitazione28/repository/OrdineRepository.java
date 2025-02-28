package esercitazione28.repository;

import esercitazione28.object.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    // Metodo per recuperare gli ordini la cui data è compresa tra start e end (inclusi)
    List<Ordine> findByDataBetween(LocalDate start, LocalDate end);
}

