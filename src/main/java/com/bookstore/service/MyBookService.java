package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Booklist;
import com.bookstore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyBookService {

    @Autowired
    private MyBookRepository myBookRepository;

    public void save(Booklist booklist){
        myBookRepository.save(booklist);
    }

    public List<Booklist> getAllMyBooks(){
        return myBookRepository.findAll();
    }

    public void deleteBookById(int id){
        myBookRepository.deleteById(id);
    }

    public Booklist getBookById(int id){
        return myBookRepository.findById(id).get();
    }

    public Optional<Booklist> findById(int id){
        return myBookRepository.findById(id);
    }

}
