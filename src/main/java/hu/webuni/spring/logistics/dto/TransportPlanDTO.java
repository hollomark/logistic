package hu.webuni.spring.logistics.dto;

import java.util.List;

public class TransportPlanDTO {
	
	
	private Long id;
	
	private List<SectionDTO> sections;
	
	private int income;

	

	public TransportPlanDTO(Long id, List<SectionDTO> sections, int income) {
		super();
		this.id = id;
		this.sections = sections;
		this.income = income;
	}

	public Long getId() {
		return id;
	}
	
	public List<SectionDTO> getSections() {
		return sections;
	}

	public int getIncome() {
		return income;
	}

	public void setSections(List<SectionDTO> sections) {
		this.sections = sections;
	}

	public void setIncome(int income) {
		this.income = income;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
}
