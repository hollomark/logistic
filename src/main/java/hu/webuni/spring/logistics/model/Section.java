package hu.webuni.spring.logistics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Section {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private TransportPlan transportPlan;
	
	@ManyToOne
	private Milestone fromMilestone;
	
	@ManyToOne
	private Milestone toMilestone;
	
	private int number;

	
	
	public Section() {
		super();
	}

	public Section(Long id, TransportPlan transportPlan, Milestone fromMilestone, Milestone toMilestone, int number) {
		super();
		this.id = id;
		this.transportPlan = transportPlan;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
	}



	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public int getNumber() {
		return number;
	}

	public TransportPlan getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlan transportPlan) {
		this.transportPlan = transportPlan;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}
