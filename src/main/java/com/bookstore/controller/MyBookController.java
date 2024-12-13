package com.bookstore.controller;

import com.bookstore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyBookController {

    @Autowired
    private MyBookService myBookService;

    @RequestMapping("/deleteMyBook/{id}")
    public String deleteMyBook(@PathVariable("id")int id){
        myBookService.deleteBookById(id);
        return "redirect:/my_books";
    }
}
