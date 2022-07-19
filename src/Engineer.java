import java.io.*;

public class Engineer extends Officer{
    private Degree degree;
    private String university;
    public static int totalEngineer;
    public Engineer(String name, String age, int gender, String salary, int degree, String university){
        super(name,age,gender,salary);
        totalEngineer+=1;
        switch (degree)
        {
            case 0:
                this.degree=Degree.Bachelor;
                break;
            case 1:
                this.degree=Degree.Engineer;
                break;
            case 2:
                this.degree=Degree.Master;
                break;
            case 3:
                this.degree=Degree.Doctor;
        }
        this.university = university;
    }
    public void importInformationToFile(BufferedWriter bufferedWriter) throws IOException {
        String information = this.name+" "+this.age+" ";
        switch (this.gender){
            case Male:
                information = information + "0 ";
                break;
            case Female:
                information = information+ "1 ";
                break;
            case Others:
                information = information+ "2 ";
                break;
        }
        information = information + this.salary;
        switch (this.degree) {
            case Bachelor:
                information += " 0 ";
                break;
            case Engineer:
                information += " 1 ";
                break;
            case Doctor:
                information += " 3 ";
                break;
            case Master:
                information += " 2 ";
                break;
        }
        information+= this.university;
        information+= " \\n";
        bufferedWriter.write(information);
        bufferedWriter.newLine();
    }
    public void getInformation()
    {
        System.out.println("Name: "+this.name+"   Age: "+this.age+"    Gender: "+this.gender);
        System.out.println("Position: Engineer");
        System.out.println("Salary: " + this.salary);
        System.out.println("Degree: "+this.degree+" by "+this.university);
    }
    /* public void setInformation(String name, String age, Gender gender, String salary, Degree degree, String university ){
        super.setInformation(name, age, gender, salary);
        this.degree = degree;
        this.university = university;
    }*/
    public void setUniversity(String university){
        this.university = university;
    }
    public void setDegree(Degree degree){
        this.degree = degree;
    }
    public String getUniversity(){
        return this.university;
    }
    public Degree getDegree(){
        return this.degree;
    }
}
