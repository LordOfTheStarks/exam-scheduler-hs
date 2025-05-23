package com.hochschule.exam_scheduler.exam.service;

import com.hochschule.exam_scheduler.exam.model.Exam;
import com.hochschule.exam_scheduler.exam.repository.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Exam> getExamsByFaculty(String facultyId) {
        return examRepo.findAll().stream()
                .filter(exam -> exam.getFacultyId().equalsIgnoreCase(facultyId))
                .toList();
    }
    public List<Exam> getExamsByProgramAndLecture(String program, String lecture) {
        return examRepo.findAll().stream().filter(exam -> exam.getProgram().equalsIgnoreCase(program)
            && exam.getLecture().toLowerCase().contains(lecture.toLowerCase()))
                .toList();
    }

    public Exam addExam(Exam newExam) {
        if (newExam.getId() == null || newExam.getId().isBlank()) {
            String lectureCode = abbreviateLecture(newExam.getLecture());
            String generatedId = newExam.getProgram().toUpperCase() + "-" + lectureCode;
            newExam.setId(generatedId);
        }
        return examRepo.save(newExam);
    }

    public Exam updateExam(int id, Exam updatedExam) {
        return examRepo.findById(id).map(existing -> {
            existing.setLecture(updatedExam.getLecture());
            existing.setLectureTitle(updatedExam.getLectureTitle());
            existing.setFacultyId(updatedExam.getFacultyId());
            existing.setProgram(updatedExam.getProgram());
            existing.setExamType(updatedExam.getExamType());
            existing.setCampus(updatedExam.getCampus());
            existing.setBuilding(updatedExam.getBuilding());
            existing.setRoom(updatedExam.getRoom());
            existing.setExamStartTime(updatedExam.getExamStartTime());
            existing.setExamMinutes(updatedExam.getExamMinutes());
            existing.setInstructor(updatedExam.getInstructor());
            return examRepo.save(existing);
        }).orElse(null);
    }

    @Transactional
    public void deleteExam(int id) {
        examRepo.deleteById(id);
    }

    private String abbreviateLecture(String lectureName) {
        String cleaned = lectureName.replaceAll("[^A-Za-z0-9]", "");

        return cleaned.length() <= 4 ? cleaned.toUpperCase() : cleaned.substring(0, 4).toUpperCase();
    }
}
