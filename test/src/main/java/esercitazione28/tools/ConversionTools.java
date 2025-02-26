package esercitazione28.tools;

import esercitazione28.object.dto.UtenteDTO;
import esercitazione28.object.dto.OrdineDTO;
import esercitazione28.object.dto.ProdottoDTO;
import esercitazione28.object.dto.DettaglioOrdineDTO;
import esercitazione28.object.model.Utente;
import esercitazione28.object.model.Ordine;
import esercitazione28.object.model.Prodotto;
import esercitazione28.object.model.DettaglioOrdine;
import java.util.List;
import java.util.stream.Collectors;

public class ConversionTools {

    // --- Conversioni per Utente ---
    public static UtenteDTO convertToDto(Utente utente) {
        if(utente == null) {
            return null;
        }
        UtenteDTO dto = new UtenteDTO();
        dto.setId(utente.getId());
        dto.setNome(utente.getNome());
        dto.setEmail(utente.getEmail());
        // Se necessario, converte anche gli ordini associati
        if (utente.getOrdini() != null) {
            List<OrdineDTO> ordiniDTO = utente.getOrdini().stream()
                    .map(ConversionTools::convertToDto)
                    .collect(Collectors.toList());
            dto.setOrdini(ordiniDTO);
        }
        return dto;
    }

    public static Utente convertToEntity(UtenteDTO dto) {
        if(dto == null) {
            return null;
        }
        Utente utente = new Utente();
        utente.setId(dto.getId());
        utente.setNome(dto.getNome());
        utente.setEmail(dto.getEmail());
        // Se necessario, converte anche la lista degli ordini
        if (dto.getOrdini() != null) {
            List<Ordine> ordini = dto.getOrdini().stream()
                    .map(ConversionTools::convertToEntity)
                    .collect(Collectors.toList());
            utente.setOrdini(ordini);
        }
        return utente;
    }

    // --- Conversioni per Ordine ---
    public static OrdineDTO convertToDto(Ordine ordine) {
        if(ordine == null) {
            return null;
        }
        OrdineDTO dto = new OrdineDTO();
        dto.setId(ordine.getId());
        dto.setData(ordine.getData());
        dto.setStato(ordine.getStato());
        dto.setTotale(ordine.getTotale());
        // Se l'ordine ha un utente, settiamo solo l'id
        if (ordine.getUtente() != null) {
            dto.setUtenteId(ordine.getUtente().getId());
        }
        // Convertiamo i dettagli dell'ordine se presenti
        if (ordine.getDettagli() != null) {
            List<DettaglioOrdineDTO> dettagliDTO = ordine.getDettagli().stream()
                    .map(ConversionTools::convertToDto)
                    .collect(Collectors.toList());
            dto.setDettagli(dettagliDTO);
        }
        return dto;
    }

    public static Ordine convertToEntity(OrdineDTO dto) {
        if(dto == null) {
            return null;
        }
        Ordine ordine = new Ordine();
        ordine.setId(dto.getId());
        ordine.setData(dto.getData());
        ordine.setStato(dto.getStato());
        ordine.setTotale(dto.getTotale());

        // Impostiamo l'utente associato se utenteId è presente
        if (dto.getUtenteId() != null) {
            Utente utente = new Utente();
            utente.setId(dto.getUtenteId());
            ordine.setUtente(utente);
        }

        if (dto.getDettagli() != null) {
            List<DettaglioOrdine> dettagli = dto.getDettagli().stream()
                    .map(ConversionTools::convertToEntity)
                    .collect(Collectors.toList());
            ordine.setDettagli(dettagli);
        }
        return ordine;
    }

    // --- Conversioni per Prodotto ---
    public static ProdottoDTO convertToDto(Prodotto prodotto) {
        if(prodotto == null) {
            return null;
        }
        ProdottoDTO dto = new ProdottoDTO();
        dto.setId(prodotto.getId());
        dto.setNome(prodotto.getNome());
        dto.setPrezzo(prodotto.getPrezzo());
        return dto;
    }

    public static Prodotto convertToEntity(ProdottoDTO dto) {
        if(dto == null) {
            return null;
        }
        Prodotto prodotto = new Prodotto();
        prodotto.setId(dto.getId());
        prodotto.setNome(dto.getNome());
        prodotto.setPrezzo(dto.getPrezzo());
        return prodotto;
    }

    // --- Conversioni per DettaglioOrdine ---
    public static DettaglioOrdineDTO convertToDto(DettaglioOrdine dettaglio) {
        if(dettaglio == null) {
            return null;
        }
        DettaglioOrdineDTO dto = new DettaglioOrdineDTO();
        dto.setId(dettaglio.getId());
        dto.setQuantita(dettaglio.getQuantita());
        dto.setPrezzoTotale(dettaglio.getPrezzoTotale());
        // Se disponibili, impostiamo gli id di ordine e prodotto
        if (dettaglio.getOrdine() != null) {
            dto.setOrdineId(dettaglio.getOrdine().getId());
        }
        if (dettaglio.getProdotto() != null) {
            dto.setProdottoId(dettaglio.getProdotto().getId());
        }
        return dto;
    }

    public static DettaglioOrdine convertToEntity(DettaglioOrdineDTO dto) {
        if(dto == null) {
            return null;
        }
        DettaglioOrdine dettaglio = new DettaglioOrdine();
        dettaglio.setId(dto.getId());
        dettaglio.setQuantita(dto.getQuantita());
        dettaglio.setPrezzoTotale(dto.getPrezzoTotale());
        // Le associazioni a Ordine e Prodotto possono essere settate altrove
        return dettaglio;
    }
}

