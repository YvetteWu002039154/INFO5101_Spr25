package model;

public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String email;
    private int phoneNumber;
    private String hobby;

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String name){
        this.firstName = name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHobby() {
        return hobby;
    }
    
    public void setHobby(String hobby){
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{Name, " + firstName
                + ", "+lastName
                + " Phone Number: "+ phoneNumber
                + " Email: "+ email 
                + " Age: "+ age
                + " Gender: "+ gender
                + " with Hobbies: "+ hobby + "}"; 
    }
}
