package pojo;

public class UserDataPojo {


    private String age;
    private String name;
    private String email;
    private String gender;
    private String id;
    private String image;
    private String phone_no;

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public UserDataPojo() {

    }

    public UserDataPojo(String id, String name, String email, String age, String gender, String image) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.image = image;
        this.id = id;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
