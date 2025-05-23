package com.hochschule.exam_scheduler.exam.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "sose2025")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String lecture;
    private String lectureTitle;
    private String facultyId;
    private String program;
    private String examType;
    private String campus;
    private String building;
    private String room;
    private LocalDateTime examStartTime;
    private int examMinutes;
    private String instructor;

    public Exam() {}

    public Exam(String lecture, String facultyId, String program, String lectureTitle, String examType,
                String campus, String building, String room, LocalDateTime examStartTime,
                int examMinutes, String instructor) {
        this.lecture = lecture;
        this.lectureTitle = lectureTitle;
        this.examType = examType;
        this.campus = campus;
        this.building = building;
        this.room = room;
        this.instructor = instructor;
        this.examStartTime = examStartTime;
        this.examMinutes = examMinutes;
        this.facultyId = facultyId;
        this.program = program;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getRoom() {
        return room;
    }

    public String getBuilding() {
        return building;
    }

    public String getCampus() {
        return campus;
    }

    public int getExamMinutes() {
        return examMinutes;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getExamStartTime() {
        return examStartTime;
    }

    public String getExamType() {
        return examType;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public String getLecture() {
        return lecture;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public String getProgram() {
        return program;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setExamMinutes(int examMinutes) {
        this.examMinutes = examMinutes;
    }

    public void setExamStartTime(LocalDateTime examStartTime) {
        this.examStartTime = examStartTime;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
