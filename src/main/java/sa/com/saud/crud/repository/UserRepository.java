package sa.com.saud.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sa.com.saud.crud.model.CRUDUser;

//@Repository
public interface UserRepository extends JpaRepository<CRUDUser, Integer> {

	public CRUDUser findByUsername (String userName);
	
	
}