package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int managementServiceSelection;
        StudentService student = new StudentService();
        CourseService course = new CourseService();
        EnrollmentService enrollment = new EnrollmentService();

        do {
            System.out.println("\n=============================== LEARN TRACK APPLICATION ===============================");
            System.out.println("\nPlease select the Management Service from below options.");
            System.out.println("1. Student Management \n2. Course Management \n3. Enrollment Management \n4. Exit Application\n");

            managementServiceSelection = InputValidator.readInt(scanner, "");

            switch (managementServiceSelection) {
                case 1:
                    student.invokeStudentManagementService();
                    break;
                case 2:
                    course.invokeCourseManagementService();
                    break;
                case 3:
                    enrollment.invokeEnrollmentManagement();
                    break;
                case 4:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (managementServiceSelection != 4);

        scanner.close();
    }
}