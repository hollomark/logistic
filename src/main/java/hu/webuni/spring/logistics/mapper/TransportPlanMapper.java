package hu.webuni.spring.logistics.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import hu.webuni.spring.logistics.dto.SectionDTO;
import hu.webuni.spring.logistics.dto.TransportPlanDTO;
import hu.webuni.spring.logistics.model.Section;
import hu.webuni.spring.logistics.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	TransportPlan dtoToModel(TransportPlanDTO dto);	
	TransportPlanDTO modelToDto(TransportPlan model);
	
	// To avoid infinite recursion
	@Mapping(target = "transportPlan", ignore = true)
	SectionDTO sectionToSectionDTO(Section model);
	
	TransportPlanDTO modelToDtoWithoutSections(TransportPlan model);
	
	List<SectionDTO> sectionListToSectionDTOList(List<Section> model);
	
}
