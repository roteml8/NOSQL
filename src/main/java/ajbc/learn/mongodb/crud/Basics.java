package ajbc.learn.mongodb.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import ajbc.learn.nosql.MyConnectionString;

public class Basics {

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
			
			final String DB_NAME = "my_own_db";
			MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	
			final String COLLECTION = "myCollection";
			MongoCollection<Document> myCollection = database.getCollection(COLLECTION);
			
			Document studentDoc = createStudentDoc(1, 2, "Rotem", "Levi");
			InsertOneResult insertResult = myCollection.insertOne(studentDoc);
			System.out.println(insertResult.wasAcknowledged());
			
			mongoClient.listDatabaseNames().forEach(System.out::println);

		}

	}
	
	public static Document createStudentDoc(int studentId, int classId, String firstName, String lastName)
	{
		Document studDoc = new Document("student_id", studentId).append("class_id", classId).append("first_name", firstName)
				.append("last_name", lastName);
		List<Document> scores = new ArrayList<>();
		scores.add(new Document("topic","java").append("score", 99));
		scores.add(new Document("topic","english").append("score", 99));
		scores.add(new Document("topic","math").append("score", 99));
		
		studDoc.append("exams", scores);
		return studDoc;
		
	}
}
