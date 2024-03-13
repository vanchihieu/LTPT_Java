package dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import entities.Location;
import entities.Zip;



public class ZipDocmentMapper {
	public static Zip fromDocument(Document doc) {
        Zip zip = new Zip();
        zip.setId(doc.getObjectId("_id"));
        zip.setCity(doc.getString("city"));
        zip.setPop(doc.getInteger("pop"));
        zip.setState(doc.getString("state"));
        zip.setZip(doc.getString("zip"));

        Document loc = doc.get("loc", Document.class);
        Location location = new Location();
        location.setY(loc.getDouble("y"));
        location.setX(loc.getDouble("x"));
        zip.setLoc(location);

        return zip;
    }

    public static Document toDocument(Zip zip) {
        Document doc = new Document();
        doc.append("_id", zip.getId());
        doc.append("city", zip.getCity());
        doc.append("pop", zip.getPop());
        doc.append("state", zip.getState());
        doc.append("zip", zip.getZip());

        Document loc = new Document();
        loc.append("y", zip.getLoc().getY());
        loc.append("x", zip.getLoc().getX());
        doc.append("loc", loc);

        return doc;
    }

    public static List<Zip> fromDocuments(List<Document> docs) {
        List<Zip> list = new ArrayList<>();
        for (Document doc : docs) {
            list.add(fromDocument(doc));
        }
        
        return list;
    }
}
