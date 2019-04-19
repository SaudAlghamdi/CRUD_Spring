package sa.com.saud.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sa.com.saud.crud.repository.EmployeeRepository;
import sa.com.saud.crud.model.Employee;

/**
 * @author salghamdi
 *
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * @return List<Employee>
	 */
	public List<Employee> findallemps() {

		ArrayList<Employee> employeesList = (ArrayList<Employee>) employeeRepository.findAll();

		return employeesList;

	}

	/**
	 * @param employee
	 */
	public void  addEmployee(Employee employee) {

		
			employeeRepository.save(employee);
			}

	public List<Employee> retrieveEmpByFN(String firstName) {
		

		return employeeRepository.findByfirstName(firstName);

	}

	/**
	 * @param empId
	 */
	public void deleteEmpById(int empId) {

		employeeRepository.deleteById(empId);


	}

	/**
	 * @param employee
	 */
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

	/**
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {

		employeeRepository.save(employee);
	}
	
	public List<Employee> searchBySalary (String salary, String salary_){
		
		return employeeRepository.findBysalary(salary, salary_);
		
	}

}
