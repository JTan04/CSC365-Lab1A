import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.lang.Object;
import java.lang.String;
import java.util.stream.Collectors;

import static java.lang.reflect.Array.getLength;

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

   public static void filterStudentByBus(List<Student> students, String lastName){
      List<Student> sorted = students.stream()
            .filter(p -> (p.getLastName().equals(lastName)))
            .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++){
         System.out.println(sorted.get(i).getLastName() + sorted.get(i).getFirstName() + sorted.get(i).getBus());
      }
   }

   public static void filterStudent(List<Student> students, String lastName){
      List<Student> sorted = students.stream()
              .filter(p -> (p.getLastName().equals(lastName)))
              .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++){
         System.out.println(sorted.get(i).getLastName() + sorted.get(i).getFirstName() + sorted.get(i).getGrade() +
               sorted.get(i).getClassroom() + sorted.get(i).getTLastName() + sorted.get(i).getTFirstName());
      }
   }

   public static void filterTeacher(List<Student> students, String lastName) {
      List<Student> sorted = students.stream()
              .filter(p -> (p.getTLastName().equals(lastName)))
              .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++) {
         System.out.println(sorted.get(i).getLastName() + sorted.get(i).getFirstName());
      }
   }
   public static void filterBus(List<Student> students, int number){
        List<Student> sorted = students.stream()
        .filter(p -> (p.getBus() == number))
        .collect(Collectors.toList());
        int i = 0;
        for (i = 0; i < sorted.size(); i++){
        System.out.println(sorted.get(i).getLastName() + sorted.get(i).getFirstName() + sorted.get(i).getGrade()
               + sorted.get(i).getClass());
        }
   }
   public static void filterGrade(List<Student> students, int number){
        List<Student> sorted = students.stream()
        .filter(p -> (p.getGrade() == number))
        .collect(Collectors.toList());
        int i = 0;
        for (i = 0; i < sorted.size(); i++){
        System.out.println(sorted.get(i).getLastName() + sorted.get(i).getFirstName() + sorted.get(i).getGPA()
                + sorted.get(i).getLastName() + sorted.get(i).getFirstName() + sorted.get(i).getBus());
        }
   }

   /*Need to remove print statements below for invalid input*/

   public static boolean processInput(String command, Scanner input, List<Student> students) {
      String[] parsedCommand = command.split(" ");
      int commandLength = getLength(parsedCommand);
      if (commandLength > 3){
         System.out.println("Invalid input\n");
         return false;
      }
      if (parsedCommand[0].equals("S") || parsedCommand[0].equals("Student")){
         if (commandLength == 3 && (parsedCommand[2].equals("B")
            || parsedCommand[2].equals("Bus"))){
            filterStudentByBus(students, parsedCommand[1]);
         }
         else if (commandLength == 2){
            filterStudent(students, parsedCommand[1]);
         }
         else{
            System.out.println("Invalid input\n");
         }
         return true;
      if (parsedCommand[0].equals("T") || parsedCommand[0].equals("Teacher")){
         if (commandLength != 2){
            System.out.println("Invalid input\n");
            return false;
         }
         filterTeacher(students, parsedCommand[1]);
         return true;
      }
      if (parsedCommand[0].equals("B") || parsedCommand[0].equals("Bus")){
         if (commandLength == 2){
            filterBus(students, parsedCommand[1]);
            return true;
         }
         else{
            System.out.println("Invalid input\n");
         }
      }

      if (parsedCommand[0].equals("G") || parsedCommand[0].equals("Grade")){
         if (commandLength != 2 && commandLength != 3){
            System.out.println("Invalid input\n");
            return false;
         }
         if (commandLength == 3 && parsedCommand[2].charAt(0) == 'H'){
            /*insert function here*/
         }
         else if (commandLength == 3 && parsedCommand[2].charAt(0) == 'L'){
            /*insert function here*/
         }
         else{
            filterGrade(students, Integer.parseInt(parsedCommand[1]));
            return true;
         }
      }
      /* case 'A':
            return true;
         case 'I':
            return true;
         case 'Q':
            return false;
         default:
            System.out.println("Invalid Command\n");
            return true;
      }*/
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
      } while(processInput(command, input, listOfStudents));
   }
}
