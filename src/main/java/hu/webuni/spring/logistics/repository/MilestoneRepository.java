package hu.webuni.spring.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import hu.webuni.spring.logistics.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>, JpaSpecificationExecutor<Milestone> {
	

}
