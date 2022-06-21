package ajbc.learn.mongodb.furniture.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import ajbc.learn.mongodb.furniture.models.Chair;
import ajbc.learn.mongodb.furniture.models.Pillow;

public class ChairsDAO {
	

	private static Gson gson = new Gson();
	private MongoCollection<Document> collection;

	public ChairsDAO(MongoCollection<Document> collection)
	{
		this.collection = collection;
	}
	
	public InsertOneResult insertChair(Chair chair)
	{
		String chairJson = gson.toJson(chair);
		Document chairDoc = Document.parse(chairJson);
		InsertOneResult insertResult = collection.insertOne(chairDoc);
		return insertResult;
	}

	public InsertManyResult insertChairs(List<Chair> chairs)
	{
		List<Document> chairDocs = new ArrayList<>();
		chairs.forEach(t->chairDocs.add(Document.parse(gson.toJson(t))));
		InsertManyResult insertResult = collection.insertMany(chairDocs);
		return insertResult;
	}
	
	public Chair getChairById(ObjectId id)
	{
		Document chairDoc = collection.find(eq("_id",id)).first();
		String chairJson = chairDoc.toJson();
		Chair chair = gson.fromJson(chairJson, Chair.class);
		return chair;
	}
	
	public List<Chair> getStools()
	{
		List<Chair> stools = new ArrayList<>();
		FindIterable<Document> iterable = collection.find(eq("isStool",true));
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext())
		{
			String chairJson = cursor.next().toJson();
			stools.add(gson.fromJson(chairJson, Chair.class));
		}
		return stools;
		
	}
	
	public List<Chair> getChairsByManufacturer(String manufacturer)
	{
		List<Chair> chairs = new ArrayList<>();
		FindIterable<Document> iterable = collection.find(eq("manufacturer",manufacturer));
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext())
		{
			String chairJson = cursor.next().toJson();
			chairs.add(gson.fromJson(chairJson, Chair.class));
		}
		return chairs;
	}
	
	public List<Chair> getChairsInPriceRange(float minPrice, float maxPrice)
	{
		List<Chair> chairs = new ArrayList<>();
		FindIterable<Document> iterable = collection.find(and(gte("price",minPrice), lte("price",maxPrice)));
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext())
		{
			String chairJson = cursor.next().toJson();
			chairs.add(gson.fromJson(chairJson, Chair.class));
		}
		return chairs;

	}
	
	public List<Chair> getChairsByManufacturers(List<String> manufacturers)
	{
		List<Chair> chairs = new ArrayList<>();
		FindIterable<Document> iterable = collection.find(in("manufacturer",manufacturers));
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext())
		{
			String chairJson = cursor.next().toJson();
			chairs.add(gson.fromJson(chairJson, Chair.class));
		}
		return chairs;

	}
	
	public List<Chair> getChairsUnderHeight(double height)
	{
		List<Chair> chairs = new ArrayList<>();
		FindIterable<Document> iterable = collection.find(lt("measurement.height",height));
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext())
		{
			String chairJson = cursor.next().toJson();
			chairs.add(gson.fromJson(chairJson, Chair.class));
		}
		return chairs;
	}
	
	public Chair updateChair(ObjectId id, Chair chair)
	{
		Bson filter = eq("_id", id);
		Bson update1 = set("manufacturer", chair.getManufacturer());
		Bson update2 = set("modelName", chair.getModelName());
		Bson update3 = set("isStool", chair.isStool());
		Bson update4 = set("price", chair.getPrice());
		Bson update5 = set("measurement.height", chair.getMeasurement().getHeight());
		Bson update6 = set("measurement.weight", chair.getMeasurement().getWeight());
		Bson update7 = set("measurement.depth", chair.getMeasurement().getDepth());

		Bson updates = combine(update1, update2, update3, update4, update5, update6, update7);
		UpdateResult updateReuslt = collection.updateOne(filter, updates);
		return chair;	
	}
	
	public Chair updateChairAndGetOldChair(ObjectId id, Chair chair)
	{
		Bson filter = eq("_id", id);
		Bson update1 = set("manufacturer", chair.getManufacturer());
		Bson update2 = set("modelName", chair.getModelName());
		Bson update3 = set("isStool", chair.isStool());
		Bson update4 = set("price", chair.getPrice());
		Bson update5 = set("measurement.height", chair.getMeasurement().getHeight());
		Bson update6 = set("measurement.weight", chair.getMeasurement().getWeight());
		Bson update7 = set("measurement.depth", chair.getMeasurement().getDepth());

		Bson updates = combine(update1, update2, update3, update4, update5, update6, update7);
        Document oldVersion = collection.findOneAndUpdate(filter, updates);
        return gson.fromJson(oldVersion.toJson(), Chair.class);
	}
	
	public List<Chair> updateChairs(Bson filter, Chair chair)
	{
		Bson update1 = set("manufacturer", chair.getManufacturer());
		Bson update2 = set("modelName", chair.getModelName());
		Bson update3 = set("isStool", chair.isStool());
		Bson update4 = set("price", chair.getPrice());
		Bson update5 = set("measurement.height", chair.getMeasurement().getHeight());
		Bson update6 = set("measurement.weight", chair.getMeasurement().getWeight());
		Bson update7 = set("measurement.depth", chair.getMeasurement().getDepth());

		Bson updates = combine(update1, update2, update3, update4, update5, update6, update7);
		UpdateResult updateReuslt = collection.updateMany(filter, updates);
		List<Chair> chairs = new ArrayList<>();
		FindIterable<Document> iterable = collection.find(filter);
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext())
		{
			String chairJson = cursor.next().toJson();
			chairs.add(gson.fromJson(chairJson, Chair.class));
		}
		return chairs;
		
	}
	
	//NOT WORKING - TODO
	public Chair addPillowToChair(ObjectId id, Pillow pillow)
	{
		Bson filter = eq("_id", id);
		Bson update = addToSet("pillows", pillow);
		UpdateOptions options = new UpdateOptions().upsert(true);
        UpdateResult updateResult = collection.updateOne(filter, update, options);
        return getChairById(id);
	}
	
	public Chair deleteChairById(ObjectId id)
	{
		 Bson filter = eq("_id", id);
         Document doc = collection.findOneAndDelete(filter);
         return gson.fromJson(doc.toJson(), Chair.class);
	}
	
	public void deleteChairsByManufacturer(String manufacturer)
	{
		 Bson filter = eq("manufacturer", manufacturer);
		 DeleteResult result = collection.deleteMany(filter);
		 
	}
	
	public void deleteChairsWithHeightOrHigher(float height)
	{
		Bson filter = gte("measurement.height", height);
		DeleteResult result = collection.deleteMany(filter);

	}
	
	public void deleteAllChairs()
	{
		collection.drop();
	}
}
