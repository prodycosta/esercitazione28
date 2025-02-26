package esercitazione28.tools;

import esercitazione28.object.dto.UtenteDTO;
import esercitazione28.object.model.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtenteMapper {
    UtenteMapper INSTANCE = Mappers.getMapper(UtenteMapper.class);

    UtenteDTO toDto(Utente utente);
    Utente toEntity(UtenteDTO utenteDTO);
}

