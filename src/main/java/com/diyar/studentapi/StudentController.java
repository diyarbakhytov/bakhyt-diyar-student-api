package com.diyar.studentapi;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Student> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Student one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        student.setId(null);
        return repo.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student data) {
        Student student = one(id);
        student.setFullName(data.getFullName());
        student.setEmail(data.getEmail());
        student.setStudentId(data.getStudentId());
        student.setGroupName(data.getGroupName());
        return repo.save(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        one(id);
        repo.deleteById(id);
    }
}
