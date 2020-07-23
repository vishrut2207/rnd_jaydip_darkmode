package pojo;


public class User_details
{
    private String email_id;

    private String country;

    private String profile_img_original;

    private String gender;

    private String last_name;

    private String id;

    private String profile_img_compress;

    private String profile_img_thumbnail;

    private String first_name;

    private String social_uid;

    public String getEmail_id ()
    {
        return email_id;
    }

    public void setEmail_id (String email_id)
    {
        this.email_id = email_id;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getProfile_img_original ()
    {
        return profile_img_original;
    }

    public void setProfile_img_original (String profile_img_original)
    {
        this.profile_img_original = profile_img_original;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getProfile_img_compress ()
    {
        return profile_img_compress;
    }

    public void setProfile_img_compress (String profile_img_compress)
    {
        this.profile_img_compress = profile_img_compress;
    }

    public String getProfile_img_thumbnail ()
    {
        return profile_img_thumbnail;
    }

    public void setProfile_img_thumbnail (String profile_img_thumbnail)
    {
        this.profile_img_thumbnail = profile_img_thumbnail;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getSocial_uid ()
    {
        return social_uid;
    }

    public void setSocial_uid (String social_uid)
    {
        this.social_uid = social_uid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [email_id = "+email_id+", country = "+country+", profile_img_original = "+profile_img_original+", gender = "+gender+", last_name = "+last_name+", id = "+id+", profile_img_compress = "+profile_img_compress+", profile_img_thumbnail = "+profile_img_thumbnail+", first_name = "+first_name+", social_uid = "+social_uid+"]";
    }
}

