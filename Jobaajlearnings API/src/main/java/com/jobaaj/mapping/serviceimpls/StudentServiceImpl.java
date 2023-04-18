package com.jobaaj.mapping.serviceimpls;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobaaj.mapping.dtos.CourseDto;
import com.jobaaj.mapping.dtos.PasswordDto;
import com.jobaaj.mapping.dtos.StudentDto;
import com.jobaaj.mapping.entity.Course;
import com.jobaaj.mapping.entity.Student;
import com.jobaaj.mapping.exceptions.DuplicateEntryException;
import com.jobaaj.mapping.exceptions.ResourceNotFound;
import com.jobaaj.mapping.repos.StudentRepo;
import com.jobaaj.mapping.repos.UserRepo;
import com.jobaaj.mapping.services.StudentService;


@Service
public class StudentServiceImpl implements StudentService,UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StudentDto createUser(StudentDto dto) {
		

		boolean checkEmail = studentRepo.existsByEmail(dto.getEmail());
		
		if(!checkEmail) {
			
			
			Random random = new Random();   
			int otp = random.nextInt(1000000);
			
			String passwordString = passwordEncoderD().encode(dto.getPassword());
			
			dto.setPassword(passwordString);
			dto.setStatus(0);
			dto.setVerification_code(otp);
			
			Student user = DtoToStudent(dto);
		        
			Student savedUser = this.studentRepo.save(user);
			
			return this.StudentToDto(savedUser);
		
		}
		else {
			throw new DuplicateEntryException("Email",dto.getEmail());  
		}
		
	}

	
	@Override
	public StudentDto getUser(Integer userId) {
		
		Student user  = this.studentRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User","Id",String.valueOf(userId)));
		
		System.out.println(user);
		
		return StudentToDto(user);
		
	}

	@Override
	public void deleteUserById(Integer userId) {
		userRepo.deleteById(userId);
	}

	@Override
	public List<StudentDto> listofUsers() {
		
		List<Student> users = this.studentRepo.findAll();
		
		List<StudentDto> StudentDtos = users.stream().map(user->this.StudentToDto(user)).collect(Collectors.toList());
		
		return StudentDtos;
	}
	
	

	@Override
	public StudentDto updateUser(StudentDto StudentDto, Integer userId) {
		
		    Student user = this.studentRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User"," Id ",String.valueOf(userId)));
		
			user.setName(StudentDto.getName());
			user.setDob(StudentDto.getDob());
			user.setSkills(StudentDto.getSkills());
			
			Student updatedUser = this.studentRepo.save(user);
			
			StudentDto updateStudentDto = this.StudentToDto(updatedUser);
			
			return updateStudentDto;
		
	}
	
	

	public StudentDto StudentToDto(Student user) {
		return this.modelMapper.map(user,StudentDto.class);
	}
	
	public Student DtoToStudent(StudentDto student) {
		return this.modelMapper.map(student,Student.class);
	}

	public CourseDto CourseToDto(Course pro) {
		return this.modelMapper.map(pro,CourseDto.class);
	}

	
	private String getEncodedPassword(String password) {
		
		try {
			
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hash);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return password;
		
	}


	@Override
	public StudentDto loginUser(StudentDto StudentDto) {
		
		
		Student user  = this.studentRepo.findByEmail(StudentDto.getEmail());
		
		
		if(user!=null) {
			
			System.out.println("yes");
			String encodedPassword = getEncodedPassword(StudentDto.getPassword());
			
			if(encodedPassword.equals(user.getPassword())) {
				
				return this.modelMapper.map(user,StudentDto.class);
				
			}else {
			 
				throw new ResourceNotFound("Password is Incorrect","Email",StudentDto.getEmail());
			}
			
		}else {
			throw new ResourceNotFound("User"," Email ",StudentDto.getEmail());
		}
			
		
	}


	public StudentDto emailVerify(StudentDto StudentDto) {
		
		Student user  = this.studentRepo.findByEmail(StudentDto.getEmail());
		
		if(user!=null) {
			
			System.out.println("yes");
			
			if(user.getVerification_code() == (user.getVerification_code())) {
				
				StudentDto.setVerification_code(0);
				StudentDto.setStatus(1);
				
				
				Student updateedUser = DtoToStudent(StudentDto);
				
				this.studentRepo.save(updateedUser);
				
				
				return this.modelMapper.map(user,StudentDto.class);
				
			}else {
			 
				throw new ResourceNotFound("OTP is Incorrect","OTP",StudentDto.getEmail());
			}
			
		}else {
			throw new ResourceNotFound("User"," Email ",StudentDto.getEmail());
		}
		
		
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Student studentInfo = studentRepo.findByEmail(username);
		
		return  User.withUsername(studentInfo.getEmail()).password(studentInfo.getPassword()).roles("NORMAL").build();
	
	}

	
	@Bean
	PasswordEncoder passwordEncoderD() {
		return new BCryptPasswordEncoder();
	}


	@Override
	public String updatePassword(PasswordDto passwordDto) {
		

	    Student user = this.studentRepo.findById(passwordDto.getStudentId()).orElseThrow(()-> new ResourceNotFound("Student"," Id ",String.valueOf(passwordDto.getStudentId())));
	    
 	   
	    if(passwordEncoderD().matches(passwordDto.getOldpassword(),user.getPassword())) { 	 
	    	
	  	    
	  	    String newPassString = passwordEncoderD().encode(passwordDto.getNewpasword());	    
	  	 	   
	  	    user.setPassword(newPassString);
	  		
	  		this.studentRepo.save(user);
	  		
	  		return "updated";
			
	    	
	    }else {
	    	
	    	return "password not matched";
	    }
	
	    
	    
	    
		
		
		
		
	}


	
	
//	@Override
//	public StudentDto getStudent(Integer empId) {
//		
//		Student user  = this.studentRepo.findById(empId).orElseThrow(() -> new ResourceNotFound("Employee","Id",String.valueOf(empId)));
//		
//		return StudentToDto(user);
//		
//	}


//	@Override
//	public CourseDto getCourse(Integer empId) {
//		
//		Course project  = this.courseRepo.findById(empId).orElseThrow(() -> new ResourceNotFound("Employee","Id",String.valueOf(empId)));
//		
//		CourseDto projectDto =  CourseToDto(project);
//		
//		return projectDto;
//		
//	}




}
