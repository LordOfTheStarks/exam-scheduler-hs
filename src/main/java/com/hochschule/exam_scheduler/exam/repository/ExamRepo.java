package com.hochschule.exam_scheduler.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hochschule.exam_scheduler.exam.model.Exam;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepo extends JpaRepository<Exam, String> {
    Optional<Exam> findByFacultyAndProgram(String facultyId, String program);

    void deleteByFacultyAndProgram(String facultyId, String program);
}
