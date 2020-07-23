package pojo;

public class VideoData {
    private VideoResult result;

    public VideoResult getResult ()
    {
        return result;
    }

    public void setResult (VideoResult result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return String.valueOf(result);
    }
}
