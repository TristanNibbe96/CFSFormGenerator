package Main.Model;


import java.io.*;
import java.util.ArrayList;

public class DataAccessor {

    private ArrayList<CaseModel> caseList = new ArrayList<CaseModel>();
    private final String savePath = "data.CFS";

    public DataAccessor(){
        LoadCasesFromFile();
    }

    public void AddNewCaseToCaseList(CaseModel newCase){
        caseList.add(newCase);
        SaveCasesToFile();
    }

    public void RemoveCaseFromList(CaseModel caseToRemove){
        caseList.remove(caseToRemove);
        SaveCasesToFile();
    }

    public ArrayList<CaseModel> getCaseList(){
        return caseList;
    }

    public void SaveCaseChanges(){
        SaveCasesToFile();
    }


    private void LoadCasesFromFile(){
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(savePath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            caseList = (ArrayList<CaseModel>) in.readObject();

            in.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }


    private void SaveCasesToFile(){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(savePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(caseList);

            out.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
