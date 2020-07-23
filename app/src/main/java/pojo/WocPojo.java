package pojo;

public class WocPojo {
    private String message;
    private String image;
    private String time;
    private String dateStamp;
    private int itemPosition;

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public static final int ADMIN = 0;
    public static final int USER = 1;
    public static final int TIME = 2;
    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public WocPojo(int viewType, String message, String image, String time,String dateStamp,int itemPosition) {
        this.viewType = viewType;
        this.itemPosition=itemPosition;
        this.message = message;
        this.dateStamp = dateStamp;
        this.image = image;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WocPojo{" +
                "message='" + message + '\'' +
                ", image='" + image + '\'' +
                ", time='" + time + '\'' +
                '}';
    }



}
