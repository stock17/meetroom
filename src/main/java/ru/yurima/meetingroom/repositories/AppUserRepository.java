package ru.yurima.meetingroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yurima.meetingroom.entities.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
