package help.lixin.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(MongoAutoConfiguration.class);

    {
        if (logger.isDebugEnabled()) {
            logger.debug("enabled Module [{}] SUCCESS.", MongoAutoConfiguration.class.getSimpleName());
        }
    }
}
