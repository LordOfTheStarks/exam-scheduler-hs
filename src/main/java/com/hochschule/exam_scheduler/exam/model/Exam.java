package com.hochschule.exam_scheduler.exam.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "wise24-25")
public class Exam implements Serializable {
    @Id
    @Column(name = "course")
    private String course;
    private String course_title;
    private String exam_type;
    private String campus;
    private String building;
    private String room;
    private LocalDateTime exam_start_time;
    private int exam_minutes;

    public Exam(String course, String course_title, String exam_type,
                String campus, String building, String room, LocalDateTime exam_start_time, int exam_minutes) {
        this.course = course;
        this.course_title = course_title;
        this.exam_type = exam_type;
        this.campus = campus;
        this.building = building;
        this.room = room;
        this.exam_start_time = exam_start_time;
        this.exam_minutes = exam_minutes;
    }

    public String getCourse() {
        return course;
    }

    public String getCourse_title() {
        return course_title;
    }

    public String getExam_type() {
        return exam_type;
    }

    public String getCampus() {
        return campus;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setExam_start_time(LocalDateTime exam_start_time) {
        this.exam_start_time = exam_start_time;
    }

    public void setExam_minutes(int exam_minutes) {
        this.exam_minutes = exam_minutes;
    }

    public String toString() {
        return "Exam{" +
                "course='" + course + '\'' +
                ", course_title='" + course_title + '\'' +
                ", exam_type'" + exam_type + '\'' +
                ", campus'" + campus + '\'' +
                ", building'" + building + '\'' +
                ", room'" + room + '\'' +
                ", exam_start_time=" + exam_start_time +
                ", exam_minutes" + exam_minutes + '\'' +
                '}';
    }

    public LocalDateTime getExam_start_time() {
        return exam_start_time;
    }

    public int getExam_minutes() {
        return exam_minutes;
    }
}
