package com.jobaaj.mapping.serviceimpls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobaaj.mapping.dtos.CouponDto;
import com.jobaaj.mapping.dtos.CouponResponse;
import com.jobaaj.mapping.dtos.EnrolCourseDto;
import com.jobaaj.mapping.entity.Coupon;
import com.jobaaj.mapping.entity.Course;
import com.jobaaj.mapping.entity.Enrol;
import com.jobaaj.mapping.entity.Payment;
import com.jobaaj.mapping.repos.CouponRepo;
import com.jobaaj.mapping.repos.CourseRepo;
import com.jobaaj.mapping.repos.EnrolRepo;
import com.jobaaj.mapping.repos.PaymentRepo;
import com.jobaaj.mapping.services.EmailService;
import com.jobaaj.mapping.services.EnrolService;


@Service
public class EnrolServiceImpl implements EnrolService {

	@Autowired
	CouponRepo couponRepo;
	
	@Autowired
	EnrolRepo enrolRepo;
	
	@Autowired
	PaymentRepo paymentRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	EmailService emailService;
	
	
	@Override
	public CouponResponse checkCouponValid(CouponDto couponDto)  {
		
	  int amount =  0;
	  String msgString = "";
		
	  Coupon coupon = couponRepo.findByCode(couponDto.getCode());
	  
	  
	  //coupon check  exist or not
	  if(coupon!=null) {
		  
		  //Code Limit Check
		  if(coupon.getCourseLimit() > coupon.getUsedLimit()) {
			  
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  

		   	  try {
		   		  
		   		  Date currentDate = sdf.parse(sdf.format(new Date()).toString());
		   		  Date expiryDate = coupon.getExpDate();
				   
				   if(expiryDate.after(currentDate)) {
					   
					   
					   
					   String applyCourse = couponDto.getCourseId()+"";
					   
					   String avialableCourses = coupon.getCourseUsed();
					   
					   String[] courses =  avialableCourses.split(",");
					   
					   boolean courseCheck = false;
					   
					   
					   for (String course : courses) {
						   
						   if(course.equals(applyCourse)) {
							   
							   courseCheck = true;
							   break;
						   }
						   
					   }
					   
					   
					   if(courseCheck) {
						   
						   
						   Course course = this.courseRepo.findById(couponDto.getCourseId()).orElse(null);
						   
						   if(course!= null) { 
							   int discount = coupon.getDiscountValue();
							   amount  = ((int)course.getDiscountedPrice() * discount) / 100;

							   msgString =  "Coupon available on this course!";
						   }else {
							   
							   amount = 0;
							   msgString =  "Coupon not found!";
						   }
						   
					   }else {
						   amount = 0;
						   msgString =  "Coupon not Availabe on this course!";
					   }
					   
					   
				   } else {
					   amount = 0;
					   msgString =  "Coupon has Expired!";
				   }
					   
		   	  } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			  
		  }else {
			  
			  amount = 0;
			  msgString =  "Coupon limit has excedeed!";
			  
			  
		  }
		  
	  }else {
		  amount = 0;
		  msgString =  "Coupon does't Exist or Expired!";
	  }
		
		
		
		return new CouponResponse(amount,msgString,true); 
	}
	
	

	@Override
	public String enrolCourse(EnrolCourseDto enDto) {
		
		String response = "";
		
		
		
		boolean checkEnrol = this.enrolRepo.existsBysIdAndCourseId(enDto.getStudentId(),enDto.getCourseId());
		
		String msg =  emailService.sendMail("k.developer.x@gmail.com", "Enrol Succesfully", "<h5>it's great to know you have join marketing program</h5>");

		
		if(!checkEnrol) {
			
			
			Enrol enrol = new Enrol();
			
			enrol.setCourseId(enDto.getCourseId());
			
			enrol.setSId(enDto.getStudentId());
			
			long unixTime = System.currentTimeMillis() / 1000L;
 
			enrol.setDateAdded(unixTime);
			
			this.enrolRepo.save(enrol);
			
			
			
			System.out.println(msg);
			//Add Transaction 
			Payment payment = new Payment();
			
			payment.setAmount(enDto.getAmount());			
			payment.setCouponId(null);		
			payment.setDateAdded(unixTime);			
			payment.setTransactionId(enDto.getPayId());		
			payment.setPaymentType(0);		
			payment.setStatus("1");		
			payment.setUserId(enDto.getStudentId());	

			this.paymentRepo.save(payment);
			
			
			response = "Student enrolled in Course succesfully!";
			
			
		}else {
			
			response = "Student already enrolled in Course";
			
		}
		
		
		return response;
		
	}
	
	

}
