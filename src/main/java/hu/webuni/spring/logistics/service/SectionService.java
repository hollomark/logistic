package hu.webuni.spring.logistics.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.spring.logistics.model.Section;
import hu.webuni.spring.logistics.repository.SectionRepository;

@Service
public class SectionService {

	@Autowired
	SectionRepository sectionRepository;
	
	
	@Transactional
	public Section addNew(Section section) {
		
		return sectionRepository.save(section);
	}
	
	@Transactional
	public List<Section> hasMilestoneWithId(Long planId, Long milestoneId) {
		
		return sectionRepository.hasMilestoneWithId(planId, milestoneId);
	}
	
}
