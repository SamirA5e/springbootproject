package com.onlyjavatech.samir;

import com.onlyjavatech.samir.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootProjectApplication.class, args);
		EmployeeRepository employeeRepository=context.getBean(EmployeeRepository.class);
//-----------------  This is my create ----------------

//		Employee emp1 = new Employee();
//
//		emp1.setFirstname("tejas1");
//		emp1.setLastname("patil1");
//		emp1.setEmailId("tejas1@gmail.com");
//
////		Employee emp1 = employeeRepository.save(emp);
////		System.out.println(emp1);
//
//		Employee emp2 = new Employee();
//
//		emp2.setFirstname("arbaz1");
//		emp2.setLastname("khan1");
//		emp2.setEmailId("arbaz1@gmail.com");
//
//		List<Employee> employee = new ArrayList<Employee>();
//		employee.add(emp1);
//		employee.add(emp2);
//=========
//		List<Employee> employeeList = new ArrayList<>();
//		for (int i = 0; i < employeeCount; i++) {
//			employeeList.add(new Employee());
//		}
//		employeeRepository.saveAll(employeeList);
//==========
//		Iterable<Employee> reult=employeeRepository.saveAll(employee);
//
//		reult.forEach(empl->{
//			System.out.println("user : "+empl);
//		});
//
//----------------------   This is my update  --------------------------

//		Optional<Employee> optional =employeeRepository.findById(2);
//		Employee emp = optional.get();
//
//		emp.setFirstname("updated");
//		Employee result =employeeRepository.save(emp);
//		System.out.println(result);

//------------------------- This is my single delete ------------------------------------

//		employeeRepository.deleteById(4);
//		System.out.println("Deleted");



	}

}
