package hu.webuni.spring.logistics.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.spring.logistics.model.Address;
import hu.webuni.spring.logistics.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	@Transactional
	public List<Address> getAll() {
		
		return addressRepository.findAll();		
	}
	
	@Transactional
	public Address getOneById(Long id) {
		
		return addressRepository.listById(id);
	}
	
	@Transactional
	public void deleteAddressById(Long id) {
		
		addressRepository.deleteById(id);
	}
	
	@Transactional
	public Page<Address> searchByExample(Address example, Pageable pageable) {
		
		// Get filters
		String countryIsoCode 	= example.getCountryIsoCode();
		String city				= example.getCity();
		String postcode			= example.getPostcode();
		String street			= example.getStreet();
		
		// Init specification
		Specification<Address> specification = Specification.where(null);
		
		if(countryIsoCode != null && !countryIsoCode.isBlank())
		{
			specification = specification.and(AddressSpecification.countryEquals(countryIsoCode));
		}
		
		if(city != null && !city.isBlank())
		{
			specification = specification.and(AddressSpecification.cityContains(city));
		}
		
		if(postcode != null && !postcode.isBlank())
		{
			specification = specification.and(AddressSpecification.postcodeEquals(postcode));
		}
		
		if(street != null && !street.isBlank())
		{
			specification = specification.and(AddressSpecification.streetContains(street));
		}
		
		
		return addressRepository.findAll(specification, pageable);
	}
	
	@Transactional
	public Address addNew(Address address) {
		
		return addressRepository.save(address);
	}
	
	@Transactional
	public Boolean isPresent(Long id) {
		
		return addressRepository.existsById(id); 
	}
	
}
