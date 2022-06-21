package ajbc.learn.mongodb.crud;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ajbc.learn.nosql.MyConnectionString;

public class Read {

	public static void main(String[] args) {
		
		ConnectionString connectionString = MyConnectionString.uri();
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .serverApi(ServerApi.builder()
		            .version(ServerApiVersion.V1)
		            .build())
		        .build();
		try (MongoClient mongoClient = MongoClients.create(settings);)
		{
			JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
			final String DB_NAME = "my_own_db";
			MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	
			final String COLLECTION = "myCollection";
			MongoCollection<Document> myCollection = database.getCollection(COLLECTION);
			
			 
		}
	}
}
