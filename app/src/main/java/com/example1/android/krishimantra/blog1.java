package com.example1.android.krishimantra;

public class blog1 {
    private String Name,City,Vehicle,Capacity,Rate,Contact;
    public blog1(){

    }
    public blog1(String Name, String City, String Capacity,String Vehicle,String Rate,String Contact) {
        this.Name = Name;
        this.City = City;
        this.Capacity = Capacity;
        this.Rate = Rate;
        this.Contact = Contact;
        this.Vehicle = Vehicle;

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
    public String getCapacity() {

        return Capacity;
    }
    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
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

    public String getVehicle() {

        return Vehicle;
    }
    public void setVehicle(String Vehicle) {
        this.Vehicle = Vehicle;
    }
}
