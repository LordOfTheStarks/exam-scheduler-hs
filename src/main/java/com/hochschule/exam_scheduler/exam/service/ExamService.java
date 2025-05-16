package com.hochschule.exam_scheduler.exam.service;

import com.hochschule.exam_scheduler.exam.model.Exam;
import com.hochschule.exam_scheduler.exam.repository.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamService {
    private final ExamRepo examRepo;

    @Autowired
    public ExamService(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    public List<Exam> getExams() {
        return examRepo.findAll();
    }

    public List<Exam> getExamsByLecture(String lectureName) {
        return examRepo.findAll().stream()
                .filter(exam -> exam.getLecture().toLowerCase().contains(lectureName.toLowerCase()))
                .collect(Collectors.toList());

    }
    public List<Exam> getExamsByProgram(String programName) {
        return examRepo.findAll().stream()
                .filter(exam -> exam.getProgram().equalsIgnoreCase(programName))
                .toList();
    }

    public Exam addExam(Exam newExam) {
        return examRepo.save(newExam);
    }

}
