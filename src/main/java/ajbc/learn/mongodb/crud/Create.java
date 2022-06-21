package ajbc.learn.mongodb.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;

import ajbc.learn.mongodb.models.Exam;
import ajbc.learn.mongodb.models.Student;
import ajbc.learn.nosql.MyConnectionString;

public class Create {
	
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
			
			Document studentDoc = Basics.createStudentDoc(1, 2, "Rotem", "Levi");
//			InsertOneResult insertResult = myCollection.insertOne(studentDoc);
//			System.out.println(insertResult.wasAcknowledged());
			
			List<Exam> exams = new ArrayList<>();
			exams.add(new Exam("Java",89));
			exams.add(new Exam("FloorWashing",54));

			Student student = new Student(1234, 5678, "Moshe", "Ufnik", exams);
			Gson gson = new Gson();
			String studJson = gson.toJson(student);
			
			Document studentDoc1 = Document.parse(studJson);
//			System.out.println(studDoc.toJson(prettyPrint));
//			InsertOneResult insertResult1 = myCollection.insertOne(studentDoc1);
//			System.out.println(insertResult1.wasAcknowledged());
			
			List<Document> studDocs = new ArrayList<>();
			studDocs.add(studentDoc);
			studDocs.add(studentDoc1);
			
			InsertManyResult manyResult = myCollection.insertMany(studDocs);
			boolean ack = manyResult.wasAcknowledged();
			System.out.println(ack);

		}

	}
	


}
