package Actions;
import Components.Book;
import Components.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan(basePackages = {"Service", "Components","Actions"})
public class Rest_App_Project implements BookRepository {
    public static void main(String[] args) {
        SpringApplication.run(Rest_App_Project.class, args);
        sendPostRequest();
    }
    @PostMapping("/send-request")
    private static void sendPostRequest() {
        try {
            URL url = new URL("http://localhost:8080/api/books");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Відправка JSON-даних
            String jsonData = "{\"title\":\"New Book Title\",\"author\":\"New Author Name\",\"isbn\":\"1234567890\"}";
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List < Book > findAll() {
        return null;
    }

    @Override
    public List < Book > findAll(Sort sort) {
        return null;
    }

    @Override
    public Page < Book > findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List < Book > findAllById(Iterable < Long > iterable) {
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
    public void deleteAll(Iterable < ? extends Book > iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public < S extends Book > S save(S s) {
        return null;
    }

    @Override
    public < S extends Book > List < S > saveAll(Iterable < S > iterable) {
        return null;
    }

    @Override
    public Optional < Book > findById(Long aLong) {
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
    public < S extends Book > List < S > saveAllAndFlush(Iterable < S > iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable < Book > iterable) {

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

    @Override
    public < S extends Book > Optional < S > findOne(Example < S > example) {
        return Optional.empty ();
    }

    @Override
    public < S extends Book > List < S > findAll(Example < S > example) {
        return null;
    }

    @Override
    public < S extends Book > List < S > findAll(Example < S > example, Sort sort) {
        return null;
    }

    @Override
    public < S extends Book > Page < S > findAll(Example < S > example, Pageable pageable) {
        return null;
    }

    @Override
    public < S extends Book > long count(Example < S > example) {
        return 0;
    }

    @Override
    public < S extends Book > boolean exists(Example < S > example) {
        return false;
    }
}
