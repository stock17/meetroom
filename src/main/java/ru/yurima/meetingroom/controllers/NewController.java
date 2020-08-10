package ru.yurima.meetingroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yurima.meetingroom.entities.AppUser;
import ru.yurima.meetingroom.entities.Meeting;
import ru.yurima.meetingroom.repositories.AppUserRepository;
import ru.yurima.meetingroom.repositories.MeetingRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class NewController {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    MeetingRepository meetingRepository;


    @GetMapping("/new")
    public String showCreateForm(Model model, Authentication auth) {
        Optional<AppUser> user = userRepository.findByUsername(auth.getName());
        if (user.isPresent()) model.addAttribute("user", user.get());
        return "new";
    }

    @PostMapping("/new")
    public ModelAndView saveNewMeeting(@RequestParam  (name = "name")  String name,
                                        @RequestParam (name = "start-time") String  startTime,
                                        @RequestParam (name = "end-time") String endTime,
                                        @RequestParam (name = "creator") String user) throws IOException {

        Meeting meeting = validateAndCreate(name, startTime, endTime, user);
        meetingRepository.save(meeting);
        return new ModelAndView("redirect:/");
    }

    public Meeting validateAndCreate(String name, String startTime, String endTime, String user){
        if (Objects.isNull(name) || name.isBlank())
            throw new IllegalArgumentException("Name must not be empty");

        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);
        long duration = ChronoUnit.MINUTES.between(start, end);

        if (start.isAfter(end) || duration <= 30 || duration >= 24 * 60)
            throw new IllegalArgumentException("Duration should be between 30 minutes and 24 hours");

        if (Objects.isNull(user) || user.isBlank())
            throw new IllegalArgumentException("Creator must not be empty");

        return new Meeting(name, start, end, List.of(user));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationExceptions(Model model, IllegalArgumentException ex) {
        model.addAttribute("exception", ex);
        return "newerr";
    }
}
