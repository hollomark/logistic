package hu.webuni.spring.logistics.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class TransportPlan {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy = "transportPlan")
	private List<Section> sections;
	
	private int income;
	
	
	public TransportPlan() {

	}

	public TransportPlan(Long id, List<Section> sections, int income) {
		super();
		this.id = id;
		this.sections = sections;
		this.income = income;
	}


	public List<Section> addNewSection(Section section) {
		
		if(!this.sections.contains(section))
		{
			this.sections.add(section.getNumber(), section);
		}
		
		return this.getSections();		
	}
	
	public Long getId() {
		return id;
	}

	public List<Section> getSections() {
		return sections;
	}

	public int getIncome() {
		return income;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}
