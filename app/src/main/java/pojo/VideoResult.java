package pojo;

public class VideoResult {
    private String thumnail_image;

    private String original_video;

    public String getThumnail_image ()
    {
        return thumnail_image;
    }

    public void setThumnail_image (String thumnail_image)
    {
        this.thumnail_image = thumnail_image;
    }

    public String getOriginal_video ()
    {
        return original_video;
    }

    public void setOriginal_video (String original_video)
    {
        this.original_video = original_video;
    }

    @Override
    public String toString()
    {
        return original_video;
    }
}
