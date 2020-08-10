package ru.yurima.meetingroom.entities;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ElementCollection
    private List<String> participants = new ArrayList<>();

    public Meeting(String title, LocalDateTime startTime, LocalDateTime endTime, List<String> participants) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public Meeting() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getShortDescription() {
        return title + System.lineSeparator() + participants;
    }

    public String getFullDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return title + System.lineSeparator() +
                "start: " + startTime.format(formatter) + System.lineSeparator() +
                "end: " + endTime.format(formatter)  + System.lineSeparator() +
                "participants: " + participants;
    }
}
