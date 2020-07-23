package pojo;



public class RData
{
    private String user_reg_temp_id;

    public String getUser_reg_temp_id ()
    {
        return user_reg_temp_id;
    }

    public void setUser_reg_temp_id (String user_reg_temp_id)
    {
        this.user_reg_temp_id = user_reg_temp_id;
    }

    @Override
    public String toString()
    {
        return user_reg_temp_id;
    }
}
/*
public class Data{
	private User user;
	private String token;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return
			"Data{" +
			"user = '" + user + '\'' +
			",token = '" + token + '\'' +
			"}";
		}
}
*/

