package hu.webuni.spring.logistics.mapper;

import org.mapstruct.Mapper;

import hu.webuni.spring.logistics.dto.MilestoneDTO;
import hu.webuni.spring.logistics.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {

	Milestone dtoToModel(MilestoneDTO dto);
	MilestoneDTO modelToDto(Milestone model);
}
