package ajbc.learn.mongodb.furniture.crud;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

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

import ajbc.learn.mongodb.furniture.models.Chair;
import ajbc.learn.mongodb.furniture.models.Measurement;
import ajbc.learn.mongodb.furniture.models.Pillow;
import ajbc.learn.mongodb.furniture.models.Pillow.PillowColor;
import ajbc.learn.mongodb.furniture.models.Pillow.PillowShape;
import ajbc.learn.nosql.MyConnectionString;
import static com.mongodb.client.model.Filters.*;

public class Runner {
	
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
			final String DB_NAME = "Furniture";
			MongoDatabase database = mongoClient.getDatabase(DB_NAME);

			final String COLLECTION = "Chairs";
			MongoCollection<Document> myCollection = database.getCollection(COLLECTION);
			
			ChairsDAO chairDao = new ChairsDAO(myCollection);
			
//			Measurement m1 = new Measurement(15,20,25);
//			Chair c1 = new Chair("Ikea", "Model1", true, 50, m1);
//			InsertOneResult result1 = chairDao.insertChair(c1);
//			System.out.println(result1.wasAcknowledged());
//			
//			Chair c2 = new Chair("Ikea", "Model2", false, 100, m1);
//			List<Chair> chairsList = Arrays.asList(c1, c2);
//			InsertManyResult result2 = chairDao.insertChairs(chairsList);
//			System.out.println(result2.wasAcknowledged());
			
//			ObjectId chairId = new ObjectId("62b05bb258bf6e2cf4cf2b2e");
//			
//			Chair c = chairDao.getChairById(chairId);
//			System.out.println(c);
//			List<Chair> stools = chairDao.getStools();
//			stools.forEach(System.out::println);
//			List<Chair> ikeaChairs = chairDao.getChairsByManufacturer("Ikea");
//			ikeaChairs.forEach(System.out::println);
//			List<Chair> chairs = chairDao.getChairsUnderHeight(20);
//			chairs.forEach(System.out::println);
//			c.setPrice(90.5f);
//			c = chairDao.updateChairAndGetOldChair(chairId, c);
//			System.out.println(c);
//			List<Chair> updated = chairDao.updateChairs(eq("manufacturer","Ikea"), c);
//			updated.forEach(System.out::println);
//			Pillow pillow = new Pillow(PillowShape.RECTANGLE, PillowColor.BLUE);
//			c = chairDao.addPillowToChair(chairId, pillow);
//			System.out.println(c);
			
//			Chair deleted = chairDao.deleteChairById(new ObjectId("62b05bb458bf6e2cf4cf2b2f"));
//			System.out.println(deleted);
//			
//			Chair newChair = new Chair("Ikea123","NewModel",true,100,new Measurement(50,100,100));
//			chairDao.insertChair(newChair);
//			chairDao.deleteChairsByManufacturer("Ikea");
			chairDao.deleteAllChairs();
		}
	}
	

}
