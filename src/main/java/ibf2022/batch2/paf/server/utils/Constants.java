package ibf2022.batch2.paf.server.utils;

public interface Constants {
    public static final String MONGO_DATABASE_NAME = "restaurants";
    public static final String COLLECTION_DINER = "diner";

    // Restaurant
    public static final String FIELD_RESTAURANTID  = "restaurantId";
    public static final String FIELD_NAME  = "name";
    public static final String FIELD_ADDRESS  = "address";
    public static final String FIELD_CUISINE  = "cuisine";
    public static final String FIELD_COMMENTS  = "comments";
    
    // Comment
    public static final String FIELD_COMMENT_RESTAURANTID = "restaurantId";
    public static final String FIELD_COMMENT_NAME = "name";
    public static final String FIELD_COMMENT_DATE = "0l";
    public static final String FIELD_COMMENT_COMMENT = "comment";
    public static final String FIELD_COMMENT_RATING = "rating";

}
