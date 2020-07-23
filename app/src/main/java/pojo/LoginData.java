package pojo;

public class LoginData
{
    private User_details user_details;

    private String token;

    public User_details getUser_details ()
    {
        return user_details;
    }

    public void setUser_details (User_details user_details)
    {
        this.user_details = user_details;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [user_details = "+user_details+", token = "+token+"]";
    }
}

