package com.airtribe.learntrack.entity;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private EnrollmentStatus.EnrollmentStatuses status;

}