package ibf2022.batch2.paf.server.models;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Do not change this file
public class Comment {

	@NotNull
	private String restaurantId;
	
	@Size(min = 4, message = "Input must be longer than 3 characters")
	@NotNull
	private String name;
	
	
	private long date = 0l;
	
	@NotNull
	private String comment;
	
	@Min(value=1)
	@Max(value=5)
	@NotNull
	private int rating;

	public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }
	public String getRestaurantId() { return this.restaurantId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setComment(String comment) { this.comment = comment; }
	public String getComment() { return this.comment; }

	public void setDate(long date) { this.date = date; }
	public long getDate() { return this.date; }

	public void setRating(int rating) { this.rating = rating; }
	public int getRating() { return this.rating; }

	@Override
	public String toString() {
		return "Comment{restaurantId=%s, name=%s, date=%d, comment=%s, rating=%d"
				.formatted(restaurantId, name, date, comment, rating);
	}
}
