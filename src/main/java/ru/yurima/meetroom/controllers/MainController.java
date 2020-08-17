package ru.yurima.meetroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yurima.meetroom.entities.Meeting;
import ru.yurima.meetroom.repositories.MeetingRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    MeetingRepository meetingRepository;

    @GetMapping("/")
    public String main(Model model, Authentication auth,
                       @RequestParam (name = "date", required = false) String firstDayOfWeek) {

        LocalDate currentFirstDayOfWeek;

        if (firstDayOfWeek == null) {
            currentFirstDayOfWeek = LocalDate.now().with(DayOfWeek.MONDAY);
        } else {
            currentFirstDayOfWeek = LocalDate.parse(firstDayOfWeek).with(DayOfWeek.MONDAY);
        }

        List<Meeting> list = meetingRepository.findBetweenDates(currentFirstDayOfWeek.atStartOfDay(),
                currentFirstDayOfWeek.plusDays(6).atStartOfDay());

        model.addAttribute("dateFormatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        model.addAttribute("meetings", list);
        model.addAttribute("username", auth.getName());
        model.addAttribute("firstDayOfWeek", currentFirstDayOfWeek);
        return "index";
    }
}
