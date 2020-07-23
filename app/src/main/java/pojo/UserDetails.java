package pojo;

public class UserDetails{
	private String emailId;
	private String country;
	private String profileImgOriginal;
	private String gender;
	private String lastName;
	private int id;
	private String profileImgCompress;
	private String profileImgThumbnail;
	private String firstName;
	private String socialUid;

	public void setEmailId(String emailId){
		this.emailId = emailId;
	}

	public String getEmailId(){
		return emailId;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setProfileImgOriginal(String profileImgOriginal){
		this.profileImgOriginal = profileImgOriginal;
	}

	public String getProfileImgOriginal(){
		return profileImgOriginal;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setProfileImgCompress(String profileImgCompress){
		this.profileImgCompress = profileImgCompress;
	}

	public String getProfileImgCompress(){
		return profileImgCompress;
	}

	public void setProfileImgThumbnail(String profileImgThumbnail){
		this.profileImgThumbnail = profileImgThumbnail;
	}

	public String getProfileImgThumbnail(){
		return profileImgThumbnail;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setSocialUid(String socialUid){
		this.socialUid = socialUid;
	}

	public String getSocialUid(){
		return socialUid;
	}

	@Override
 	public String toString(){
		return 
			"UserDetails{" + 
			"email_id = '" + emailId + '\'' + 
			",country = '" + country + '\'' + 
			",profile_img_original = '" + profileImgOriginal + '\'' + 
			",gender = '" + gender + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",id = '" + id + '\'' + 
			",profile_img_compress = '" + profileImgCompress + '\'' + 
			",profile_img_thumbnail = '" + profileImgThumbnail + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",social_uid = '" + socialUid + '\'' + 
			"}";
		}
}
