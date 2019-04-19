package sa.com.saud.crud.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import sa.com.saud.crud.service.EmployeeService;
import sa.com.saud.crud.model.Employee;
import sa.com.saud.crud.response.Message;

/**
 * @author salghamdi
 *
 */
@CrossOrigin
@RestController
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	/**
	 * @return
	 */
	@GetMapping("/allemployees")
	public List<Employee> findallEmployees() {

		return employeeService.findallemps();

	}

	/**
	 * @return
	 */
	@GetMapping("/salary")
	public List<Employee> findEmpBySalaryRange(@RequestParam("firstSalary") String salary, @RequestParam("secondSalary") String salary_) {

		return employeeService.searchBySalary(salary, salary_);

	}

	
	/**
	 * @param employee
	 */
	@PostMapping("/addemployee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		
		try {
		employeeService.addEmployee(employee);
		}catch (Exception e){
		
			return new ResponseEntity<>( new Message("Employee Not Added, there is issue please try again") ,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(new Message("Employee Added Successfully"),HttpStatus.OK); 
	}
	

	/**
	 * @param firstName
	 * @return
	 */
	@GetMapping("/employee/{firstName}")
	public List<Employee> findEmployeeByName (@PathVariable("firstName") String firstName){
		
		return employeeService.retrieveEmpByFN(firstName);
	}
	
	/**
	 * @param id
	 */
	@DeleteMapping("/deleteEmp/{id}")
	public void deleteEmpById (@PathVariable("id") int id) {
		
		employeeService.deleteEmpById(id);
	}

	@DeleteMapping("/deleteEmployee")
	public void deleteEmp (@RequestBody Employee employee) {
		
		employeeService.deleteEmployee(employee);
		
	}
	
	@PutMapping("/updateEmp")
	public void updateEmployee (@RequestBody Employee employee) {
		
		employeeService.updateEmployee(employee);
		
	}
}
