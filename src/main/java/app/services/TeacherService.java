package app.services;

import app.models.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(Teacher teacher);
    List<Teacher> getTeachers();
    Teacher getTeacherById(Long id);
    void deleteTeacherById(Long id);
    boolean isTeacherExistById(Long id);

}
