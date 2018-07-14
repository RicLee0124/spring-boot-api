package com.lrm.api;


import com.lrm.domain.Book;
import com.lrm.dto.BookDto;
import com.lrm.exception.InvalidRequestException;
import com.lrm.exception.NotFoundException;
import com.lrm.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookApi {

    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public ResponseEntity<?> listAllBooks(){
        List<Book> books = bookService.findAllBooks();
        if(books.isEmpty()){
            throw new NotFoundException("books not found");
        }
        return new ResponseEntity<List<Book>>(books,HttpStatus.MULTI_STATUS.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        Optional<Book> opt = bookService.getBookById(id);
        if(!opt.isPresent()){
            throw new NotFoundException(String.format("book by id %s not found",id));
        }
        Book book = opt.get();
        return new ResponseEntity<Object>(book,HttpStatus.OK);
    }


    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new InvalidRequestException("Ivliad parameter",bindingResult);
        }
        Book book1 = bookService.saveBook(book);
        return new ResponseEntity<Object>(book1,HttpStatus.CREATED);
    }


    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,@Valid @RequestBody BookDto bookDto,BindingResult bindingResult){
        Optional<Book> opt = bookService.getBookById(id);
        if(!opt.isPresent()){
            throw new NotFoundException(String.format("book by id %s not found",id));
        }
        if (bindingResult.hasErrors()){
            throw new InvalidRequestException("Ivliad parameter",bindingResult);
        }
        Book currentBook = opt.get();
        BeanUtils.copyProperties(bookDto,currentBook);
        Book book1 = bookService.updateBook(currentBook);
        return new ResponseEntity<Book>(book1,HttpStatus.OK);
    }


//    public void convert(BookDto bookDto, Book book){
//        String[] nullPropertyNames = getNullPropertyNames(bookDto);
//        BeanUtils.copyProperties(bookDto,book,nullPropertyNames);
//    }
//
//
//    public String[] getNullPropertyNames(Object source){
//        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
//        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
//        List<String> nullPropertyNames = new ArrayList<>();
//        for(PropertyDescriptor pd : pds){
//            String properName = pd.getName();
//            if(beanWrapper.getPropertyValue(properName) == null){
//                nullPropertyNames.add(properName);
//            }
//        }
//        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
//    }


    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/books")
    public ResponseEntity<?> deleteAllBook(){
        bookService.deleteAllBook();
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
