package rutke.julio.gptw.example.endpoint.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rutke.julio.gptw.core.model.Example;
import rutke.julio.gptw.example.endpoint.service.ExampleService;

@RestController
@RequestMapping("/example/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExampleController {
    private final ExampleService exampleService;

    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Example>> list(Pageable pageable){
        return new ResponseEntity<>(exampleService.list(pageable), HttpStatus.OK);
    }
}
