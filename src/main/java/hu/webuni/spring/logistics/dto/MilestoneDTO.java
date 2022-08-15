package hu.webuni.spring.logistics.dto;

import java.time.LocalDateTime;

public class MilestoneDTO {


	private Long id;
	
	private LocalDateTime plannedTime;

	private AddressDTO address;
	
	

	public MilestoneDTO(Long id, LocalDateTime plannedTime, AddressDTO address) {
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
