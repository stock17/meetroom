package ru.yurima.meetingroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yurima.meetingroom.entities.AppUser;
import ru.yurima.meetingroom.entities.Meeting;
import ru.yurima.meetingroom.repositories.AppUserRepository;
import ru.yurima.meetingroom.repositories.MeetingRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class NewController {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    MeetingRepository meetingRepository;


    @GetMapping("/new")
    public String showCreateForm(Model model, Authentication auth) {
        Optional<AppUser> user = userRepository.findByUsername(auth.getName());
        if (!user.isEmpty()) model.addAttribute("user", user.get());
        return "new";
    }

    @PostMapping("/new")
    public ModelAndView saveNewMeeting(@RequestParam (name = "name") String name,
                                        @RequestParam (name = "start-time") String  startTime,
                                        @RequestParam (name = "end-time") String endTime,
                                        @RequestParam (name = "creator") String user) throws IOException {

        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);
        Meeting meeting = new Meeting(name, start, end, List.of(user));
        meetingRepository.save(meeting);
        return new ModelAndView("redirect:/");
    }
}
