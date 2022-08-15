package hu.webuni.spring.logistics.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.spring.logistics.model.Address;
import hu.webuni.spring.logistics.model.Address_;

public class AddressSpecification {
	
	public static Specification<Address> countryEquals(String countryIsoCode) {
		
		return (root, cq, cb) -> cb.equal(root.get(Address_.countryIsoCode), countryIsoCode);
	}

	public static Specification<Address> cityContains(String city) {
		
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), city.toLowerCase() + "%");
	}

	public static Specification<Address> postcodeEquals(String postcode) {

		return (root, cq, cb) -> cb.equal(root.get(Address_.postcode), postcode);
	}

	public static Specification<Address> streetContains(String street) {

		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), street.toLowerCase() + "%");
	}

}
