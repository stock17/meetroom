package ru.yurima.meetingroom.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.yurima.meetingroom.entities.Meeting;
import ru.yurima.meetingroom.repositories.MeetingRepository;

import java.util.Optional;

@Controller
public class DetailsController {

    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("/details")
    public String showDetails(Model model, @RequestParam long id) {
        Optional<Meeting> meeting = meetingRepository.findById(id);
        if (meeting.isPresent()) {
            model.addAttribute("meeting", meeting.get());
            return "details";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }
}
