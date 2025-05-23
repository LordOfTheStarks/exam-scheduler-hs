package com.hochschule.exam_scheduler.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hochschule.exam_scheduler.exam.model.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepo extends JpaRepository<Exam, String> {
    List<Exam> findByFacultyIdIgnoreCase(String facultyId);

    Optional<Exam> findByIdIgnoreCase(String id);

    void deleteByFacultyIdAndProgram(String facultyId, String program);
}
