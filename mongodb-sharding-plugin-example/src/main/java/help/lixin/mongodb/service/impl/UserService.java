package help.lixin.mongodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import help.lixin.mongodb.entity.User;
import help.lixin.mongodb.service.IUserService;

/**
 * 强烈不要用:MongoRepository,MongoDB在单机(以及副本集)情况下都没问题,但是,在Collection分片集群的情况下,会抛出如下异常:<br/>
 * org.springframework.dao.DataIntegrityViolationException: Failed to target
 * upsert by query :: could not extract exact shard key; nested exception is
 * com.mongodb.MongoWriteException: Failed to target upsert by query :: could
 * not extract exact shard key <br/>

 * @author lixin
 */
@Service
public class UserService implements IUserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public User save(User user) {
		return mongoTemplate.insert(user);
	}

	@Override
	public Boolean update(User user) {
		Query query = Query.query(Criteria.where("_id").is(user.getId()));
		// { "$set" : { "age" : 100, "gender" : "男" } }
		Update update = Update.update("age", user.getAge());
		update.set("gender", user.getGender());

		UpdateResult result = mongoTemplate.updateMulti(query, update, User.class);
		return result.getModifiedCount() > 0 ? true : false;
	}

	public Boolean delete(User user) {
		Query query = Query.query(Criteria.where("name").is(user.getName()));
		DeleteResult result = mongoTemplate.remove(query, User.class);
		return result.getDeletedCount() > 0 ? true : false;
	}

	@Override
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}
}
