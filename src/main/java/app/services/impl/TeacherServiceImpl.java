package app.services.impl;

import app.models.Teacher;
import app.repositories.TeacherRepository;
import app.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getTeachers() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return this.teacherRepository.findOne(id);
    }

    @Override
    public void deleteTeacherById(Long id) {
        this.teacherRepository.delete(id);
    }

    @Override
    public boolean isTeacherExistById(Long id) {
        return this.teacherRepository.exists(id);
    }

}
