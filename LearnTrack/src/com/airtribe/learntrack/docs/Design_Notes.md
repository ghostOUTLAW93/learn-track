# Design Notes

## Why ArrayList Instead of Array
ArrayLists were used for storing collections of entities (e.g., `students` in Student, `courses` in Course, `enrollments` in Enrollment) because they provide dynamic sizing, allowing the collections to grow as new items are added without needing to specify a fixed size upfront, unlike arrays which are immutable in length.

## Where Static Members Were Used and Why
Static members were used for counters (e.g., `studentCounter` in Student, `courseCounter` in Course, `counter` in Enrollment) and shared lists (e.g., `students`, `courses`, `enrollments`) to maintain global state across all instances of the classes, enabling easy tracking of IDs and access to entity lists without requiring object instantiation.

## Where Inheritance Was Used and What Was Gained
Inheritance was used with Student and Trainer extending Person. This allowed sharing common attributes (id, firstName, lastName, email) and methods (getDisplayName) from the base class, reducing code duplication and promoting reusability while allowing subclasses to add specific fields (e.g., batch for Student, specialization for Trainer).