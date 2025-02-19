package com.thefivebros.fivebros.model;

public class Profile {

    private int userId;
    private String name;
    private String aboutMe;
    private String email;
    private String phone;
    private String image;
    public Profile(int userId, String aboutMe, String email, String phone, String image) {
        this.userId = userId;
        this.aboutMe = aboutMe;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public Profile() {}

    public Profile(int userId, String name, String aboutMe, String email, String phone, String image) {
        this.userId = userId;
        this.name = name;
        this.aboutMe = aboutMe;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    // Getters and Setters
    public int getId() { return userId; }
    public void setId(int id) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAboutMe() { return aboutMe; }
    public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}

