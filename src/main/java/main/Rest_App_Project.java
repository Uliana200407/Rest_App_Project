package main;

import Components.Book;
import dto.BookDTO;
import repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan(basePackages = {"exceptions", "Components", "controllers"})
public abstract class Rest_App_Project implements BookRepository {
    public static void main(String[] args) {
        SpringApplication.run ( Rest_App_Project.class, args );
        run ();
    }

    private static void run() {
        sendPostRequest ();
    }

    private static void sendPostRequest() {
        try {
            URL url = new URL ( "http://localhost:8080/api/books" );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection ();
            connection.setRequestMethod ( "POST" );
            connection.setRequestProperty ( "Content-Type", "application/json" );
            connection.setDoOutput ( true );

            // Send JSON data
            String jsonData = "{\"title\":\"New Book Title\",\"author\":\"New Author Name\",\"publicationYear\":2023,\"genre\":\"New Genre\"}";
            try (OutputStream outputStream = connection.getOutputStream ()) {
                byte[] input = jsonData.getBytes ( "utf-8" );
                outputStream.write ( input, 0, input.length );
            }

            int responseCode = connection.getResponseCode ();
            System.out.println ( "Response Code: " + responseCode );
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public List < BookDTO > findAll() {
        return null;
    }

    @Override
    public List < BookDTO > findAll(Sort sort) {
        return null;
    }

    @Override
    public Page < BookDTO > findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List < BookDTO > findAllById(Iterable < Long > iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public void deleteAllById(Iterable < ? extends Long > iterable) {

    }


    @Override
    public void deleteAll() {

    }

    @Override
    public < S extends Book > S save(S s) {
        return null;
    }


    @Override
    public Optional < BookDTO > findById(Long aLong) {
        return Optional.empty ();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public < S extends Book > S saveAndFlush(S s) {
        return null;
    }


    @Override
    public void deleteAllByIdInBatch(Iterable < Long > iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Book getOne(Long aLong) {
        return null;
    }

    @Override
    public Book getById(Long aLong) {
        return null;
    }
}



