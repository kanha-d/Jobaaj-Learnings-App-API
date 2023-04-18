package com.jobaaj.mapping.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jobaaj.mapping.dtos.ApiResponse;
import com.jobaaj.mapping.dtos.CertificateDto;
import com.jobaaj.mapping.dtos.CourseDto;
import com.jobaaj.mapping.dtos.MarkCourseDto;
import com.jobaaj.mapping.dtos.PasswordDto;
import com.jobaaj.mapping.dtos.StudentDto;
import com.jobaaj.mapping.services.CourseService;
import com.jobaaj.mapping.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student/")
public class StudentController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	@GetMapping("/courses/{userId}")
	public ResponseEntity<List<CourseDto>> getMyCourses(@PathVariable("userId") Integer userId) {

		// return new
		// ResponseEntity<List<CourseDto>>(this.courseService.listofRecommendedCourses(offset,limit,sort),HttpStatus.OK);
		return new ResponseEntity<List<CourseDto>>(this.courseService.listofStudentCourses(userId), HttpStatus.OK);
	}

	// Put - Update User
	@PutMapping("/update/{userId}")
	public ResponseEntity<StudentDto> updateUser(@Valid @RequestBody StudentDto StudentDto,
			@PathVariable("userId") Integer userId) {

		StudentDto updateStudentDto = this.studentService.updateUser(StudentDto, userId);
		return ResponseEntity.ok(updateStudentDto);

	}

	// Put - Update Password
	@PutMapping("/passwordupdate")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody PasswordDto passwordDto) {

		String msg = this.studentService.updatePassword(passwordDto);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	// Put - Update Course
	@PutMapping("/markLesson")
	public ResponseEntity<ApiResponse> markLesson(@Valid @RequestBody MarkCourseDto markCourseDto) {

		String msg = this.courseService.markLesson(markCourseDto);

		return new ResponseEntity<ApiResponse>(new ApiResponse(msg,true), HttpStatus.OK);

	}

	@PutMapping("/markSection")
	public ResponseEntity<ApiResponse> markSection(@Valid @RequestBody MarkCourseDto markCourseDto) {

		String msg = this.courseService.markSection(markCourseDto);

		return new ResponseEntity<ApiResponse>(new ApiResponse(msg,true), HttpStatus.OK);

	}

	// Delete Mapping - Delete User
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {

		this.studentService.deleteUserById(userId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Account has been Delted!", true), HttpStatus.OK);

	}

	// Get Mapping - All User
	@GetMapping("/list")
	public ResponseEntity<List<StudentDto>> getAllUsers() {

		return new ResponseEntity<List<StudentDto>>(this.studentService.listofUsers(), HttpStatus.OK);

	}

	@PostMapping("/courseCertificate")
	public ResponseEntity<ApiResponse> courseCertificate(@Valid @RequestBody CertificateDto certificateDto) {

		
		   String url = "https://api.jobaajlearnings.com/student?certificate="+certificateDto.getStudentId()+"course_id="+certificateDto.getCourseId();
		    
		   
	        RestTemplate restTemplate = new RestTemplate();
	        
	       
	        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
	        
	        
	        JSONObject jsonObject = new JSONObject(result);
	        JSONObject jsonObject2 =  new JSONObject(jsonObject.getString("body"));
	        String certificateUrl = jsonObject2.get("certificate_url").toString();
	        
	        
		return new ResponseEntity<ApiResponse>(new ApiResponse(certificateUrl,true), HttpStatus.OK);

	}

	// Get Mapping - My Profile
	@GetMapping("/user/{userId}")
	public ResponseEntity<StudentDto> getUser(@PathVariable("userId") Integer userId) {

		StudentDto usersDto = this.studentService.getUser(userId);

		return new ResponseEntity<StudentDto>(usersDto, HttpStatus.OK);

	}

}
