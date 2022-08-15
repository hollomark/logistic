package hu.webuni.spring.logistics.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.spring.logistics.dto.DelayDTO;
import hu.webuni.spring.logistics.dto.LoginDTO;
import hu.webuni.spring.logistics.dto.TransportPlanDTO;
import hu.webuni.spring.logistics.mapper.TransportPlanMapper;
import hu.webuni.spring.logistics.model.Address;
import hu.webuni.spring.logistics.model.Milestone;
import hu.webuni.spring.logistics.model.Section;
import hu.webuni.spring.logistics.model.TransportPlan;
import hu.webuni.spring.logistics.repository.AddressRepository;
import hu.webuni.spring.logistics.repository.MilestoneRepository;
import hu.webuni.spring.logistics.repository.SectionRepository;
import hu.webuni.spring.logistics.repository.TransportPlanRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TrasnportPlanIT {

	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	TransportPlanRepository planRepository;
	
	@Autowired
	TransportPlanMapper planMapper;
	
	
	private static final String LOGIN = "/api/login";
	private static final String BASE_URI = "/api/transportPlans/";

	
	@BeforeEach
	public void initDb() {
		
		// Truncate data
		sectionRepository.deleteAll();
		planRepository.deleteAll();
		milestoneRepository.deleteAll();
		addressRepository.deleteAll();
		
	}
	
	
	
	
	
	
	
	
	@Test
	void notAuthorized() {
		
		DelayDTO delay = new DelayDTO(1L, 5);
		String token = "";
		
		webTestClient
			.post()
			.uri(BASE_URI + "20" + "/delay")
			.headers(headers -> headers.setBearerAuth(token))
			.bodyValue(delay)
			.exchange()
			.expectStatus()
			.isForbidden();
		
	}
	
	
	@Test
	void notExistingPlan() {
		
		DelayDTO delay = new DelayDTO(1L, 5);
		String token = getJwtToken();
		
		webTestClient
			.post()
			.uri(BASE_URI + "20" + "/delay")
			.headers(headers -> headers.setBearerAuth(token))
			.bodyValue(delay)
			.exchange()
			.expectStatus()
			.isNotFound();
	}
	
	
	@Test
	void notExistingMilestone() {
		
		// Init tables with data
		TransportPlan tp1 = planRepository.save(new TransportPlan(1L, null, 10000));
		TransportPlan tp2 = planRepository.save(new TransportPlan(2L, null, 1));
		
		Address add1 = addressRepository.save(new Address(1L, "HU", "Budapest", "Elem utca", "1045", "10", 47.554830, 19.102789));
		Address add2 = addressRepository.save(new Address(2L, "HU", "Kecskemét", "Bánáti utca", "6000", "2", 46.884738, 19.682939));
		Address add3 = addressRepository.save(new Address(3L, "HU", "Bér", "Petőfi út", "3045", "54", 47.863621, 19.506012));
		
		Milestone m1 = milestoneRepository.save(new Milestone(1L, LocalDateTime.parse("2022-02-24T14:00:00"), add1));
		Milestone m2 = milestoneRepository.save(new Milestone(2L, LocalDateTime.parse("2022-02-25T14:00:00"), add2));
		Milestone m3 = milestoneRepository.save(new Milestone(3L, LocalDateTime.parse("2022-02-28T14:00:00"), add3));
		Milestone m4 = milestoneRepository.save(new Milestone(4L, LocalDateTime.parse("2022-03-28T15:00:00"), add3));
		Milestone m5 = milestoneRepository.save(new Milestone(5L, LocalDateTime.parse("2022-03-28T15:00:00"), add3));
		
		Section s1 = sectionRepository.save(new Section(1L, tp1, m1, m2, 1));
		Section s2 = sectionRepository.save(new Section(2L, tp1, m2, m3, 2));
		
		Section s3 = sectionRepository.save(new Section(3L, tp2, m4, m5, 1));
		
		
		DelayDTO delay = new DelayDTO(m4.getId(), 5);
		String token = getJwtToken();
		
		webTestClient
			.post()
			.uri(BASE_URI + tp1.getId() + "/delay")
			.headers(headers -> headers.setBearerAuth(token))
			.bodyValue(delay)
			.exchange()
			.expectStatus()
			.isBadRequest();
	}	
	
	
	@Test
	void addDelayToStratingMilestone() {
		
		// Init tables with data
		TransportPlan tp1 = planRepository.save(new TransportPlan(1L, null, 10000));
		TransportPlan tp2 = planRepository.save(new TransportPlan(2L, null, 1));
		
		Address add1 = addressRepository.save(new Address(1L, "HU", "Budapest", "Elem utca", "1045", "10", 47.554830, 19.102789));
		Address add2 = addressRepository.save(new Address(2L, "HU", "Kecskemét", "Bánáti utca", "6000", "2", 46.884738, 19.682939));
		Address add3 = addressRepository.save(new Address(3L, "HU", "Bér", "Petőfi út", "3045", "54", 47.863621, 19.506012));
		
		Milestone m1 = milestoneRepository.save(new Milestone(1L, LocalDateTime.parse("2022-02-24T14:00:00"), add1));
		Milestone m2 = milestoneRepository.save(new Milestone(2L, LocalDateTime.parse("2022-02-25T14:00:00"), add2));
		Milestone m3 = milestoneRepository.save(new Milestone(3L, LocalDateTime.parse("2022-02-28T14:00:00"), add3));
		Milestone m4 = milestoneRepository.save(new Milestone(4L, LocalDateTime.parse("2022-03-28T15:00:00"), add3));
		Milestone m5 = milestoneRepository.save(new Milestone(5L, LocalDateTime.parse("2022-03-28T15:00:00"), add3));
		
		Section s1 = sectionRepository.save(new Section(1L, tp1, m1, m2, 1));
		Section s2 = sectionRepository.save(new Section(2L, tp1, m2, m3, 2));
		
		Section s3 = sectionRepository.save(new Section(3L, tp2, m4, m5, 1));
		
		
		TransportPlan plan = planMapper.dtoToModel(addDelay(tp1.getId(), m1.getId()));
		List<Section> sections = plan.getSections();
		
		assertThat(sections.get(0).getFromMilestone().getPlannedTime().equals(LocalDateTime.parse("2022-02-24T14:05:00")));
		assertThat(sections.get(0).getToMilestone().getPlannedTime().equals(LocalDateTime.parse("2022-02-25T14:05:00")));
	}
	
	
	@Test
	void addDelayToEndMilestone() {
		
		// Init tables with data
		TransportPlan tp1 = planRepository.save(new TransportPlan(1L, null, 10000));
		TransportPlan tp2 = planRepository.save(new TransportPlan(2L, null, 1));
		
		Address add1 = addressRepository.save(new Address(1L, "HU", "Budapest", "Elem utca", "1045", "10", 47.554830, 19.102789));
		Address add2 = addressRepository.save(new Address(2L, "HU", "Kecskemét", "Bánáti utca", "6000", "2", 46.884738, 19.682939));
		Address add3 = addressRepository.save(new Address(3L, "HU", "Bér", "Petőfi út", "3045", "54", 47.863621, 19.506012));
		
		Milestone m1 = milestoneRepository.save(new Milestone(1L, LocalDateTime.parse("2022-02-24T14:00:00"), add1));
		Milestone m2 = milestoneRepository.save(new Milestone(2L, LocalDateTime.parse("2022-02-25T14:00:00"), add2));
		Milestone m3 = milestoneRepository.save(new Milestone(3L, LocalDateTime.parse("2022-02-28T14:00:00"), add3));
		Milestone m4 = milestoneRepository.save(new Milestone(4L, LocalDateTime.parse("2022-03-28T15:00:00"), add3));
		Milestone m5 = milestoneRepository.save(new Milestone(5L, LocalDateTime.parse("2022-03-28T15:00:00"), add3));
		
		Section s1 = sectionRepository.save(new Section(1L, tp1, m1, m2, 1));
		Section s2 = sectionRepository.save(new Section(2L, tp1, m2, m3, 2));
		
		Section s3 = sectionRepository.save(new Section(3L, tp2, m4, m5, 1));
		
		
		
		TransportPlan plan = planMapper.dtoToModel(addDelay(tp1.getId(), m2.getId()));
		List<Section> sections = plan.getSections();
		
		assertThat(sections.get(0).getToMilestone().getPlannedTime().equals(LocalDateTime.parse("2022-02-25T14:05:00")));
		assertThat(sections.get(1).getFromMilestone().getPlannedTime().equals(LocalDateTime.parse("2022-02-28T14:05:00")));
		
	}
	
	
	
	
	
	
	
	
	
	TransportPlanDTO addDelay(Long planId, Long milestoneId) {
		
		DelayDTO delay = new DelayDTO(milestoneId, 5);
		String token = getJwtToken();
		
		return webTestClient
					.post()
					.uri(BASE_URI + planId + "/delay")
					.headers(headers -> headers.setBearerAuth(token))
					.bodyValue(delay)
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(TransportPlanDTO.class)
					.returnResult()
					.getResponseBody();
					
	}
	
	
	String getJwtToken() {
		
		LoginDTO login = new LoginDTO("transportManager", "password");
		
		return webTestClient
				.post()
				.uri(LOGIN)
				.bodyValue(login)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();
				
	}
	
}
