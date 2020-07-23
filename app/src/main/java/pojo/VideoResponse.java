package pojo;

public class VideoResponse
{
    private String code;

    private VideoData data;

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

    public VideoData getData ()
    {
        return data;
    }

    public void setData (VideoData data)
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
        return "ClassPojo [code = "+code+", data = "+data+", cause = "+cause+", message = "+message+"]";
    }
}
