package pojo;

public class RegistrationResponse
{
	private String code;

	private RData data;

	private String cause;

	private String message;

	public String getCode ()
	{
		return code;
	}

	public void setCode (String code)
	{
		this.code = code;
	}

	public RData getData ()
	{
		return data;
	}

	public void setData (RData data)
	{
		this.data = data;
	}

	public String getCause ()
	{
		return cause;
	}

	public void setCause (String cause)
	{
		this.cause = cause;
	}

	public String getMessage ()
	{
		return message;
	}

	public void setMessage (String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "[code = "+code+", data = "+data+", cause = "+cause+", message = "+message+"]";
	}
}

/*
public class RegistrationResponse{
	private int code;
	private Data data;
	private String cause;
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setCause(String cause){
		this.cause = cause;
	}

	public String getCause(){
		return cause;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"RegistrationResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' +
			",cause = '" + cause + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
*/
