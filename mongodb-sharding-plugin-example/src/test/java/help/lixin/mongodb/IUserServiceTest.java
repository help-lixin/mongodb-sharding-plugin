package help.lixin.mongodb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import help.lixin.mongodb.entity.User;
import help.lixin.mongodb.service.IUserService;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {

	@Autowired
	private IUserService userService;

	@Test
	public void testSave() throws Exception {
		User user = new User();
		user.setId("hello");
		user.setName("李四");
		user.setAge(18);
		user.setGender("女");
		User persiteEntiry = userService.save(user);
		Assert.assertEquals(user.getId(), persiteEntiry.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		User user = new User();
		user.setId("hello");
		user.setName("李四");
		user.setAge(100);
		user.setGender("男");
		boolean isSucc = userService.update(user);
		Assert.assertEquals(true, isSucc);
	}

	@Test
	public void testDel() throws Exception {
		User user = new User();
		user.setId("hello");
		user.setName("李四");
		user.setAge(100);
		user.setGender("男");
		boolean isSucc = userService.delete(user);
		Assert.assertEquals(true, isSucc);
	}

	@Test
	public void testList() throws Exception {
		List<User> list = userService.findAll();
		Assert.assertEquals(1000, list.size());
	}

}
