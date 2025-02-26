package esercitazione28.tools;

import esercitazione28.object.dto.ProdottoDTO;
import esercitazione28.object.model.Prodotto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdottoMapper {
    ProdottoMapper INSTANCE = Mappers.getMapper(ProdottoMapper.class);

    ProdottoDTO toDto(Prodotto prodotto);
    Prodotto toEntity(ProdottoDTO prodottoDTO);
}

