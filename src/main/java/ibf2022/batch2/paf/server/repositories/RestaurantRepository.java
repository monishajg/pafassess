package ibf2022.batch2.paf.server.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.StringOperators.ReplaceAll;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import ibf2022.batch2.paf.server.utils.Constants;

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
        Query query = new Query();
        query = query.addCriteria(Criteria.where("cuisine").is(cuisine));
        query.with(Sort.by(Sort.Direction.ASC, "cuisine"));
        List<Restaurant> restaurants = template.find(query, Restaurant.class, COLLECTION_DINER);
        return restaurants;
    }
	
	// TODO: Task 4 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// 
	public Optional<Restaurant> getRestaurantById(String id) {
        Query query = new Query();
        query = query.addCriteria(Criteria.where("_id").is(id));
        Restaurant restaurant = template.findOne(query, Restaurant.class, COLLECTION_DINER);
        return Optional.ofNullable(restaurant);
    }

	// TODO: Task 5 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	//
	public void insertRestaurantComment(Comment comment) {
        template.insert(comment);
    }
}
