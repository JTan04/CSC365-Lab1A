import java.util.*;
import java.io.*;
import java.lang.String;
import java.util.stream.Collectors;

import static java.lang.reflect.Array.getLength;

public class schoolSearch{
   public static class Teacher{
      String TLastName;
      String TFirstName;
      int Classroom;
      public Teacher(String TLastName, String TFirstName, int Classroom){
         this.TLastName = TLastName;
         this.TFirstName = TFirstName;
         this.Classroom = Classroom;
      }

      public String getTLastName(){
         return this.TLastName;
      }

      public String getTFirstName(){
         return this.TFirstName;
      }

      public int getClassroom(){
         return this.Classroom;
      }
   }
   public static class Student{
      String StLastName;
      String StFirstName;
      int Grade;
      int Classroom;
      int Bus;
      double GPA;
      public Student(String StLastName, String StFirstName, int Grade,
                     int Classroom, int Bus, double GPA){
         this.StLastName = StLastName;
         this.StFirstName = StFirstName;
         this.Grade = Grade;
         this.Classroom = Classroom;
         this.Bus = Bus;
         this.GPA = GPA;
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

   public static String getTeacherLastName(Student student, List<Teacher> teachers) {
      for(int i = 0; i < teachers.size(); i++) {
         if(teachers.get(i).getClassroom() == student.getClassroom()) {
            return teachers.get(i).getTLastName();
         }
      }
      return "";
   }
   public static String getTeacherFirstName(Student student, List<Teacher> teachers) {
      for(int i = 0; i < teachers.size(); i++) {
         if(teachers.get(i).getClassroom() == student.getClassroom()) {
            return teachers.get(i).getTFirstName();
         }
      }
      return "";
   }
   public static void filterStudent(List<Student> students, List<Teacher> teachers, String lastName){
      List<Student> sorted = students.stream()
              .filter(p -> (p.getLastName().equals(lastName)))
              .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++){
         System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName() + "," + sorted.get(i).getGrade() +
               "," + sorted.get(i).getClassroom() + "," + getTeacherLastName(sorted.get(i), teachers) + "," + getTeacherFirstName(sorted.get(i), teachers));
      }
      System.out.println();
   }

   public static void filterTeacher(List<Student> students, List<Teacher> teachers, String lastName) {
      List<Student> sorted = students.stream()
              .filter(p -> (getTeacherLastName(p, teachers).equals(lastName)))
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

   public static void filterGradeTeacher(List<Student> students, List<Teacher> teachers, int number){
      List<Student> sorted = students.stream()
              .filter(p -> (p.getGrade() == number))
              .collect(Collectors.toList());
      List<Teacher> newlist = new ArrayList<>();
      int i;
      for (i = 0; i < sorted.size(); i++){
         newlist.add(new Teacher(sorted.get(i).getLastName(), sorted.get(i).getFirstName(), 0));
      }
      List<Teacher> output = newlist.stream()
              .distinct().collect(Collectors.toList());
      for (i = 0; i < sorted.size(); i++){
         System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName());
      }
   }

   public static void sortEnrollment(List<Student> students) {
      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
      int i;
      int num;
      for (i = 0; i < students.size(); i++){
         num = map.getOrDefault(students.get(i).getClassroom(), 0);
         map.put(students.get(i).getClassroom(), num + 1);
      }
      int j = 0;
      /*int room;
      int enrolled;*/
      List<Map.Entry<Integer, Integer>> mapEntries = new ArrayList<>();
      /*List<Integer> rooms = new ArrayList<>();
      List<Integer> enrollments = new ArrayList<>();*/
      Set<Map.Entry<Integer, Integer>> set = map.entrySet();
      Iterator<Map.Entry<Integer, Integer>> entries = set.iterator();
      while (entries.hasNext()) {
         Map.Entry<Integer, Integer> entry = entries.next();
         mapEntries.add(entry);
         /*rooms.add(entry.getKey());
         enrollments.add(entry.getValue());*/
         j++;
      }
      Comparator<Map.Entry<Integer, Integer>> comp = Comparator.comparingInt(Map.Entry::getKey);
      mapEntries.sort(comp);
      for (i = 0; i < j; i++){
         System.out.println(mapEntries.get(i).getKey() + ", " + mapEntries.get(i).getValue());
      }
   }

   public static void filterClassroomStudent(List<Student> students, List<Teacher> teachers, int number){
      List<Student> sorted = students.stream()
              .filter(p -> (p.getGrade() == number))
              .collect(Collectors.toList());
      int i = 0;
      for (i = 0; i < sorted.size(); i++){
         System.out.println(sorted.get(i).getLastName() + "," + sorted.get(i).getFirstName());
      }
      System.out.println();
   }
   public static ArrayList<Integer> getClassrooms(List<Student> sorted) {
      ArrayList<Integer> classrooms = new ArrayList<Integer>();
      for(Student s : sorted) {
         if(!(classrooms.contains(s.getClassroom()))) {
            classrooms.add(s.getClassroom());
         }
      }
      return classrooms;
   }
   public static void filterClassroomTeacher(List<Student> students, List<Teacher> teachers, int number){
      List<Student> sorted = students.stream()
              .filter(p -> (p.getGrade() == number))
              .collect(Collectors.toList());
      int i = 0;
      ArrayList<Integer> classrooms = getClassrooms(sorted);
      for (i = 0; i < teachers.size(); i++){
         if(classrooms.contains(teachers.get(i).getClassroom())) {
            System.out.println(teachers.get(i).getTLastName() + "," + teachers.get(i).getTFirstName());
         }
      }
      System.out.println();
   }

   public static ArrayList<Integer> getBusRoutes(List<Student> students) {
      ArrayList<Integer> buses = new ArrayList<Integer>();
      for(Student s : students) {
         if(!buses.contains(s.getBus())) {
            buses.add(s.getBus());
         }
      }
      return buses;
   }

   /*Need to remove print statements below for invalid input*/

   public static boolean processInput(String command, Scanner input, List<Student> students, List<Teacher> teachers) {
      String[] parsedCommand = command.split(" ");
      int commandLength = getLength(parsedCommand);
      if (parsedCommand[0].equals("S:") || parsedCommand[0].equals("Student:")){
         if (commandLength == 3 && (parsedCommand[2].equals("B")
            || parsedCommand[2].equals("Bus"))){
            filterStudentByBus(students, parsedCommand[1]);
         }
         else if (commandLength == 2){
            filterStudent(students, teachers, parsedCommand[1]);
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
            filterTeacher(students, teachers, parsedCommand[1]);
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
         if (commandLength == 3 && (parsedCommand[2].equals("T") || parsedCommand[2].equals("Teacher"))){
            filterGradeTeacher(students, teachers, Integer.parseInt(parsedCommand[1]));
         }
         else if (commandLength == 3){
            /*insert function here*/
            getGradeHighLow(students, teachers, Integer.parseInt(parsedCommand[1]),
               parsedCommand[2].charAt(0));
         }
         else{
            filterGrade(students, Integer.parseInt(parsedCommand[1]));
         }
         return true;
      }

      if (parsedCommand[0].equals("E") || parsedCommand[0].equals("Enrollment")){
         if (commandLength != 1){
            System.out.println("Invalid Input");
         }
         else{
            sortEnrollment(students);
         }
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
      if(parsedCommand[0].equals("C:") || parsedCommand[0].equals("Classroom:")) {
         if (commandLength != 3){
            System.out.println("Invalid input\n");
            return true;
         }
         if (parsedCommand[2].equals("S") || parsedCommand[2].equals("Student")){
            filterClassroomStudent(students, teachers, Integer.parseInt(parsedCommand[1]));
         }
         if (parsedCommand[2].equals("T") || parsedCommand[2].equals("Teacher")){
            filterClassroomTeacher(students, teachers, Integer.parseInt(parsedCommand[1]));
         }
         return true;
      }
      if(parsedCommand[0].equals("D:") || parsedCommand[0].equals("Data:")) {
         if(commandLength == 2) {
            getData(students, teachers, parsedCommand[1]);
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

   public static void getData(List<Student> students, List<Teacher> teachers, String type) {
      if(type.equals("G") || type.equals("Grade")) {
         double sum = 0.0;
         int count = 0;
         for(int i = 0; i < 7; i++) {
            for(Student s : students) {
               if(s.getGrade() == i) {
                  sum += s.getGPA();
                  count++;
               }
            }
            if(count > 0) {
               System.out.println("Grade " + i + ": Average GPA - " + String.format("%.2f", sum/count));
            }
            else {
               System.out.println("Grade " + i + ": No Students");
            }
            sum = 0;
            count = 0;
         }
         System.out.println();
      }
      else if(type.equals("T") || type.equals("Teacher")) {
         double sum = 0.0;
         int count = 0;
         for(Teacher t : teachers) {
            for(Student s : students) {
               if(t.getClassroom() == s.getClassroom()) {
                  sum += s.getGPA();
                  count++;
               }
            }
            System.out.println(t.getTLastName() + ": Average GPA - " + String.format("%.2f", sum/count));
            sum = 0;
            count = 0;
         }
         System.out.println();
      }
      else if(type.equals("B") || type.equals("Bus")) {
         System.out.println("Data Bus");
         ArrayList<Integer> buses = getBusRoutes(students);
         double sum = 0.0;
         int count = 0;
         for(Integer i : buses) {
            for(Student s : students) {
               if(s.getBus() == i) {
                  sum += s.getGPA();
                  count++;
               }
            }
            if(i == 0) {
               System.out.println("No Bus: Average GPA - " + String.format("%.2f", sum/count));
            }
            else {
               System.out.println("Bus " + i + ": Average GPA - " + String.format("%.2f", sum/count));
            }
            sum = 0;
            count = 0;
         }
         System.out.println();
      }
   }
   public static void getGradeHighLow(List<Student> listOfStudents, List<Teacher> teachers, int grade, char c){
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
         + "," + print.getGrade() + "," + print.getBus() + "," + print.getGPA() + "," + getTeacherLastName(print, teachers) + "," + getTeacherFirstName(print, teachers));
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
      File file = new File("list.txt");
      File file2 = new File("teachers.txt");
      Scanner sc = new Scanner(file), input = new Scanner(System.in);
      Scanner sc2 = new Scanner(file2);
      boolean run = true;
      String command;
      List<Student> listOfStudents = new ArrayList<>();
      List<Teacher> listOfTeachers = new ArrayList<>();

      while(sc.hasNextLine()){
         String studentInfo = sc.nextLine();
         studentInfo = studentInfo.replaceAll("\\s+", "");
         String[] infoList = studentInfo.split(",");

         String stLast = infoList[0];
         String stFirst = infoList[1];
         int grade = Integer.parseInt(infoList[2]);
         int classroom = Integer.parseInt(infoList[3]);
         int bus = Integer.parseInt(infoList[4]);
         double gpa = Double.parseDouble(infoList[5]);

         Student stud = new Student(stLast, stFirst, grade, classroom, bus, gpa);
         listOfStudents.add(stud);
      }

      while(sc2.hasNextLine()){
         String teacherInfo = sc2.nextLine();
         teacherInfo = teacherInfo.replaceAll("\\s+", "");
         String[] infoList = teacherInfo.split(",");

         String tLast = infoList[0];
         String tFirst = infoList[1];
         int classroom = Integer.parseInt(infoList[2]);

         Teacher teach = new Teacher(tLast, tFirst, classroom);
         listOfTeachers.add(teach);
      }
      do {
         printPrompt();
         command = input.nextLine();
      } while(processInput(command, input, listOfStudents, listOfTeachers));
   }
}
