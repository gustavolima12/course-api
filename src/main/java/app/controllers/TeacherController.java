package app.controllers;

import app.models.Teacher;
import app.services.TeacherService;
import app.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static app.util.APIEndpoints.TEACHERS;

@RestController
@RequestMapping(TEACHERS)
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers() {
        List<Teacher> teachers = teacherService.getTeachers();
        return ResponseEntity.ok().body(teachers);
    }

    @PostMapping
    public ResponseEntity<?> addTeacher(@Valid @RequestBody Teacher newTeacher, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(erro -> errors.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<Teacher>(errors));
        }
        Teacher teacher = teacherService.createTeacher(newTeacher);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(teacher.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
