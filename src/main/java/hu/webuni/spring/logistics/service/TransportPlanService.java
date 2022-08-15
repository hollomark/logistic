package hu.webuni.spring.logistics.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import hu.webuni.spring.logistics.model.Delay;
import hu.webuni.spring.logistics.model.Milestone;
import hu.webuni.spring.logistics.model.Section;
import hu.webuni.spring.logistics.model.TransportPlan;
import hu.webuni.spring.logistics.repository.MilestoneRepository;
import hu.webuni.spring.logistics.repository.SectionRepository;
import hu.webuni.spring.logistics.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	
	@Value("${income.loss.30.percent}")
	int until30;
	
	@Value("${income.loss.60.percent}")
	int until60;
	
	@Value("${income.loss.120.percent}")
	int until120;
	
	
	@Transactional
	public TransportPlan addNew(TransportPlan plan) {
		
		return transportPlanRepository.save(plan);
	}
	
	@Transactional
	public TransportPlan getById(Long id) {
		
		return transportPlanRepository.getOne(id);
	}
	
	@Transactional
	public Boolean planExists(Long id) {
		
		return transportPlanRepository.existsById(id);
	}

	@Transactional
	public TransportPlan addDelay(Long planId, Delay delay) {
		
		TransportPlan plan = this.getById(planId);
		
		if(!milestoneRepository.existsById(delay.getMilestoneId()) || plan == null)
		{
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		Milestone milestone = milestoneRepository.getById(delay.getMilestoneId());
		List<Section> sections = sectionRepository.hasMilestoneWithId(planId, milestone.getId());
		
		if(sections == null || sections.size() == 0)
		{
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		
		Section section = sections.get(0);
		
		
		if(section.getFromMilestone().equals(milestone))
		{	
			// Apply delay to the whole section
			Milestone toMilestone = section.getToMilestone();
			toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
			section.setToMilestone(toMilestone);
			
			milestoneRepository.save(toMilestone);
		}
		else if(section.getToMilestone().equals(milestone))
		{
			// Delayed milestone is the end one
			List<Section> planSections = plan.getSections();
			if(sections.size() > section.getNumber())
			{
				// Number: 1, Id: 0
				// Number: 2, Id: 1
				// The previus section's number is the next section's id
				Section nextSection = planSections.get(section.getNumber());
				
				if(nextSection != null && !nextSection.getFromMilestone().equals(milestone))
				{
					Milestone fromMilestone = nextSection.getFromMilestone();
					
					fromMilestone.setPlannedTime(
							fromMilestone.getPlannedTime()
							.plusMinutes(delay.getDelay()));
					
					milestoneRepository.save(fromMilestone);
				}
				else
				{
					Milestone toMilestone = nextSection.getToMilestone();
					toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
					nextSection.setToMilestone(toMilestone);
					
					milestoneRepository.save(toMilestone);	
				}
			}
		}
		
		
		// Add delay
		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay.getDelay()));
		milestoneRepository.save(milestone);
		
		
		// Set income loss
		if(delay.getDelay() <= 30)
		{
			plan.setIncome(this.decreaseIncome(plan.getIncome(), until30));
		}
		else if(delay.getDelay() <= 60)
		{
			plan.setIncome(this.decreaseIncome(plan.getIncome(), until60));
		}
		else
		{
			plan.setIncome(this.decreaseIncome(plan.getIncome(), until120));
		}
		
		
		return plan;
	}
	

	private int decreaseIncome(int income, int percent) {
		
		return (int) (income * (1.0 - (percent / 100.0)));
	}
}
