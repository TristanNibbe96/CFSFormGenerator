package Main.Model;

import java.io.Serializable;

public class CaseModel implements Serializable {
    private String firstName;
    private String lastName;

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return this.lastName;
    }

    @Override
    public String toString(){

        return lastName + ", " + firstName;
    }

}
