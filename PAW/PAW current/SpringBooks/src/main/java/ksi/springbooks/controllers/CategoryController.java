package ksi.springbooks.controllers;

import ksi.springbooks.models.Category;
import ksi.springbooks.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categories_list")
    public String viewCategoriesList(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "categories_list";
    }

    @RequestMapping("/new_category")
    public String showFormNewCategory(Model model) {
        Category newCategory = new Category();
        model.addAttribute("category", newCategory);
        return "new_category";
    }

    @PostMapping("/save_category")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories_list";
    }

    @RequestMapping("/edit_category/{idc}")
    public String showEditFormCategory(@PathVariable(name = "idc") Long idc, Model model) {
        Optional<Category> category = categoryService.findById(idc);
        model.addAttribute("category", category.orElse(new Category()));
        return "edit_category";
    }

    @PostMapping("/editCategory")
    public String editCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories_list";
    }

    @RequestMapping("/delete_category/{idc}")
    public String deleteCategory(@PathVariable(name = "idc") Long idc) {
        if(categoryService.findById(idc).isPresent() && !categoryService.findById(idc).get().getBooks().isEmpty()) {
            return "page500";
        } else {
            categoryService.deleteById(idc);
            return "redirect:/categories_list";
        }
    }

}
