package hu.webuni.spring.logistics.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import hu.webuni.spring.logistics.dto.DelayDTO;
import hu.webuni.spring.logistics.dto.TransportPlanDTO;
import hu.webuni.spring.logistics.mapper.DelayMapper;
import hu.webuni.spring.logistics.mapper.MilestoneMapper;
import hu.webuni.spring.logistics.mapper.TransportPlanMapper;
import hu.webuni.spring.logistics.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {
	
	@Autowired
	TransportPlanService transportPlanService;

	@Autowired
	TransportPlanMapper transportPlanMapper;
	
	@Autowired
	DelayMapper delayMapper;
	
	@Autowired
	MilestoneMapper milestoneMapper;
	
	
	
	
	@PostMapping("/{planId}/delay")
	public ResponseEntity<TransportPlanDTO> addDelayToMilestone(@RequestBody DelayDTO delay, @PathVariable Long planId) {
		
		try
		{
			TransportPlanDTO updated = transportPlanMapper.modelToDto(transportPlanService.addDelay(planId, delayMapper.dtoToModel(delay)));
			return ResponseEntity.ok(updated);
		}
		catch(HttpClientErrorException exc)
		{
		 	return ResponseEntity.status(exc.getStatusCode()).build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<TransportPlanDTO> addNew(@RequestBody @Valid TransportPlanDTO plan) {
		
		try
		{
			TransportPlanDTO saved = transportPlanMapper.modelToDto(transportPlanService.addNew(transportPlanMapper.dtoToModel(plan)));
			return ResponseEntity.ok(saved);
		}
		catch(Exception exc)
		{
			System.out.println(exc.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

}
