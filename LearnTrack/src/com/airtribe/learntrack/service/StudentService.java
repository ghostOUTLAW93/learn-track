package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService extends Student {
    private static int studentCounter = 0;
    public static ArrayList<Student> students = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int serviceFeatureSelection;

    public void invokeStudentManagementService() {
        do {
            System.out.println("\n------------------------Student Management---------------------------");
            System.out.println("Please select the feature in Student Management");
            System.out.println("1. Add new student \n2. View all students \n3. Search Student by Id \n4. Deactivate a student" +
                    "\n5. Select different Management Service\n");
            serviceFeatureSelection = InputValidator.readInt(scanner, "");

            switch (serviceFeatureSelection) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    System.out.println("Please enter the ID to be searched.");
                    int id = InputValidator.readInt(scanner, "");
                    try {
                        searchStudentById(id);
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Please provide the Student Id to INACTIVATE the student.");
                    int inactiveId = scanner.nextInt();
                    try {
                        makeStudentInactive(inactiveId);
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Exiting Student Management Service.");
                    break;
                default:
                    System.out.println("INVALID INPUT OPTION!");
            }
        }while (serviceFeatureSelection != 5);
    }

    private void makeStudentInactive(int deactiveId) throws EntityNotFoundException {
        if (deactiveId <= studentCounter){
            students.get(deactiveId-1).setActive(false);
            System.out.println("Student with id " + deactiveId + " is now INACTIVE!");
            return;
        }
        throw new EntityNotFoundException("There is no student with ID " + deactiveId);
    }

    private void searchStudentById(int searchedId) throws EntityNotFoundException {
        int low = 0;
        int high = studentCounter -1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            Student student = students.get(mid);
            int studentId = student.getId();
            if (studentId == searchedId){
                System.out.println(student.toString());
                return;
            } else if (searchedId < studentId) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        throw new EntityNotFoundException("Student with ID " + searchedId + " not found.");
    }

    public static Student findStudentById(int id) throws EntityNotFoundException {
        if (id < 1 || id > studentCounter) {
            throw new EntityNotFoundException("Student with ID " + id + " not found.");
        }
        return students.get(id - 1);
    }

    public void addStudent() {
        Student student = new Student();
        collectStudentInfo(student);
        students.add(student);
        ++studentCounter;
        System.out.println("\n" + student.getFirstName() + " [Id number : " + studentCounter + "]" + " added successfully!");
    }

    private void collectStudentInfo(Student student) {
        student.setId(IdGenerator.getNextStudentId());
        System.out.print("Please enter first name -> ");
        student.setFirstName(scanner.next());
        System.out.print("Please enter last name -> ");
        student.setLastName(scanner.next());
        System.out.print("Please enter email id -> ");
        student.setEmail(scanner.next());
        System.out.print("Please enter batch -> ");
        student.setBatch(scanner.next());
        student.setActive(true);
    }

    public void viewAllStudents() {
        if (students.isEmpty()){
            System.out.println("\nNO STUDENT ENROLLED! ");
            return;
        }
        System.out.println("Here is the list of all the students : ");

        for (int i = 1; i <= studentCounter; i++){
            Student student = students.get(i - 1);
            System.out.println("\nStudent Id : " + i + "\n" + student.toString());        }
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Batch: " + this.getBatch() + ")";
    }

}
