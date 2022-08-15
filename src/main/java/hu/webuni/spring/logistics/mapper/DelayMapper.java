package hu.webuni.spring.logistics.mapper;

import org.mapstruct.Mapper;

import hu.webuni.spring.logistics.dto.DelayDTO;
import hu.webuni.spring.logistics.model.Delay;

@Mapper(componentModel = "spring")
public interface DelayMapper {
	
	Delay dtoToModel(DelayDTO dto);
	DelayDTO modelToDto(Delay model);

}
