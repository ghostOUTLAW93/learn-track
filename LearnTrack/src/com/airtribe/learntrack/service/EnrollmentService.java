package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentService {
    private static final List<Enrollment> enrollments = new ArrayList<>();
    private static int counter = 0;
    int serviceFeatureSelection;

    public void invokeEnrollmentManagement() {
        do {
            System.out.println("\n---------------------------------Enrollment Management Service----------------------------------");
            System.out.println("\nPlease select the enrollment management feature you want to avail.");
            System.out.println("1. Enroll a student in a course \n2. View enrollments for a student \n3. Mark enrollment as completed/cancelled" +
                    "\n4. Select different Management Service\n");
            Scanner scanner = new Scanner(System.in);
            serviceFeatureSelection = InputValidator.readInt(scanner, "");

            switch (serviceFeatureSelection) {
                case 1:
                    enrollStudentInCourse();
                    break;
                case 2:
                    viewEnrollmentsForStudent();
                    break;
                case 3:
                    markEnrollmentStatus();
                    break;
                case 4:
                    System.out.println("Exiting Enrollment Management Service.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (serviceFeatureSelection != 4);
    }

    private static void enrollStudentInCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        try {
            Student student = StudentService.findStudentById(studentId);
            Course course = CourseService.findCourseById(courseId);
            if (!student.isActive()) {
                System.out.println("Student is inactive.");
                return;
            }
            if (!course.isActive()) {
                System.out.println("Course is inactive.");
                return;
            }
            Enrollment enrollment = new Enrollment();
            enrollment.setStudentId(studentId);
            enrollment.setCourseId(courseId);
            enrollment.setStatus(EnrollmentStatus.EnrollmentStatuses.ACTIVE);
            enrollments.add(enrollment);
            enrollment.setId(++counter);
            System.out.println("Enrollment added successfully! Enrollment ID: " + counter);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewEnrollmentsForStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        try {
            boolean found = false;
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getStudentId() == studentId) {
                    Course course = CourseService.findCourseById(enrollment.getCourseId());
                    System.out.println("Enrollment ID: " + enrollment.getId() + ", Course: " + course.getCourseName() + ", Status: " + enrollment.getStatus());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No enrollments found for this student.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void markEnrollmentStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Enrollment ID: ");
        int enrollmentId = scanner.nextInt();
        System.out.print("Enter new status (COMPLETED or CANCELLED): ");
        String statusInput = scanner.next().toUpperCase();
        try {
            Enrollment enrollment = findEnrollmentById(enrollmentId);
            if (statusInput.equals("COMPLETED")) {
                enrollment.setStatus(EnrollmentStatus.EnrollmentStatuses.COMPLETED);
            } else if (statusInput.equals("CANCELLED")) {
                enrollment.setStatus(EnrollmentStatus.EnrollmentStatuses.CANCELLED);
            } else {
                System.out.println("Invalid status.");
                return;
            }
            System.out.println("Enrollment status updated successfully.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        if (id < 1 || id > counter) {
            throw new EntityNotFoundException("Enrollment with ID " + id + " not found.");
        }
        return enrollments.get(id - 1);
    }
}
