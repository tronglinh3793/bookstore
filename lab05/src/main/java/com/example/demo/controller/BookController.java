package com.example.demo.controller;

import com.example.demo.entity.BookEntity;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    ServletContext app;
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String list(Model model){
        List<BookEntity> listBook = bookService.getAllBooks();
        model.addAttribute("listBook", listBook);
        model.addAttribute("title", "Book List");
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new BookEntity());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") BookEntity book,
                          BindingResult br,
                          @RequestParam MultipartFile imageBook) throws IOException
    {
        if(br.hasErrors()){
            return "book/add";
        }

        if(imageBook != null && imageBook.getSize() > 0)
        {
            try{
                File saveFile = new ClassPathResource("static/images").getFile();
                String newImageFile = UUID.randomUUID() + ".png";
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                Files.copy(imageBook.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                book.setImage(newImageFile);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        bookService.addOrUpdateBook(book);
        return "redirect:/books";
    }

    @GetMapping("edit/{id}")
    public String editBookForm(@PathVariable("id")Long id,Model model){
        BookEntity editBook = bookService.getBookById(id);
        model.addAttribute("book", editBook);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("book") BookEntity bookEdit,
                       BindingResult br,
                       @RequestParam MultipartFile imageBook){
        if(br.hasErrors()){
            return "book/edit";
        }
//        if(imageBook != null && imageBook.getSize() > 0)
//        {
//            try{
//                File saveFile = new ClassPathResource("static/images").getFile();
//                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + bookEdit.getImage());
//                Files.copy(imageBook.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
        bookService.addOrUpdateBook(bookEdit);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
