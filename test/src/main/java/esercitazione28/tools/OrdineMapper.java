package esercitazione28.tools;

import esercitazione28.object.dto.OrdineDTO;
import esercitazione28.object.model.Ordine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrdineMapper {
    OrdineMapper INSTANCE = Mappers.getMapper(OrdineMapper.class);

    OrdineDTO toDto(Ordine ordine);
    Ordine toEntity(OrdineDTO ordineDTO);
}

