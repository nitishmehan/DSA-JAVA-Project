package models;

public class Passenger {
    private String passengerId;
    private String name;
    private int age;
    private String contactInfo;

    public Passenger(String passengerId, String name, int age, String contactInfo) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + passengerId + ", Name: " + name + ", Age: " + age + ", Contact Info: " + contactInfo;
    }
}