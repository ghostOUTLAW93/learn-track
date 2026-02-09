package com.airtribe.learntrack.entity;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean isActive;

    @Override
    public String toString() {
        return  "Course Name -> " + this.getCourseName() +
                "\nDescription -> " + this.getDescription() +
                "\nDuration(Weeks) -> " + this.getDurationInWeeks() +
                "\nIs Active -> " + this.isActive();
    }
}
