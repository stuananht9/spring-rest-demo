package spring.restful.demo.demo2.repository;

import org.springframework.stereotype.Repository;
import spring.restful.demo.demo2.entity.BookEntity;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuananh on 06/13/17.
 */
@Repository
public class BookRepository {
    List<BookEntity> bookEntityList = new ArrayList<BookEntity>();

    @PostConstruct
    public void init() {

        bookEntityList.add(new BookEntity(1, "Java A-Z",12.1, "Roger"));
        bookEntityList.add(new BookEntity(2, "Python",19.9, "Peter"));
        bookEntityList.add(new BookEntity(3, "Linux",25.5, "Alex"));
    }

    public List<BookEntity> getAllBook() {
        return bookEntityList;
    }

    public BookEntity findBookByBookID(int bookID) {
        for (BookEntity bookEntity: bookEntityList) {
            if (bookEntity.getId() == bookID) {
                return bookEntity;
            }
        }
        return null;
    }

    public BookEntity save(BookEntity bookEntity) {
        bookEntityList.add(bookEntity);
        return bookEntity;
    }

    public boolean deleteBookByBookID(int bookID) {
        BookEntity bookEntity = findBookByBookID(bookID);
        if (bookEntity != null) {
            bookEntityList.remove(bookEntity);
            return true;
        }
        return false;
    }

    public BookEntity update(BookEntity newBook) {
        boolean isFound = false;
        BookEntity foundBook = null;
        // check if book ID is existing
        for (BookEntity book : bookEntityList){
            if (book.getId() == newBook.getId()){
                isFound = true;
                foundBook = book;
                break;
            }
        }

        if (!isFound){
            return null;
        }

        bookEntityList.remove(foundBook);
        newBook.setId(foundBook.getId());
        bookEntityList.add(newBook);
        return newBook;
    }

    public boolean delete(int bookID){
        boolean isFound = false;
        BookEntity foundBook = null;

        for (BookEntity book : bookEntityList){
            if (book.getId() == bookID){
                isFound = true;
                foundBook = book;
                break;
            }
        }

        if (!isFound){
            return false;
        }
        bookEntityList.remove(foundBook);
        return true;
    }
}
