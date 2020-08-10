package ru.yurima.meetingroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yurima.meetingroom.entities.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
