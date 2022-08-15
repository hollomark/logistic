package hu.webuni.spring.logistics.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.spring.logistics.dto.AddressDTO;
import hu.webuni.spring.logistics.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	Address dtoToModel(AddressDTO dto); 
	AddressDTO modelToDto(Address model);
	
	List<Address> listDtoToModel(List<AddressDTO> dtoList);
	List<AddressDTO> listModelToDto(List<Address> modelList);
	

}
