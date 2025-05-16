package com.hochschule.exam_scheduler.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hochschule.exam_scheduler.exam.model.Exam;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Integer> {
    Optional<Exam> findByFacultyIdAndProgram(String facultyId, String program);

    void deleteByFacultyIdAndProgram(String facultyId, String program);
}
