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

   public static void main(String[] args) throws IOException{
      File file = new File(args[0]);
      Scanner sc = new Scanner(file);
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
   }
}
