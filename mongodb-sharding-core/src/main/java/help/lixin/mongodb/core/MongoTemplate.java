package help.lixin.mongodb.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import help.lixin.mongodb.plugin.IMongoCollectionNameCustomizer;
import org.bson.Document;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MongoTemplate extends org.springframework.data.mongodb.core.MongoTemplate {

    public MongoTemplate(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    public MongoTemplate(MongoDatabaseFactory mongoDbFactory) {
        super(mongoDbFactory);
    }

    public MongoTemplate(MongoDatabaseFactory mongoDbFactory, MongoConverter mongoConverter) {
        super(mongoDbFactory, mongoConverter);
    }
    
    @Override
    public String getCollectionName(Class<?> entityClass) {
        String collectionName = super.getCollectionName(entityClass);
        IMongoCollectionNameCustomizer mongoCollectionNameCustomizer = getIMongoCollectionNameCustomizer();
        if (null != mongoCollectionNameCustomizer) {
            collectionName = mongoCollectionNameCustomizer.customizer(collectionName);
        }
        return collectionName;
    }


    public IMongoCollectionNameCustomizer getIMongoCollectionNameCustomizer() {
        IMongoCollectionNameCustomizer mongoCollectionNameCustomizer = null;
        ServiceLoader<IMongoCollectionNameCustomizer> load = ServiceLoader.load(IMongoCollectionNameCustomizer.class);
        Iterator<IMongoCollectionNameCustomizer> iterator = load.iterator();
        if (iterator.hasNext()) {
            mongoCollectionNameCustomizer = iterator.next();
        }
        return mongoCollectionNameCustomizer;
    }
}
