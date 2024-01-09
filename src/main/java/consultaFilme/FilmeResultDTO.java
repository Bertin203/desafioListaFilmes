package consultaFilme;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmeResultDTO {
	@JsonProperty("Title")
	private String Title;
	@JsonProperty("Year")
	private String Year;
	@JsonProperty("Director")
	private String Director;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String director) {
		Director = director;
	}
	
		
}
