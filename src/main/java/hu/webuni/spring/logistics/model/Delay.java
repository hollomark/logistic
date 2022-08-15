package hu.webuni.spring.logistics.model;

public class Delay {

	private Long milestoneId;
	private int delay;
	
	
	public Delay(Long milestoneId, int delay) {
		super();
		this.milestoneId = milestoneId;
		this.delay = delay;
	}
	
	public Long getMilestoneId() {
		return milestoneId;
	}
	
	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
}
