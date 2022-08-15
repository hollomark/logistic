package hu.webuni.spring.logistics.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.spring.logistics.dto.AddressDTO;
import hu.webuni.spring.logistics.mapper.AddressMapper;
import hu.webuni.spring.logistics.model.Address;
import hu.webuni.spring.logistics.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressRestController {
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressMapper addressMapper;
	
	
	@GetMapping
	public List<AddressDTO> listAll() {
		
		return addressMapper.listModelToDto(addressService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressDTO> getOneById(@PathVariable Long id) {
		
		AddressDTO found = addressMapper.modelToDto(addressService.getOneById(id));
		
		if(found != null)
		{
			return ResponseEntity.ok(found);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
		
	}

	@PostMapping
	public ResponseEntity<AddressDTO> addNew(@RequestBody @Valid AddressDTO address) {
		
		try 
		{
			AddressDTO saved = addressMapper.modelToDto(addressService.addNew(addressMapper.dtoToModel(address)));
			return ResponseEntity.ok(saved);
		}
		catch(Exception exc)
		{
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<AddressDTO>> searchByExample(@RequestBody AddressDTO example, 
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "0") int size, 
			@RequestParam(defaultValue = "id,asc") String sort) {
		
		if(example == null)
		{
			return ResponseEntity.badRequest().build();
		}
		
		if(size == 0)
		{
			size = Integer.MAX_VALUE;
		}
		
		
		// Set sorting
		Pageable pageable;
		
		if(sort.contains(",asc") || !sort.contains(",desc"))
		{
			pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0]).ascending());
		}
		else
		{
			pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0]).descending());
		}
		
		// Get the actual page
		Page<Address> result = addressService.searchByExample(addressMapper.dtoToModel(example), pageable);
		
		// Set total count header
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("X-Total-Count", String.valueOf(result.getTotalElements()));
		
		return ResponseEntity
				.ok()
				.headers(responseHeaders)
				.body(addressMapper.listModelToDto(result.getContent()));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressDTO> deleteAddress(@PathVariable Long id) {
		
		try
		{
			addressService.deleteAddressById(id);
		}
		catch(Exception exc)
		{
			System.out.println(exc.getMessage());
		}

		//Finally
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AddressDTO> overwriteExisting(@RequestBody AddressDTO newAddress, @PathVariable Long id) {
		
		if(newAddress.getId() != null && newAddress.getId() != id)
		{
			return ResponseEntity.badRequest().build();
		}
		else if(!addressService.isPresent(id))
		{
			return ResponseEntity.notFound().build();
		}
		
		try 
		{
			newAddress.setId(id);
			AddressDTO saved = addressMapper.modelToDto(addressService.addNew(addressMapper.dtoToModel(newAddress)));
			return ResponseEntity.ok(saved);
		}
		catch(Exception exc)
		{
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	
}
