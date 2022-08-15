package hu.webuni.spring.logistics.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Milestone {

	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDateTime plannedTime;
	
	@ManyToOne
	private Address address;
	
	

	public Milestone() {
		
	}
	
	public Milestone(Long id, LocalDateTime plannedTime, Address address) {
		super();
		this.id = id;
		this.plannedTime = plannedTime;
		this.address = address;
	}



	public Long getId() {
		return id;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
