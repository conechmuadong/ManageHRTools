public class Officer {
    protected String name;
    protected String age;
    protected Gender gender;
    protected String salary;
    protected static int total;
    //protected static int totalSalary;
    protected Officer(String name, String age, int gender, String salary){
        this.total+=1;
        this.name = name;
        this.age = age;
        this.salary = salary;
        switch (gender) {
            case 0:
                this.gender = Gender.Male;
                break;
            case 1:
                this.gender = Gender.Female;
                break;
            default:
                this.gender = Gender.Others;
                break;
        }
        //this.totalSalary+=this.salary;
    }
    protected String getOfficerName(){
        return this.name;
    }
    protected Gender getOfficerGender(){
        return this.gender;
    }
    protected String getOfficerAge(){
        return this.age;
    }
    protected void setInformation(String name, String age, Gender gender, String salary){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected void setAge(String age){
        this.age = age;
    }
    protected void setGender(Gender gender){
        this.gender = gender;
    }
    protected void setSalary(String salary){
        this.salary = salary;
    }
}
