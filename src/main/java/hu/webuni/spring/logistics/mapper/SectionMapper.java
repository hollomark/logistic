package hu.webuni.spring.logistics.mapper;

import org.mapstruct.Mapper;
import hu.webuni.spring.logistics.dto.SectionDTO;
import hu.webuni.spring.logistics.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {
	
	Section dtoToModel(SectionDTO dto);
	SectionDTO modelToDto(Section model);

}
