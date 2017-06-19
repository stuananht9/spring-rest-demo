package spring.restful.demo.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.restful.demo.demo2.entity.BookEntity;
import spring.restful.demo.demo2.repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tuananh on 06/13/17.
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAllBook(){
        List<BookEntity> bookEntityList = bookRepository.getAllBook();
        return bookEntityList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addNewBook(@RequestBody BookEntity newBookEntity){
        BookEntity result = bookRepository.save(newBookEntity);
        return result;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object updateBook(@RequestBody final BookEntity updatedBookEntity){
        BookEntity result = bookRepository.update(updatedBookEntity);
        if (result == null){
            Map<String, String> error = new HashMap<String, String>(){{
                put("error", updatedBookEntity.getId() + "does not exist");
            }};
            return error;
        }
        return result;
    }

    @RequestMapping(value = "/{bookID}", method = RequestMethod.DELETE)
    public Object deleteBook(@PathVariable(value = "bookID") final String bookID){
        Boolean result = bookRepository.delete(Integer.valueOf(bookID));
        if (!result){
            Map<String, String> error = new HashMap<String, String>(){{
                put("error", "A book which book ID = " + bookID + " does not exist");
            }};
            return error;
        }else {
            Map<String, String> success = new HashMap<String, String>(){{
                put("success", "A book which book ID = " + bookID + " has been deleted successfull");
            }};
            return success;
        }
    }
}
