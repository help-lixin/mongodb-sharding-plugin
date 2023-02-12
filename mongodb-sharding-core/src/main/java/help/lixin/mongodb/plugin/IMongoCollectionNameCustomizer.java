package help.lixin.mongodb.plugin;

public interface IMongoCollectionNameCustomizer {

    public String customizer(String oldCollectionName);

}
