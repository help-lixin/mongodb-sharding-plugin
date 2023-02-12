package help.lixin.mongodb.service;

import java.util.List;

import help.lixin.mongodb.entity.User;

public interface IUserService {
	User save(User user);

	Boolean update(User user);

	List<User> findAll();
	
	Boolean delete(User user);
}
