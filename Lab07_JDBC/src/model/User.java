package model;

public class User {
    private int userID;
    private String name;
    private String college;
    private int age;
    private String hobby;

    public int getUserId() {
        return userID;
    }
    
    public void setUserId(int userID) {
        this.userID = userID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public int  getAge() {
        return age;
    }
    
    public void setAge(int age){
        this.age = age;
    }

    public String getCollege() {
        return college;
    }
    
    public void setCollege(String college){
        this.college = college;
    }
    
    public String getHobby() {
        return hobby;
    }
    
    public void setHobby(String hobby){
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{Name, " + name 
                + " Age: "+ age
                + " with Hobbies: "+ hobby 
                + " to " + college + "}"; 
    }
}
