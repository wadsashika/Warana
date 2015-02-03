package com.cse.warana.utility.infoHolders;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/12/2014.
 */
public class Profile {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String linkedIn;
    private String gender;
    private String pic_url;
    private String title;
    private String blogUrl;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private String gitUrl;
    private ArrayList<String> urls = new ArrayList<>();

    public Profile() {
        name    ="";
        email   ="";
        phone   ="";
        address ="";
        linkedIn="";
        gender  ="";
        pic_url ="";
        title   ="";
        blogUrl ="";
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getName() {
        String[] split=name.split(" ");
        if (split.length>1)
            return split[0]+" "+split[1];
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object prof) {

        if (prof.getClass().getName()=="Profile"){
            Profile profile=(Profile)prof;
            if (this.name.contains(((Profile) profile).getName()) || ((Profile) profile).getName().contains(this.name)){
                if(profile.getTitle().length()>0 && this.title.length()>0 && !profile.getTitle().equals(this.name)){
                    return false;
                }
                if (this.blogUrl.length()>0 && profile.getBlogUrl().length()>0 && !this.blogUrl.equals(profile.getBlogUrl())){
                    return false;
                }
            }
        }

        return true;
    }
    public void copy(Profile p){
        if (name.length()==0){
            name= p.getName();
        }
        if (email.length()==0){
            email= p.getEmail();
        }
        if (phone.length()==0){
            phone= p.getPhone();
        }
        if (address.length()==0){
            address= p.getAddress();
        }
        if (linkedIn.length()==0){
            linkedIn= p.getLinkedIn();
        }
        if (gender.length()==0){
            gender= p.getGender();
        }
        if (pic_url.length()==0){
            pic_url= p.getPic_url();
        }
        if (blogUrl.length()==0){
            blogUrl= p.getBlogUrl();
        }
        if (title.length()==0){
            title= p.getTitle();
        }
    }
}