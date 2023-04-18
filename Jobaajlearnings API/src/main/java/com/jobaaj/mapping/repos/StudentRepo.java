package com.jobaaj.mapping.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.jobaaj.mapping.entity.Course;
import com.jobaaj.mapping.entity.Student;


@EnableJpaRepositories
public interface StudentRepo extends JpaRepository<Student, Integer> {

	public boolean existsByEmail(String email);

//	public boolean existsByEmail(String email);
//	
//	public boolean existsByContact(String contact);

	public Student findByEmail(String email);
	
	
	

//	@Query(value="select s from Student s where s.sid = :id",nativeQuery = true)
//	void updatePassword(@Param("newpass") String password, @Param("id") int id);

//	
//	@Modifying
//	@Query(value = "UPDATE  student s set s.name = ?1 WHERE  s.sid = :id AND s.name = :newpass", nativeQuery = true)
//	void updatePassword(@Param("newpass") String password,@Param("id") Integer studentId);

}
