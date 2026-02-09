package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseService extends Course{
    private static int courseCounter = 0;
    private static final List<Course> courses = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int serviceFeatureSelection;

    public void invokeCourseManagementService() {
        do {
            System.out.println("---------------------------------Course Management Service----------------------------------");
            System.out.println("\nPlease select the course management feature you want to avail.");
            System.out.println("1. Add new course \n2. View all courses \n3. Acivate/Deactive a course" +
                    "\n4. Select different Management Service\n");
            Scanner scanner = new Scanner(System.in);
            serviceFeatureSelection = InputValidator.readInt(scanner, "");

            switch (serviceFeatureSelection) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    activateDeactivateCourse();
                    break;
                case 4:
                    System.out.println("Exiting Course Management Service.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (serviceFeatureSelection != 4);
    }

    private void addCourse() {
        Course course = new Course();
        collectCourseInfo(course);
        courses.add(course);
        ++courseCounter;
        System.out.println("\nCourse " + course.getCourseName() + " [Id number : " + courseCounter + "]" + " added successfully!");
    }

    private void collectCourseInfo(Course course) {
        course.setId(IdGenerator.getNextCourseId());
        System.out.println("\nPlease enter the details of the course.");
        course.setCourseName(InputValidator.readLine(scanner, "Course Name: "));
        course.setDescription(InputValidator.readLine(scanner, "Description: "));
        course.setDurationInWeeks(InputValidator.readPositiveInt(scanner, "Duration in Weeks: "));
        course.setActive(true);
    }

    private static void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (int i = 1; i <= courseCounter; i++) {
            Course course = courses.get(i - 1);
            System.out.println("\nCourse Id : " + i + "\n" + course.toString());
        }
    }

    private static void activateDeactivateCourse() {
        System.out.println("Please enter the ID of the course to activate/deactivate.");
        Scanner scanner = new Scanner(System.in);
        int id = InputValidator.readInt(scanner, "");
        try {
            Course course = findCourseById(id);
            course.setActive(!course.isActive());
            System.out.println("Course " + course.getCourseName() + " is now " + (course.isActive() ? "active" : "inactive") + ".");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Course findCourseById(int id) throws EntityNotFoundException {
        if (id < 1 || id > courseCounter) {
            throw new EntityNotFoundException("Course with ID " + id + " not found.");
        }
        return courses.get(id - 1);
    }

    @Override
    public String toString() {
        return "\nCourse Name -> " +  this.getCourseName()+
                "\nDescription -> " + this.getDescription() +
                "\nDuration -> " + this.getDurationInWeeks() + " weeks" +
                "\nIs Active -> " + this.isActive();
    }

}
