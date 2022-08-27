import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.List;
import java.util.stream.StreamSupport;

public class ManageHR {
    public static final List<Engineer> engineersList = new Vector<>(0);
    //public static final List<Worker> workerList = new Vector<>(0);
    public static void importDataFile() throws IOException {
        File engineerData = new File("C:\\Users\\bannh\\IdeaProjects\\Manage Human Resources\\engineerData.txt");
       //File workerData = new File("C:\\Users\\bannh\\IdeaProjects\\Manage Human Resources\\workerData.txt");
        if (!engineerData.exists())
        {
            engineerData.createNewFile();
        }
        else{
            try (BufferedReader br = new BufferedReader(new FileReader(engineerData))){
                String line;
                String [] information = new String[6];
                while ((line = br.readLine())!=null)
                {
                    int i=0;
                    int stringIndex = 0;
                    for (int j = 0; j<line.length(); j++)
                    {
                        if(line.charAt(j)==' ') {
                            information[i] = String.copyValueOf(line.toCharArray(), stringIndex, j - stringIndex);
                            stringIndex = j + 1;
                            i++;
                        }
                    }
                    information[2]=information[2].trim();
                    information[4]=information[4].trim();
                    int gender = information[2].charAt(0) - '0';
                    int degree = information[4].charAt(0) - '0';
                    Engineer engineer = new Engineer(information[0],information[1],gender,information[3],degree,information[5]);
                    engineersList.add(engineer);
                }
            }
        }
    }
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void mainMenu(){
        System.out.println("Human Resources Management Tools");
        System.out.println("1. Add an Officer's profile\n2. Searching in list\n3. Show Officer list\n4. Delete an Officer's profile\n5. Exit");
    }
    public static void inputInformation() throws InterruptedException {
        clearScreen();
        System.out.println("Add an Officer's profile:\n1.Add an Engineer's profile\n2.Back to main menu");
        Scanner input = new Scanner(System.in);
        switch (input.nextInt())
        {
            case 1:
                clearScreen();
                System.out.print("Name: ");
                String name = input.next();
                System.out.print("Age: ");
                String age = input.next();
                System.out.print("Gender: 0.Male 1.Female 2.Others\n");
                int gender = input.nextInt();
                System.out.print("Salary: ");
                String salary = input.next();
                System.out.print("Degree: 0. Bachelor 1. Engineer 2. Master 3. Doctor\n");
                int degree = input.nextInt();
                System.out.print("University Graduation: ");
                String university = input.next();
                Engineer engineer = new Engineer(name,age,gender,salary,degree,university);
                engineersList.add(engineer);
                clearScreen();
                System.out.println("Profile added successfully.");
                Thread.sleep(2000);
                clearScreen();
                mainMenu();
                break;
            case 2:
            case 3:
                clearScreen();
                mainMenu();
                break;
        }
    }
    public static void searchInformation() throws InterruptedException {
        clearScreen();
        boolean searching = true;
        while (searching) {
            Scanner input = new Scanner(System.in);
            System.out.print("Searching by name: ");
            String line = input.next();
            Engineer results = null;
            boolean founded = false;
            for (Engineer i : engineersList) {
                if (line.contentEquals(i.name)) {
                    i.getInformation();
                    founded = true;
                    results = i ;
                    break;
                }
            }
            if (!founded) {
                System.out.println("Results not found");
            }
            System.out.println("1. Continue Searching\n2. Edit information \n0. Back to main menu");
            try {
                switch (input.nextInt()) {
                    case 0:
                        searching = false;
                        break;
                    case 2:
                        editInformation(results);
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input");
                System.out.println("Back to main menu");
                searching = false;
            }
        }
        mainMenu();
    }
    public static void editInformation (Engineer engineer) throws InterruptedException {
        boolean editing = true;
        Scanner input = new Scanner(System.in);
        while (editing){
            System.out.println("What do you want to edit?\n1. Name\n2. Gender\n3. Age\n4. Salary\n5. Degree\n6. University\n0. Exit Editing");
            try {
                switch (input.nextInt()) {
                    default:
                        editing = false;
                        break;
                    case 1:
                        System.out.println("Change Officer's name to: ");
                        engineer.setName(input.next());
                        break;
                    case 2:
                        System.out.println("Change Officer's Gender to: 1.Male 2.Female 3.Others");
                        if (input.nextInt() == 1) {
                            engineer.setGender(Gender.Male);
                        } else if (input.nextInt() == 2) {
                            engineer.setGender(Gender.Female);
                        } else {
                            engineer.setGender(Gender.Others);
                        }
                        break;
                    case 3:
                        System.out.println("Change Officer's age to: ");
                        engineer.setAge(input.next());
                        break;
                    case 4:
                        System.out.println("Change Officer's salary to: ");
                        engineer.setSalary(input.next());
                        break;
                    case 5:
                        System.out.println("Change Officer's Degree to: 1.Bachelor 2.Engineer 3.Master 4.Doctor");
                        if (input.nextInt() == 1) {
                            engineer.setDegree(Degree.Bachelor);
                        } else if (input.nextInt() == 2) {
                            engineer.setDegree(Degree.Engineer);
                        } else if (input.nextInt() == 3) {
                            engineer.setDegree(Degree.Master);
                        } else {
                            engineer.setDegree(Degree.Doctor);
                        }
                        break;
                    case 6:
                        System.out.println("Change Officer's university to: ");
                        engineer.setUniversity(input.next());
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Your input is invalid");
                System.out.println("Back to main menu...");
                Thread.sleep(1000);
                editing = false;
            }
            if (editing){
                System.out.println("Information Edited Successfully\n1. Continue Edit\n0. Exit Editing");
                if(input.nextInt()!=1){
                    editing=false;
                }
            }
        }
    }
    public static void showList() throws InterruptedException {
        int j=1;
        Scanner input = new Scanner(System.in);
        System.out.println("No. |  Name  | Age | Gender ");
        for (Engineer i : engineersList){
            System.out.println(j + ". "+i.getOfficerName()+"  | " +i.getOfficerAge()+" | "+i.getOfficerGender());
            j++;
        }
        System.out.println("\n1. Edit information\n2. Show Officer's Information\n3.Back to main menu");
        switch (input.nextInt()){
            case 1:
                boolean editting = true;
                while (editting) {
                    System.out.println("Choose Officer to Edit (Input Officer's Index): ");
                    try {
                        editting = false;
                        editInformation(engineersList.get(input.nextInt() - 1));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Out of Bounds Index. Try again");
                        editting = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Input must be an integer. Try again");
                        editting = true;
                        input.next();
                    }
                }
                mainMenu();
                break;
            case 2:
                System.out.println("Choose Officer to show information (Input Officer's Index): ");
                engineersList.get(input.nextInt()-1).getInformation();
                System.out.println("0. Back to main menu");
                if (input.nextInt()==0){

                }
                mainMenu();
                break;
            default:
                clearScreen();
                mainMenu();
                break;
        }
    }
    public static void deleteInformation(){
        Scanner input = new Scanner(System.in);
        boolean deleting = true;
        while (deleting){
            System.out.println("Input officer's Index to delete: ");
            int index = input.nextInt();
            engineersList.remove(index);
            System.out.println("Delete successfully. Continue?\n1.Yes 2.No");
            if (input.nextInt()==2){
                deleting = false;
            }
        }
        mainMenu();
    }
    public static void updateDataFile() throws IOException {
        File engineerData = new File("C:\\Users\\bannh\\IdeaProjects\\Manage Human Resources\\engineerData.txt");
        FileWriter fileWriter = new FileWriter(engineerData.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(Engineer i : engineersList){
            i.importInformationToFile(bufferedWriter);
        }
        bufferedWriter.close();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        importDataFile();
        Scanner input = new Scanner(System.in);
        mainMenu();
        int exitStatus = 0;
        while (exitStatus==0)
        {
            switch (input.nextInt())
            {
                case 1:
                    inputInformation();
                    break;
                case 2:
                    searchInformation();
                    break;
                case 3:
                    showList();
                    break;
                case 4:
                    deleteInformation();
                    break;
                case 5:
                    updateDataFile();
                    exitStatus=1;
                    break;
            }
        }
    }
}
