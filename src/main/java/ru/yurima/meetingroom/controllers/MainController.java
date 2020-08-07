package ru.yurima.meetingroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yurima.meetingroom.entities.Meeting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model) {
        // FOr testing
        // ------------------------------------------------------------------------------------------------
        LocalDateTime startTime = LocalDateTime.of(2020, 8, 8, 19, 00);
        LocalDateTime endTime = startTime.plusHours(1);
        Meeting meeting = new Meeting("Saturday", startTime, endTime, Arrays.asList("Me", "Joe"));

        LocalDateTime startTime1 = LocalDateTime.of(2020, 8, 5, 11, 00);
        LocalDateTime endTime1 = startTime.plusHours(1);
        Meeting meeting1 = new Meeting("Wednesday", startTime1, endTime1, Arrays.asList("Me", "Ann"));

        List<Meeting> list = new ArrayList<>();
        list.add(meeting1);
        list.add(meeting);

        model.addAttribute("meetings", list);
        //-------------------------------------------------------------------------------------------------

        return "index";
    }
}
