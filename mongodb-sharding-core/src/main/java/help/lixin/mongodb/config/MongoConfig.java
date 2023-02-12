package help.lixin.mongodb.config;

import help.lixin.mongodb.core.MongoDatabaseFactoryProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

@Configuration
public class MongoConfig {

    @Bean
    public BeanPostProcessor mongoDatabaseFactoryProxy() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof MongoDatabaseFactory) {
                    MongoDatabaseFactory target = (MongoDatabaseFactory) bean;
                    MongoDatabaseFactoryProxy proxy = new MongoDatabaseFactoryProxy(target);
                    return proxy;
                }
                return bean;
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(MongoOperations.class)
    public MongoTemplate mongoTemplate(MongoDatabaseFactory factory, MongoConverter converter) {
        return new help.lixin.mongodb.core.MongoTemplate(factory, converter);
    }
}
