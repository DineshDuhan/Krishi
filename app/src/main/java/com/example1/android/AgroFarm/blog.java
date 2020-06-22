package com.example1.android.AgroFarm;

public class blog {
    private String Name,City,Crop,Rate,Contact;
public blog(){

}
    public blog(String Name, String City, String Crop,String Rate,String Contact) {
        this.Name = Name;
        this.City = City;
        this.Crop = Crop;
        this.Rate = Rate;
        this.Contact = Contact;

    }
    public String getName() {

        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getCity() {

        return City;
    }
    public void setCity(String City) {
        this.City = City;
    }
    public String getCrop() {

        return Crop;
    }
    public void setCrop(String Crop) {
        this.Crop = Crop;
    }
    public String getRate() {

        return Rate;
    }
    public void setRate(String Rate) {
        this.Rate = Rate;
    }
    public String getContact() {

        return Contact;
    }
    public void setContact(String Contact) {
        this.Contact = Contact;
    }
}
