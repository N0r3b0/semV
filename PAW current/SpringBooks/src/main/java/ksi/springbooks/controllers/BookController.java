package ksi.springbooks.controllers;
import ksi.springbooks.models.Book;
import ksi.springbooks.services.BookService;
import ksi.springbooks.services.CategoryService;
import ksi.springbooks.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookService service;

    public BookController() {
    }

    @RequestMapping("books_list")
    public String viewBooksList(Model model){
        List<Book> lb = service.findAll("findByOrderByCategoryDescriptionAscTitleAsc");
        model.addAttribute("lb", lb);
        return "books_list";
    }

    @RequestMapping("/new_book")
    public String showFormNewBook(Model model) {
        Book nb = new Book();
        model.addAttribute("book", nb);


        return "new_book";
    }

    @PostMapping(value="/save_book")
    public String saveBook(@ModelAttribute("book") Book book) {
        service.save(book);
        return "redirect:/books_list";
    }

    @RequestMapping("/edit_book/{idb}")
    public ModelAndView showEditFormBook(@PathVariable(name = "idb") Long idb) {
        ModelAndView mav = new ModelAndView("edit_book");
        Optional<Book> eb = service.findById(idb);
        mav.addObject("book", eb);

        return mav;
    }
    @PostMapping(value="/editBook")
    public String editBook(@ModelAttribute("book") Book book) {
        service.save(book);
        return "redirect:/books_list";
    }

    @RequestMapping("/delete_book/{idb}")
    public String deleteBook(@PathVariable(name = "idb") Long idb) {
        service.deleteById(idb);
        return "redirect:/books_list";
    }


}