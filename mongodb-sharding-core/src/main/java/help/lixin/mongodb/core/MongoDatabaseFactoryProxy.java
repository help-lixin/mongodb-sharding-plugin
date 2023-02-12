package help.lixin.mongodb.core;

import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
import help.lixin.mongodb.plugin.IMongoDatabaseCustomizer;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoDatabaseFactorySupport;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.ServiceLoader;

public class MongoDatabaseFactoryProxy implements MongoDatabaseFactory {

    private MongoDatabaseFactory target;

    private String databaseName;

    public MongoDatabaseFactoryProxy(MongoDatabaseFactory target) {
        this.target = target;
        try {
            Field databaseNameField = MongoDatabaseFactorySupport.class.getDeclaredField("databaseName");
            databaseNameField.setAccessible(Boolean.TRUE);
            databaseName = (String) databaseNameField.get(target);
        } catch (Exception ignore) {
        }
    }

    public MongoDatabaseFactory getTarget() {
        return target;
    }

    @Override
    public CodecRegistry getCodecRegistry() {
        return target.getCodecRegistry();
    }

    @Override
    public MongoDatabaseFactory withSession(ClientSessionOptions options) {
        return target.withSession(options);
    }

    @Override
    public boolean isTransactionActive() {
        return target.isTransactionActive();
    }

    @Override
    public MongoDatabase getMongoDatabase() throws DataAccessException {
        return this.getMongoDatabase(databaseName);
    }

    @Override
    public MongoDatabase getMongoDatabase(String dbName) throws DataAccessException {
        String mongoDatabaseName = dbName;
        IMongoDatabaseCustomizer mongoDatabaseCustomizer = getMongoDatabaseCustomizer();
        if (null != mongoDatabaseCustomizer) {
            mongoDatabaseName = mongoDatabaseCustomizer.customizer(dbName);
        }
        return target.getMongoDatabase(mongoDatabaseName);
    }

    @Override
    public PersistenceExceptionTranslator getExceptionTranslator() {
        return target.getExceptionTranslator();
    }

    @Override
    public ClientSession getSession(ClientSessionOptions options) {
        return target.getSession(options);
    }

    @Override
    public MongoDatabaseFactory withSession(ClientSession session) {
        return target.withSession(session);
    }

    public IMongoDatabaseCustomizer getMongoDatabaseCustomizer() {
        IMongoDatabaseCustomizer mongoDatabaseCustomizer = null;
        ServiceLoader<IMongoDatabaseCustomizer> load = ServiceLoader.load(IMongoDatabaseCustomizer.class);
        Iterator<IMongoDatabaseCustomizer> iterator = load.iterator();
        if (iterator.hasNext()) {
            mongoDatabaseCustomizer = iterator.next();
        }
        return mongoDatabaseCustomizer;
    }
}
