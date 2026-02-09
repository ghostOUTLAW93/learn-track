package com.airtribe.learntrack.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends Person {
    private String batch;
    private boolean isActive;

    @Override
    public String toString() {
        return  "Name -> " + this.getFirstName() + " " + this.getLastName() +
                "\nEmail -> " + this.getEmail() +
                "\nBatch -> " + this.getBatch() +
                "\nIs Active -> " + this.isActive();
    }

}
