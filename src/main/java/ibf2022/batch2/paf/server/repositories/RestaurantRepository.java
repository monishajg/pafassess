package ibf2022.batch2.paf.server.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.StringOperators.ReplaceAll;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import ibf2022.batch2.paf.server.utils.Constants;
import ibf2022.batch2.paf.server.utils.Utils;

@Repository
public class RestaurantRepository implements Constants {

	@Autowired
    private MongoTemplate template;
	
	// TODO: Task 2 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.diner.distinct("cuisine").sort()
	public List<String> getCuisines() {
		List<String> cuisines = template.findDistinct(new Query(), FIELD_CUISINE, COLLECTION_DINER, String.class);
		Collections.sort(cuisines);
		return cuisines;
	}	

	// TODO: Task 3 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.diner.find({ cuisine: "Afghan" })
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		Criteria criteria = Criteria.where("cuisine").regex(cuisine,"i"); 
		Query query = Query.query(criteria); 
	 
		List<Document> resultList = template.find(query, Document.class, COLLECTION_DINER); 
		List<Restaurant> finalList = new ArrayList<>(); 
	 
		for(Document d:resultList){ 
		 Restaurant r = new Restaurant(); 
		 r.setRestaurantId(d.getString("restaurant_id")); 
		 r.setName(d.getString("name")); 
		 finalList.add(r); 
		} 
		// System.out.println(">>>>>>> finalList: "+ finalList); 
		return finalList;
	}
	// public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
    //     Query query = new Query();
    //     query = query.addCriteria(Criteria.where("cuisine").is(cuisine));
    //     query.with(Sort.by(Sort.Direction.ASC, "cuisine"));
    //     List<Restaurant> restaurants = template.find(query, Restaurant.class, COLLECTION_DINER);
    //     return restaurants;
    // }
	
	// TODO: Task 4 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// 
	public Restaurant getRestaurantById(String id) {
		Criteria criteria = Criteria.where("restaurant_id").is(id);
		Query query = Query.query(criteria);
		Restaurant restaurant = template.findOne(query, Restaurant.class, COLLECTION_DINER);
		restaurant.setRestaurantId(id);
		System.out.println(restaurant + "restaurant by id");
		return restaurant;
	}

	// TODO: Task 5 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// 
	public void insertRestaurantComment(Comment comment) {
        Criteria criteria = Criteria.where("restaurant_id").is(comment.getRestaurantId());
        Query query = new Query(criteria);
        
        Update updateOps = new Update().push("comments").each(comment);
        UpdateResult updateResult = template.updateFirst(query, updateOps, COLLECTION_DINER);
        if (updateResult == null)
            System.out.println("not updated");
        else
        System.out.println(updateResult.getModifiedCount()+"document(s) updated..");
    }
}