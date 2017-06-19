package spring.restful.demo.demo2;

import org.springframework.web.client.RestTemplate;
import spring.restful.demo.demo2.entity.BookEntity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tuananh on 06/19/17.
 */
public class Main {
    static String url = "http://localhost:8080/book";

    public static void main(String[] args) {
        printAllBook();
    }

    static void printAllBook(){
        // make a HTTP request GET
        RestTemplate restTemplate = new RestTemplate();
        BookEntity[] bookArray= restTemplate.getForObject(url, BookEntity[].class);
        List<BookEntity> bookEntityList = Arrays.asList(bookArray);
        System.out.println("Fetched " + bookEntityList.size() + " from REST API");
        for(BookEntity book: bookEntityList){
            System.out.println(book.getName() + " of " + book.getAuthor());
        }
    }
    static void insertNewBook(){
        // make a HTTP request (POST) to specified URL
        // pass new book data in to the request as JSON body
        RestTemplate restTemplate = new RestTemplate();
        BookEntity newBook = new BookEntity();
        newBook.setId(9);//should be auto increment value
        newBook.setName("RoR A-Z");
        newBook.setAuthor("Eric");
        newBook.setPrice(12.3);
        BookEntity result = restTemplate.postForObject(url, newBook, BookEntity.class);
        System.out.println("A new book named : '" + result.getName() + "' has been inserted successfully.");
    }

    static void updateExistingBook(){
        // make a HTTP request (PUT) to specified URL
        // pass updated book data in to the request as JSON body
        RestTemplate restTemplate = new RestTemplate();
        BookEntity updatedBook = new BookEntity();
        updatedBook.setId(1); //Book ID that will be updated
        // Specify updated book data
        updatedBook.setName("RoR A-Z");
        updatedBook.setAuthor("Markus");
        updatedBook.setPrice(99.9);
        restTemplate.put(url, updatedBook);
    }

    static void deleteBook(){
        // make a HTTP request (DELETE) to specified URL
        // [DELETE] http://localhost:8080/book/1 -> delete a book which its id = 1
        RestTemplate restTemplate = new RestTemplate();
        String bookID = "1"; //Book ID that will be deleted
        String deleteURL = url + "/" + bookID;
        restTemplate.delete(deleteURL);
    }
}
