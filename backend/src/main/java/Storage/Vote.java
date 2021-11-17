package Storage;

import com.mongodb.BasicDBObject;

public class Vote {
    public String pin;
    public String vote;

    public BasicDBObject asDBObject(){
        return new BasicDBObject()
                .append("pin", this.pin)
                .append("vote", this.vote);
    }
}
