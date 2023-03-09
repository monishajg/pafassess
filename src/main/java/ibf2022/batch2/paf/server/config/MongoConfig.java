// package ibf2022.batch2.paf.server.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.mongodb.core.MongoTemplate;

// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;

// import ibf2022.batch2.paf.server.utils.Constants;

// @Configuration
// public class MongoConfig implements Constants {

//     // Inject the mongo connection string
//     @Value("${mongo.url}")
//     private String mongoUrl;
    
//     // Create and initialize MongoTemplate
//     @Bean
//     public MongoTemplate createMongoTemplate() {
//         //Create a MongoClient with the mongo connection string
//         MongoClient client = MongoClients.create(mongoUrl); //remember s
//         return new MongoTemplate(client, MONGO_DATABASE_NAME); //template will work with shows
//     }
// }
