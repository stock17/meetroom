package ru.yurima.meetingroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yurima.meetingroom.entities.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query("SELECT m FROM Meeting m WHERE m.startTime BETWEEN :startDate AND :endDate")
    public List<Meeting> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT m FROM Meeting m WHERE (m.startTime BETWEEN :startTime AND :endTime)" +
    "OR (m.endTime BETWEEN :startTime AND :endTime)")
    public List<Meeting> findIntersectedByTime(LocalDateTime startTime, LocalDateTime endTime);
}

