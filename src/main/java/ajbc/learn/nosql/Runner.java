package ajbc.learn.nosql;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Runner {

	public static void main(String[] args) {
		

		ConnectionString connectionString = new ConnectionString
				("mongodb+srv://rotem:candylane@myfirstcluster.lswbo.mongodb.net/?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .serverApi(ServerApi.builder()
		            .version(ServerApiVersion.V1)
		            .build())
		        .build();
		try (MongoClient mongoClient = MongoClients.create(settings);)
		{
			MongoDatabase database = mongoClient.getDatabase("sample_mflix");
			FindIterable<Document> docs = database.getCollection("movies").find();
			int num = 100;
			int counter = 0;
			for (Document d: docs)
			{
				if (counter == num)
				{	
					System.out.println("---------------------------------");
					System.out.println(d.toJson());
					System.out.println("---------------------------------");
					break;
				}
				counter++;
			}
		}

	}

}
