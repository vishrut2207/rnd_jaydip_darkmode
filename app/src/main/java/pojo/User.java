package pojo;

public class User{
	private String emailId;
	private String thumbnailImg;
	private String country;
	private String profileImg;
	private String originalImg;
	private String gender;
	private Object lastName;
	private String compressImg;
	private Object firstName;

	public void setEmailId(String emailId){
		this.emailId = emailId;
	}

	public String getEmailId(){
		return emailId;
	}

	public void setThumbnailImg(String thumbnailImg){
		this.thumbnailImg = thumbnailImg;
	}

	public String getThumbnailImg(){
		return thumbnailImg;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setProfileImg(String profileImg){
		this.profileImg = profileImg;
	}

	public String getProfileImg(){
		return profileImg;
	}

	public void setOriginalImg(String originalImg){
		this.originalImg = originalImg;
	}

	public String getOriginalImg(){
		return originalImg;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setLastName(Object lastName){
		this.lastName = lastName;
	}

	public Object getLastName(){
		return lastName;
	}

	public void setCompressImg(String compressImg){
		this.compressImg = compressImg;
	}

	public String getCompressImg(){
		return compressImg;
	}

	public void setFirstName(Object firstName){
		this.firstName = firstName;
	}

	public Object getFirstName(){
		return firstName;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"email_id = '" + emailId + '\'' + 
			",thumbnail_img = '" + thumbnailImg + '\'' + 
			",country = '" + country + '\'' + 
			",profile_img = '" + profileImg + '\'' + 
			",original_img = '" + originalImg + '\'' + 
			",gender = '" + gender + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",compress_img = '" + compressImg + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}
