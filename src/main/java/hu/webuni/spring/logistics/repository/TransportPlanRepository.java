package hu.webuni.spring.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.spring.logistics.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long>, JpaSpecificationExecutor<TransportPlan> {

	@Query("SELECT t FROM TransportPlan t JOIN FETCH t.sections WHERE t.id = :id")
	TransportPlan getOne(Long id);
		
}
