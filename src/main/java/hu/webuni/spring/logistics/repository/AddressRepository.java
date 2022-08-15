package hu.webuni.spring.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.spring.logistics.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
	
	@Query("SELECT a FROM Address a WHERE id = :id")
	public Address listById(Long id);
	
}
