package hu.webuni.spring.logistics.dto;

public class AddressDTO {
	
	
	private Long id;
	
	private String countryIsoCode;
	
	private String city;
	
	private String street;
	
	private String postcode;
	
	private String houseNumber;
	
	private double latitude;
	
	private double longitude;
	
	
	public AddressDTO() {
		
	}

	public AddressDTO(Long id, String countryIsoCode, String city, String street, String postcode, String houseNumber,
			double latitude, double longitude) {

		this.id = id;
		this.countryIsoCode = countryIsoCode;
		this.city = city;
		this.street = street;
		this.postcode = postcode;
		this.houseNumber = houseNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public String getCountryIsoCode() {
		return countryIsoCode;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode = countryIsoCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
