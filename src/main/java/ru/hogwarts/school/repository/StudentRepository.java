package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);


    @Query(value = "select COUNT(*) from students",nativeQuery = true)
    int getStudentCount();

    @Query(value = "select AVG(age) from students", nativeQuery = true)
    int getAverageAge();

    @Query(value = "select * from students order by id desc limit 5", nativeQuery = true)
    List<Student> getLastStudent();

}
