package spring.restful.demo.demo2;

import org.springframework.web.client.RestTemplate;

/**
 * Created by tuananh on 06/19/17.
 */
public class Main {
    static String url = "http://localhost:8080/book";

    public static void main(String[] args) {

    }

    static void printAllBook(){
        // make a HTTP request GET
        RestTemplate restTemplate = new RestTemplate();
    }
}
