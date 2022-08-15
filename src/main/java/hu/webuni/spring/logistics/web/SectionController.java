package hu.webuni.spring.logistics.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.spring.logistics.dto.SectionDTO;
import hu.webuni.spring.logistics.mapper.SectionMapper;
import hu.webuni.spring.logistics.service.SectionService;

@RestController
@RequestMapping("/api/section")
public class SectionController {
	
	@Autowired
	SectionMapper sectionMapper;
	
	@Autowired
	SectionService sectionService;
	
	@PostMapping
	public ResponseEntity<SectionDTO> addNew(@RequestBody @Valid SectionDTO section) {
		
		try
		{
			SectionDTO saved = sectionMapper.modelToDto(sectionService.addNew(sectionMapper.dtoToModel(section)));
			return ResponseEntity.ok(saved);
		}
		catch(Exception exc)
		{
			System.out.println(exc.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
}
