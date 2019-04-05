import java.util.*;
import java.io.*;

public class schoolSearch{
   public static class Student{
      String StLastName;
      String StFirstName;
      int Grade;
      int Classroom;
      int Bus;
      double GPA;
      String TLastName;
      String TFirstName;
      public Student(String StLastName, String StFirstName, int Grade,
                     int Classroom, int Bus, double GPA, String TLastName,
                     String TFirstName){
         this.StLastName = StLastName;
         this.StFirstName = StFirstName;
         this.Grade = Grade;
         this.Classroom = Classroom;
         this.Bus = Bus;
         this.GPA = GPA;
         this.TLastName = TLastName;
         this.TFirstName = TFirstName;
      }

      public String getLastName(){
         return this.StLastName;
      }

      public String getFirstName(){
         return this.StFirstName;
      }

      public int getGrade(){
         return this.Grade;
      }

      public int getClassroom(){
         return this.Classroom;
      }

      public int getBus(){
         return this.Bus;
      }

      public double getGPA(){
         return this.GPA;
      }

      public String getTLastName(){
         return this.TLastName;
      }

      public String getTFirstName(){
         return this.TFirstName;
      }
   }

   public static void printPrompt() {
      System.out.println("Enter one of the following commands: ");
      System.out.print("S[tudent]: <lastname> [B[us]]\nT[eacher]: <lastname>\nB[us]: <number>\nG[rade]: <number> [H[igh]|L[ow]]\nA[verage]: <number>\nI[nfo]\nQ[uit]\n");
      System.out.println("*Bracketed portions are optional");
      return;
   }
   public static boolean processInput(String command, Scanner input) {
      switch(command.charAt(0)) {
         case 'S':
            System.out.println("Student\n");
            return true;
         case 'T':
            System.out.println("Teacher\n");
            return true;
         case 'B':
            System.out.println("Bus\n");
            return true;
         case 'G':
            System.out.println("Grade\n");
            return true;
         case 'A':
            System.out.println("Average\n");
            return true;
         case 'I':
            System.out.println("Info\n");
            return true;
         case 'Q':
            return false;
         default:
            System.out.println("Invalid Command\n");
            return true;
      }
   }

   public static void main(String[] args) throws IOException{
      File file = new File(args[0]);
      Scanner sc = new Scanner(file), input = new Scanner(System.in);
      boolean run = true;
      String command;
      List<Student> listOfStudents = new ArrayList<>();
      while(sc.hasNextLine()){
         String studentInfo = sc.nextLine();
         String[] infoList = studentInfo.split(",");

         String stLast = infoList[0];
         String stFirst = infoList[1];
         int grade = Integer.parseInt(infoList[2]);
         int classroom = Integer.parseInt(infoList[3]);
         int bus = Integer.parseInt(infoList[4]);
         double gpa = Double.parseDouble(infoList[5]);
         String tLast = infoList[6];
         String tFirst = infoList[7];

         Student stud = new Student(stLast, stFirst, grade, classroom, bus, gpa,
                           tLast, tFirst);
         listOfStudents.add(stud);
      }
      do {
         printPrompt();
         command = input.nextLine();
      } while(processInput(command, input));
   }
}
