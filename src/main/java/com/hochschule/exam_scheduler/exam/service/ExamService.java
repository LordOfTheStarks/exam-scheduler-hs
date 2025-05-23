package com.hochschule.exam_scheduler.exam.service;

import com.hochschule.exam_scheduler.exam.model.Exam;
import com.hochschule.exam_scheduler.exam.repository.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Map;

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
        return examRepo.findByFacultyIdIgnoreCase(facultyId);
    }

    public Exam getExamByLectureId(String id) {
        return examRepo.findByIdIgnoreCase(id).orElse(null);
    }

    public List<Exam> getExamsByProgramAndLecture(String program, String lecture) {
        return examRepo.findAll().stream().filter(exam -> exam.getProgram().equalsIgnoreCase(program)
            && exam.getLecture().toLowerCase().contains(lecture.toLowerCase()))
                .toList();
    }

    public Exam addExam(Exam newExam) {
        String lecture = normalizeToEnglish(newExam.getLecture().toUpperCase()).replaceAll("//s+", "");
        String program = normalizeToEnglish(newExam.getProgram().toUpperCase());
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
        return examRepo.findByIdIgnoreCase(id).map(existing -> {
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

    public Exam patchExam(String id, Map<String, Object> updates) {
        Exam exam = examRepo.findByIdIgnoreCase(id).orElseThrow(() -> new RuntimeException("Exam not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "building" -> exam.setBuilding((String) value);
                case "campus" -> exam.setCampus((String) value);
                case "room" -> exam.setRoom((String) value);
                case "lecture" -> exam.setLecture((String) value);
                case "lectureTitle" -> exam.setLectureTitle((String) value);
                case "facultyId" -> exam.setFacultyId((String) value);
                case "program" -> exam.setProgram((String) value);
                case "examType" -> exam.setExamType((String) value);
                case "examStartTime" -> exam.setExamStartTime(LocalDateTime.parse((String) value));
                case "examMinutes" -> exam.setExamMinutes(((Number) value).intValue());
                case "instructor" -> exam.setInstructor((String) value);
            }
        });

        return examRepo.save(exam);
    }

    @Transactional
    public void deleteExam(String id) {
        examRepo.deleteById(id);
    }

    @Transactional
    public void deleteExamByFacultyAndProgram(String facultyId, String program) {
        examRepo.deleteByFacultyIdAndProgram(facultyId, program);
    }

    private String normalizeToEnglish(String text) {
        if (text == null) return null;

        text = text.replace("İ", "I")
                .replace("ı", "i")
                .replace("Ğ", "G")
                .replace("ğ", "g")
                .replace("Ü", "U")
                .replace("ü", "u")
                .replace("Ş", "S")
                .replace("ş", "s")
                .replace("Ö", "O")
                .replace("ö", "o")
                .replace("Ç", "C")
                .replace("ç", "c");

        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[^\\p{ASCII}]", "");

        return text;
    }

}
