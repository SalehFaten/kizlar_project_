package models;

import java.util.Vector;

public class City {
	
	private String CityName;
	private String CountryName;
	private int CityId;
	private Vector<Path> CityPaths;
	private Vector<InterestingPlace> CityInterestingPlace;
	private Vector<Map> CityMaps;
	
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public int getCityId() {
		return CityId;
	}
	public void setCityId(int cityId) {
		CityId = cityId;
	}
	public Vector<Path> getCityPaths() {
		return CityPaths;
	}
	public void setCityPaths(Vector<Path> cityPaths) {
		CityPaths = cityPaths;
	}
	public Vector<InterestingPlace> getCityInterestingPlace() {
		return CityInterestingPlace;
	}
	public void setCityInterestingPlace(Vector<InterestingPlace> cityInterestingPlace) {
		CityInterestingPlace = cityInterestingPlace;
	}
	public Vector<Map> getCityMaps() {
		return CityMaps;
	}
	public void setCityMaps(Vector<Map> cityMaps) {
		CityMaps = cityMaps;
	}

}
