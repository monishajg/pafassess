package ibf2022.batch2.paf.server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.server.repositories.RestaurantRepository;
import ibf2022.batch2.paf.server.services.RestaurantService;
import ibf2022.batch2.paf.server.models.Restaurant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class RestaurantController {

	@Autowired
	private RestaurantService restSvc;
	
	// TODO: Task 2 - request handler
	@GetMapping(path = "/cuisines")
	public ResponseEntity<List<String>> getCuisines() {
        log.info(">>> Requesting list of cuisines...");
        return ResponseEntity.ok().body(restSvc.getCuisines());
        }
    
    // TODO: Task 3 - request handler
	@GetMapping(path = "/restaurants/{cuisine}")
	public ResponseEntity<List<Restaurant>> getRestaurants(@PathVariable String cuisine) {
		log.info(">>> Requesting list of restaurants...");
		return ResponseEntity.ok().body(restSvc.getRestaurantsByCuisine(cuisine));
		}

	// TODO: Task 4 - request handler
	// @GetMapping(path = "/restaurant/{restaurantId}")
	public ResponseEntity<Optional<Restaurant>> getRestaurant(String restaurantId) {
	return null;
	}


	// TODO: Task 5 - request handler
	// @PostMapping(path = "/restaurant/comment")
	// public 
	
	
	
	// public ResponseEntity<String> insertGameReview(@Valid Review review, BindingResult result,
    //         HttpServletResponse response, Model model) {

    //     if (!(gameRepo.checkGameId(review.getGid()))) { // checkGameId from Gamerepo
    //         FieldError fieldErr = new FieldError("Review", FIELD_GAME_ID, "Game Id does not exists");
    //         result.addError(fieldErr);
    //     }

    //     if (result.hasErrors()) {
    //         List<FieldError> listOfErr = result.getFieldErrors();
    //         JsonObjectBuilder job = Json.createObjectBuilder();
    //         listOfErr.stream()
    //                 .forEach(x -> {
    //                     job.add(x.getField(), x.getDefaultMessage());
    //                 });

    //         return ResponseEntity.badRequest().body(job.build().toString());
    //     }

    //     Optional<Document> opt = gameService.getGameName(review.getGid());

    //     review.setPosted(new Date());
    //     String name = opt.get().getString("name");
    //     review.setName(name);

    //     Document doc = gameService.insertComment(review); // from GameService l57
    //     doc.replace("posted", doc.getDate("posted").toString());
    //     doc.remove("_id");
    //     // doc.replace("_id", doc.getObjectId("_id").toString());

    //     return ResponseEntity
    //             .ok()
    //             .body(doc.toJson());

}