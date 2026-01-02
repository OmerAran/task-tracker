package com.omeraran.task_tracker.user;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @GetMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User retrieve(@PathVariable Long id){
        log.info("[START] UserController - retrieve - user id: {}", id);
        return service.retrieve(id);

    }

    @PostMapping("/api/v1/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User request){
        log.info("[START] UserController - create");
        return service.create(request);
    }
    
    @PutMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable Long id, @RequestBody User request){
        log.info("[START] UserController - update - user id: {}", id);
        return service.update(id, request);

    }

    @DeleteMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        log.info("[START] UserController - delete - user id: {}", id);

        service.delete(id);

        log.info("[END] UserController - delete - user id : {}", id);
    }
}
