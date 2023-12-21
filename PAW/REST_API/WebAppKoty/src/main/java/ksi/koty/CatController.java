package ksi.koty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class CatController {
    private final CatRepository repository;

    CatController(CatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cats")
    List<Cat> getCats() {
        return repository.findAll();
    }

    @GetMapping("/cats/{id}")
    ResponseEntity<?> getCat(@PathVariable("id") Long id) {
        Optional<Cat> cat = repository.findById(id);
        return cat.map(ResponseEntity::ok)
                // HTTP status 404
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/fun-fact")
    String getFunFact() {
        final String uri = "https://catfact.ninja/fact";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @PostMapping("/cats")
    ResponseEntity<?> newCat(@RequestBody Cat newCat) {
        if (newCat.getName() == null || newCat.getBreed() == null) {
            return ResponseEntity.badRequest().body("Name and breed are required");
        }
        Cat savedCat = repository.save(newCat);
        return ResponseEntity.ok(savedCat);
    }

    @PutMapping("/cats/{id}")
    Cat replaceCat(@RequestBody Cat newCat, @PathVariable("id") Long id) {
        return repository.findById(id)
                .map(cat -> {
                    cat.setName(newCat.getName());
                    cat.setBreed(newCat.getBreed());
                    return repository.save(cat);
                })
                .orElseGet(() -> {
                    newCat.setId(id);
                    return repository.save(newCat);
                });
    }

    @DeleteMapping("/cats/{id}")
    public ResponseEntity<?> deleteCat(@PathVariable("id") Long id) {
        Optional<Cat> catToDelete = repository.findById(id);

        if (catToDelete.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {  // HTTP status 404
            return ResponseEntity.badRequest().body("Cat with the specified ID does not exist");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();

        // HTTP status 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error. Please try again later.");
    }
    // example of an error
    @GetMapping("/cats/error-example")
    public ResponseEntity<String> generateError() {
        throw new RuntimeException("This is a simulated internal server error");
    }


}