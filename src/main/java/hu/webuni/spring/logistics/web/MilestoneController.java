package hu.webuni.spring.logistics.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.spring.logistics.dto.MilestoneDTO;
import hu.webuni.spring.logistics.mapper.MilestoneMapper;
import hu.webuni.spring.logistics.service.MilestoneService;

@RestController
@RequestMapping("/api/milestone")
public class MilestoneController {
	
	@Autowired
	MilestoneMapper milestoneMapper;
	
	@Autowired
	MilestoneService milestoneService;
	
	
	@PostMapping
	public ResponseEntity<MilestoneDTO> addNew(@RequestBody @Valid MilestoneDTO milestone) {
		
		try 
		{
			return ResponseEntity.ok(milestoneMapper.modelToDto(milestoneService.addNew(milestoneMapper.dtoToModel(milestone))));
		}
		catch(Exception exc)
		{
			System.out.println(exc.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

}
