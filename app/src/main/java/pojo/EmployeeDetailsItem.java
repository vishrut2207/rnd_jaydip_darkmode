package pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class EmployeeDetailsItem {

    @SerializedName("address")
    private String address;


    @SerializedName("gender")
    private String gender;


    @SerializedName("friends")
    private List<FriendsItem> friends;

    @SerializedName("tags")
    private List<String> tagss;

    @SerializedName("favoriteFruit")
    private String favoriteFruit;

    @SerializedName("balance")
    private String balance;

    @SerializedName("eyeColor")
    private String eyeColor;

    @SerializedName("phone")
    private String phone;

    @SerializedName("name")
    private String name;


    @SerializedName("company")
    private String company;


    @SerializedName("email")
    private String email;

    @SerializedName("image")
    private String image;

    @SerializedName("index")
    private int index;

    @SerializedName("url")
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public EmployeeDetailsItem(String image) {
        this.image = image;


    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }


    public void setFriends(List<FriendsItem> friends) {
        this.friends = friends;
    }

    public List<FriendsItem> getFriends() {
        return friends;
    }

    public void setTags(List<String> tagss) {
        this.tagss = tagss;
    }

    public List<String> getTagss() {
        return tagss;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance() {
        return balance;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return
                "EmployeeDetailsItem{" +
                        "address = '" + address + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",friends = '" + friends + '\'' +
                        ",tags = '" + tagss + '\'' +
                        ",favoriteFruit = '" + favoriteFruit + '\'' +
                        ",balance = '" + balance + '\'' +
                        ",eyeColor = '" + eyeColor + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",name = '" + name + '\'' +
                        ",company = '" + company + '\'' +
                        ",email = '" + email + '\'' +
                        ",image = '" + image + '\'' +
                        "}";
    }
}