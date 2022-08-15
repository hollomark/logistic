package hu.webuni.spring.logistics.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.spring.logistics.model.Milestone;
import hu.webuni.spring.logistics.repository.AddressRepository;
import hu.webuni.spring.logistics.repository.MilestoneRepository;

@Service
public class MilestoneService {
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	AddressRepository addressRepository;

	
	@Transactional
	public Milestone addNew(Milestone milestone) {
		
		return milestoneRepository.save(milestone);
	}
	
	@Transactional
	public Boolean milestoneExists(Long id) {
		
		return milestoneRepository.existsById(id);
	}

	@Transactional
	public Milestone getById(Long milestoneId) {
		
		return milestoneRepository.getById(milestoneId);
	}
}
