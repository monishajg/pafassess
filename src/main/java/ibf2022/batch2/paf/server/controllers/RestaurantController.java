package ibf2022.batch2.paf.server.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.server.services.RestaurantService;
import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class RestaurantController {

	@Autowired
	private RestaurantService restSvc;
	
	// TODO: Task 2 - request handler
	@GetMapping(path = "/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getCuisines() {
        log.info(">>> Requesting list of cuisines...");
        return ResponseEntity.ok().body(restSvc.getCuisines());
        }
    
    // TODO: Task 3 - request handler
	@GetMapping(path = "/restaurants/{cuisine}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getRestaurants(@PathVariable String cuisine) {
		log.info(">>> Requesting list of restaurants...");
		return ResponseEntity.ok().body(restSvc.getRestaurantsByCuisine(cuisine));
		}

	// TODO: Task 4 - request handler
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String restaurantId) {
		Restaurant restaurant = restSvc.getRestaurantById(restaurantId);
		System.out.println(restaurant);

		if (restaurant == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        }
	}

	// TODO: Task 5 - request handler
	@PostMapping(path = "/restaurant/comment")
	public void postComment(@RequestBody MultiValueMap<String, String> form) {
		Date d = new java.util.Date();
		long epoch = d.getTime();
		Comment c = new Comment();
		c.setName(form.getFirst("name"));
		c.setRating(Integer.parseInt(form.getFirst("rating")));
		c.setComment(form.getFirst("comment"));
		c.setRestaurantId(form.getFirst("restaurantId"));
		c.setDate(epoch);

		restSvc.postRestaurantComment(c);
	} 
}