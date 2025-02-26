package esercitazione28.tools;

import esercitazione28.object.dto.DettaglioOrdineDTO;
import esercitazione28.object.model.DettaglioOrdine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DettaglioOrdineMapper {
    DettaglioOrdineMapper INSTANCE = Mappers.getMapper(DettaglioOrdineMapper.class);

    DettaglioOrdineDTO toDto(DettaglioOrdine dettaglioOrdine);
    DettaglioOrdine toEntity(DettaglioOrdineDTO dettaglioOrdineDTO);
}

