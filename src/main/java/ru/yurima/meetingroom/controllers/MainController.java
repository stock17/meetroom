package ru.yurima.meetingroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yurima.meetingroom.entities.Meeting;
import ru.yurima.meetingroom.repositories.MeetingRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    MeetingRepository meetingRepository;

    @GetMapping("/")
    public String main(Model model, Authentication auth, @RequestParam (name = "date", required = false) String firstDayOfWeek) {
        // FOr testing
        // ------------------------------------------------------------------------------------------------
//        LocalDateTime startTime = LocalDateTime.of(2020, 8, 10, 11, 00);
//        LocalDateTime endTime = startTime.plusHours(1);
//        Meeting meeting = new Meeting("Saturday", startTime, endTime, Arrays.asList("Me", "Joe"));
//        meeting.setId(1);
//
//        LocalDateTime startTime1 = LocalDateTime.of(2020, 8, 11, 14, 00);
//        LocalDateTime endTime1 = startTime.plusHours(1);
//        Meeting meeting1 = new Meeting("Wednesday", startTime1, endTime1, Arrays.asList("Me", "Ann"));
//        meeting1.setId(2);
        //-------------------------------------------------------------------------------------------------

        LocalDate currentFirstDayOfWeek;

        if (firstDayOfWeek == null) {
            currentFirstDayOfWeek = LocalDate.now().with(DayOfWeek.MONDAY);
        } else {
            currentFirstDayOfWeek = LocalDate.parse(firstDayOfWeek).with(DayOfWeek.MONDAY);
        }

        List<Meeting> list = meetingRepository.findBetweenDates(currentFirstDayOfWeek.atStartOfDay(),
                currentFirstDayOfWeek.plusDays(6).atStartOfDay());
//        list.add(meeting1);
//        list.add(meeting);

        model.addAttribute("meetings", list);
        model.addAttribute("username", auth.getName());
        model.addAttribute("firstDayOfWeek", currentFirstDayOfWeek);
        return "index";
    }
}
