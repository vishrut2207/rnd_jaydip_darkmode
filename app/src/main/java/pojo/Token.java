package pojo;

public class Token{
	private int code;
	private TokenData data;
	private String cause;
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(TokenData data){
		this.data = data;
	}

	public TokenData getData(){
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
			"Token{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",cause = '" + cause + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
