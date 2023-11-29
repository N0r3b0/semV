package ksi.springbooks.controllers;

import ksi.springbooks.models.Publisher;
import ksi.springbooks.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/publishers_list")
    public String viewPublishersList(Model model) {
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        return "publishers_list";
    }

    @GetMapping("/new_publisher")
    public String showFormNewPublisher(Model model) {
        Publisher newPublisher = new Publisher();
        model.addAttribute("publisher", newPublisher);
        return "new_publisher";
    }

    @PostMapping("/save_publisher")
    public String savePublisher(@ModelAttribute("publisher") Publisher publisher) {
        publisherService.save(publisher);
        return "redirect:/publishers_list";
    }

    @GetMapping("/edit_publisher/{idp}")
    public String showEditFormPublisher(@PathVariable(name = "idp") Long idp, Model model) {
        Optional<Publisher> publisher = publisherService.findById(idp);
        model.addAttribute("publisher", publisher.orElse(new Publisher()));
        return "edit_publisher";
    }

    @PostMapping("/editPublisher")
    public String editPublisher(@ModelAttribute("publisher") Publisher publisher) {
        publisherService.save(publisher);
        return "redirect:/publishers_list";
    }

    @GetMapping("/delete_publisher/{idp}")
    public String deletePublisher(@PathVariable(name = "idp") Long idp) {
        if(publisherService.findById(idp).isPresent() && !publisherService.findById(idp).get().getBooks().isEmpty()) {
            return "page500";
        } else {
            publisherService.deleteById(idp);
            return "redirect:/publishers_list";
        }
    }
}
