package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.entity.Booklist;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookService myBookService;

    @GetMapping("/")
    public String home() {
        return "home"; //access to home.html
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister"; //access to bookRegister.html
    }

    @GetMapping("/all_books")
    public ModelAndView getAllBook() {
        List<Book> list = bookService.getAllBook();
//        ModelAndView m=new ModelAndView();
//        m.setViewName("bookList");
//        m.addObject("book",list);
        //viewname=html, modelname=entity
        return new ModelAndView("bookList", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/all_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<Booklist> booklist = myBookService.getAllMyBooks();
        model.addAttribute("book", booklist);
        return "myBooks"; //access to myBooks.html
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b = bookService.getBookById(id);
        Booklist booklist = new Booklist(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookService.save(booklist);
        return "redirect:/all_books";
    }

//    cw
    @RequestMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        if(bookService.findById(book.getId()).isPresent()){
          bookService.save(book);
        }
        if(myBookService.findById(book.getId()).isPresent()){
            Booklist booklist = new Booklist(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
            myBookService.save(booklist);
        }
        return "redirect:/all_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book b = bookService.getBookById(id);
        model.addAttribute("book", b);
        return "bookEdit";
    }

    @RequestMapping("deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        if(bookService.findById(id).isPresent()){
            bookService.deleteBookById(id);
        }
        if(myBookService.findById(id).isPresent()){
            myBookService.deleteBookById(id);
        }
        return "redirect:/all_books";
    }

}
