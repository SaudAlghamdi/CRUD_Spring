package sa.com.saud.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sa.com.saud.crud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findByfirstName (String firstName);
	
	 @Query("select e from Employee e where e.salary between :salary1 and :salary2")
	 List<Employee> findBysalary(String salary1, String salary2);
	
	 
	
}