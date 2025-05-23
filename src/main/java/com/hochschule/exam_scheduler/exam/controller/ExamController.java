package com.hochschule.exam_scheduler.exam.controller;

import com.hochschule.exam_scheduler.exam.model.Exam;
import com.hochschule.exam_scheduler.exam.service.ExamService;
import com.hochschule.exam_scheduler.exam.repository.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/exam")
public class ExamController {
    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public List<Exam> getExams(@RequestParam(required = false) String lectureName) {
        if(lectureName != null) {
            return examService.getExamsByLecture(lectureName);
        } else {
            return examService.getExams();
        }
    }

    @GetMapping("/byProgram")
    public List<Exam> getExamsByProgram(@RequestParam String program) {
        return examService.getExamsByProgram(program);
    }

    @GetMapping("/byFaculty")
    public List<Exam> getExamsByFaculty(@RequestParam String facultyId) {
        return examService.getExamsByFaculty(facultyId);
    }

    @GetMapping("/search")
    public List<Exam> getExamsByProgramAndLecture(@RequestParam String program,
                                                  @RequestParam String lecture) {
        return examService.getExamsByProgramAndLecture(program, lecture);
    }

    @PostMapping
    public Exam addExam(@RequestBody Exam newExam) {
        return examService.addExam(newExam);
    }

    @GetMapping("/{id}")
    public Exam getExamByLectureId(@PathVariable String id) {
        return examService.getExamByLectureId(id);
    }
    @PutMapping("/{id}")
    public Exam updateExam(@PathVariable String id, @RequestBody Exam updatedExam) {
        return examService.updateExam(id, updatedExam);
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable String id) {
        examService.deleteExam(id);
    }

    @DeleteMapping("/byFacultyProgram")
    public void deleteExamsByFcAndPg(@PathVariable String facultyId,
                                     @PathVariable String program) {
        examService.deleteExamByFacultyAndProgram(facultyId, program);
    }
}
