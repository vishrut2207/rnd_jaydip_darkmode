package pojo;

public class OTPResponse{
	private int code;
	private OTPData data;
	private String cause;
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(OTPData data){
		this.data = data;
	}

	public OTPData getData(){
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
			"OTPResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",cause = '" + cause + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
