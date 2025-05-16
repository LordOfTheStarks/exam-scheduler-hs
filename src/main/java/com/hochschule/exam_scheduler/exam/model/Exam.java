package com.hochschule.exam_scheduler.exam.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sose2025")
public class Exam implements Serializable {
    @Id
    @Column(name = "lecture")
    private String lecture;
    private String lecture_title;
    @Id
    @Column(name = "facultyID")
    private String facultyId;
    private String program;
    private String exam_type;
    private String campus;
    private String building;
    private String room;
    private LocalDateTime exam_start_time;
    private int exam_minutes;
    private String instructor;

    public Exam(String lecture, String facultyId, String program, String lecture_title, String exam_type,
                String campus, String building, String room, LocalDateTime exam_start_time,
                int exam_minutes, String instructor) {
        this.lecture = lecture;
        this.lecture_title = lecture_title;
        this.exam_type = exam_type;
        this.campus = campus;
        this.building = building;
        this.room = room;
        this.instructor = instructor;
        this.exam_start_time = exam_start_time;
        this.exam_minutes = exam_minutes;
        this.facultyId = facultyId;
        this.program = program;
    }

    public Exam() {
    }
}
