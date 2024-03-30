package entities;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

// {
//     _id: ObjectId("5c8eccc1caa187d17ca6ed16"),
//     city: 'ALPINE',
//     zip: '35014',
//     loc: { y: 33.331165, x: 86.208934 },
//     pop: 3062,
//     state: 'AL'
//   }
public class Zip {
	@BsonId
    private ObjectId id;
    private String city;
    private String zip;
    private Location loc;
    private int pop;
    private String state;

    public Zip() {
    }

    public Zip(ObjectId id, String city, String zip, Location loc, int pop, String state) {
        this.id = id;
        this.city = city;
        this.zip = zip;
        this.loc = loc;
        this.pop = pop;
        this.state = state;
    }
    

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Zip [city=" + city + ", id=" + id + ", loc=" + loc + ", pop=" + pop + ", state=" + state + ", zip=" + zip
                + "]";
    }

}
