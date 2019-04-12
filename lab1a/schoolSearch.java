import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.lang.Object;
import java.lang.String;
import java.util.stream.Collectors;

import static java.lang.reflect.Array.getLength;

/* Lab01 Part 1 main file
 * contributors: Julian Tan, Connor Alvin, Michelle Jakab
 */

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
      System.out.print("S[tudent]: <lastname> [B[us]]\nT[eacher]: <lastname>\nB[us]: <number>\nG[rade]: <number> [H[igh]|L[ow]|T[eacher]]\nC[lassroom]: <number> S[tudent]|T[eacher]\nA[verage]: <number>\nD[ata]: G[rade]|T[eacher]|B[us]\nI[nfo]\nE[nrollment]\nQ[uit]\n");
      System.out.println("*Bracketed portions are optional\n");
      return;
   }

   public static void filterStudentByBus(List<Student> students, String lastName){
      List<Student> sorted = students.stream()
            .filter(p -> (p.getLastName().equals(lastName)))
            .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++){
         System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName() + "," + sorted.get(i).getBus());
      }
      System.out.println();
   }

   public static void filterStudent(List<Student> students, String lastName){
      List<Student> sorted = students.stream()
              .filter(p -> (p.getLastName().equals(lastName)))
              .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++){
         System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName() + "," + sorted.get(i).getGrade() +
               "," + sorted.get(i).getClassroom() + "," + sorted.get(i).getTLastName() + "," + sorted.get(i).getTFirstName());
      }
      System.out.println();
   }

   public static void filterTeacher(List<Student> students, String lastName) {
      List<Student> sorted = students.stream()
              .filter(p -> (p.getTLastName().equals(lastName)))
              .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++) {
         System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName());
      }
      System.out.println();
   }
   public static void filterBus(List<Student> students, int number){
        List<Student> sorted = students.stream()
        .filter(p -> (p.getBus() == number))
        .collect(Collectors.toList());
        int i = 0;
        for (i = 0; i < sorted.size(); i++){
            System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName() + "," + sorted.get(i).getGrade()
               + "," + sorted.get(i).getClassroom());
        }
        System.out.println();
   }
   public static void filterGrade(List<Student> students, int number){
        List<Student> sorted = students.stream()
        .filter(p -> (p.getGrade() == number))
        .collect(Collectors.toList());
        int i = 0;
        for (i = 0; i < sorted.size(); i++){
            System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName());
        }
        System.out.println();
   }

   /*Need to remove print statements below for invalid input*/

   public static boolean processInput(String command, Scanner input, List<Student> students) {
      String[] parsedCommand = command.split(" ");
      int commandLength = getLength(parsedCommand);
      if (parsedCommand[0].equals("S:") || parsedCommand[0].equals("Student:")){
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
      }
      if (parsedCommand[0].equals("T:") || parsedCommand[0].equals("Teacher:")){
         if (commandLength != 2){
            System.out.println("Invalid input\n");
         } else {
            filterTeacher(students, parsedCommand[1]);
         }
         return true;
      }
      if (parsedCommand[0].equals("B:") || parsedCommand[0].equals("Bus:")){
         if (commandLength == 2){
            filterBus(students, Integer.parseInt(parsedCommand[1]));
         }
         else{
            System.out.println("Invalid input\n");
         }
         return true;
      }

      if (parsedCommand[0].equals("G:") || parsedCommand[0].equals("Grade:")){
         if (commandLength != 2 && commandLength != 3){
            System.out.println("Invalid input\n");
         }
         if (commandLength == 3){
            /*insert function here*/
            getGradeHighLow(students, Integer.parseInt(parsedCommand[1]),
               parsedCommand[2].charAt(0));
         }
         else{
            filterGrade(students, Integer.parseInt(parsedCommand[1]));
         }
         return true;
      }

      if (parsedCommand[0].equals("A:") || parsedCommand[0].equals("Average:")){
         if(commandLength == 2){
            getAverage(students, Integer.parseInt(parsedCommand[1]));
         }
         else{
            System.out.println("Invalid input\n");
         }
         return true;
      }
      if(parsedCommand[0].equals("D:") || parsedCommand[0].equals("Data:")) {
         if(commandLength == 2) {
         }
         else {
            System.out.println("Invalid Input\n");
         }
         return true;
      }
      if (parsedCommand[0].equals("I") || parsedCommand[0].equals("Info")){
         if(commandLength == 1){
            getInfo(students);
         }
         else{
            System.out.println("Invalid input\n");
         }
         return true;
      }

      if (parsedCommand[0].equals("Q") || parsedCommand[0].equals("Quit")){
         return false;
      }
      return true;
   }

   public static void getGradeHighLow(List<Student> listOfStudents, int grade, char c){
      Student max = null;
      Student min = null;
      Student print = null;
      double maxGPA = 0;
      double minGPA = 5;
      for(Student s : listOfStudents){
         if(s.getGrade() == grade){
            if(maxGPA < s.getGPA()){
               maxGPA = s.getGPA();
               max = s;
            }
            if(minGPA > s.getGPA()){
               minGPA = s.getGPA();
               min = s;
            }
         }
      }
      if(c == 'H'){
         print = max;
      }else if(c == 'L'){
         print = min;
      }
      if(print != null){
         System.out.println(print.getLastName() + "," + print.getFirstName()
         + "," + print.getGrade() + "," + print.getBus() + "," + print.getGPA() + "," + print.getTLastName() + "," + print.getTFirstName());
         System.out.println();
      }
   }

   public static void getAverage(List<Student> listOfStudents, int grade){
      double average = 0.0;
      int count = 0;
      for(Student s : listOfStudents){
         if(s.getGrade() == grade){
            average += s.getGPA();
            count++;
         }
      }
      average = average/count;
      System.out.println("Grade: " + grade + ", Average GPA: " + String.format("%.2f", average));
      System.out.println();
   }

   public static void getInfo(List<Student> listOfStudents){
      int g0 = 0;
      int g1 = 0;
      int g2 = 0;
      int g3 = 0;
      int g4 = 0;
      int g5 = 0;
      int g6 = 0;
      for(Student s : listOfStudents){
         switch(s.getGrade()){
            case 0:
               g0++;
               break;
            case 1:
               g1++;
               break;
            case 2:
               g2++;
               break;
            case 3:
               g3++;
               break;
            case 4:
               g4++;
               break;
            case 5:
               g5++;
               break;
            case 6:
               g6++;
               break;
            default:
               break;
         }
      }
      System.out.println("0: " + g0);
      System.out.println("1: " + g1);
      System.out.println("2: " + g2);
      System.out.println("3: " + g3);
      System.out.println("4: " + g4);
      System.out.println("5: " + g5);
      System.out.println("6: " + g6);
   }

   public static void main(String[] args) throws IOException{
      File file = new File("students.txt");
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
