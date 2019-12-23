
package pojos;

public class Student {
    int id;
    String fname,email,pass;

    public Student(){
        
    }
    public Student(String fname,String email,String pass){
        this.fname = fname;
        this.email = email;
        this.pass = pass;
    }
    public Student(int id, String fname, String email, String pass) {
        this.id = id;
        this.fname = fname;
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
