package hu.webuni.spring.logistics.dto;

public class SectionDTO {
	
	
	private Long id;
	
	private TransportPlanDTO transportPlan;

	private MilestoneDTO fromMilestone;

	private MilestoneDTO toMilestone;
	
	private int number;
	
	

	public SectionDTO(Long id, TransportPlanDTO transportPlan, MilestoneDTO fromMilestone, MilestoneDTO toMilestone,
			int number) {
		super();
		this.id = id;
		this.transportPlan = transportPlan;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
	}

	public void setFromMilestone(MilestoneDTO fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public void setToMilestone(MilestoneDTO toMilestone) {
		this.toMilestone = toMilestone;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public MilestoneDTO getFromMilestone() {
		return fromMilestone;
	}

	public MilestoneDTO getToMilestone() {
		return toMilestone;
	}

	public int getNumber() {
		return number;
	}

	public TransportPlanDTO getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlanDTO transportPlan) {
		this.transportPlan = transportPlan;
	}

	public void setId(Long id) {
		this.id = id;
	}	

}
