package hms.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;

import hms.pojo.User;

public interface UserRope  extends JpaRepository<User, Long>{
	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);

}
