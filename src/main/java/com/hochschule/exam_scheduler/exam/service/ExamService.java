package com.hochschule.exam_scheduler.exam.service;

import com.hochschule.exam_scheduler.exam.model.Exam;
import com.hochschule.exam_scheduler.exam.repository.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public Exam getExamByLectureId(String id) {
        return examRepo.findById(id).orElse(null);
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
        String lecture = newExam.getLecture().toUpperCase().replaceAll("//s+", "");
        String program = newExam.getProgram().toUpperCase();
        String number = "";
        Matcher matcher = Pattern.compile("(\\d+)$").matcher(lecture);
        if (matcher.find()) {
            number = matcher.group(1);
            lecture = lecture.replaceAll("(\\d+)$", "");
        }
        String abbreviated = lecture.length() > 4 ? lecture.substring(0, 4) : lecture;

        String generatedId = program + "-" + abbreviated + number;
        newExam.setId(generatedId);

        return examRepo.save(newExam);
    }

    public Exam updateExam(String id, Exam updatedExam) {
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
    public void deleteExam(String id) {
        examRepo.deleteById(id);
    }

}
