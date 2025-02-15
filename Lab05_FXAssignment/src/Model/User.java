package Model;

public class User {
    private String name;
    private int age;
    private String email;

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName(){
        return  name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{Name, " + name
                + " Email: "+ email 
                + " Age: "+ age
                + "}"; 
    }
}
