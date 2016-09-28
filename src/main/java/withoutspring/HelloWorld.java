package withoutspring;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by chokst on 3/2/15.
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        displayAllDbs(mongoClient);
        DB db = getDbHandle(mongoClient);
        displayAllCollections(db);

        DBCollection collection = db.getCollection("mycollection");

        createDocument(collection);
        findDocument(collection);
        updateDocument(collection);
        findDocument(collection);
        dropDocument(collection);
        findDocument(collection);
    }
    private static void displayAllDbs(MongoClient mongoClient) {
        System.out.println("DBs:------");
        List<String> dbs = mongoClient.getDatabaseNames();
        for(String db : dbs){
            System.out.println(db);
        }
    }
    private static DB getDbHandle(MongoClient mongoClient) {
        //If MongoDB in secure mode, authentication is required.
        //MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("mydb");
        //boolean auth = db.authenticate("username", "password".toCharArray());
        return db;
    }
    private static void displayAllCollections(DB db) {
        System.out.println("Collections:-----");
        Set<String> tables = db.getCollectionNames();

        for(String coll : tables){
            System.out.println(coll);
        }
    }

    private static void createDocument(DBCollection dbCollection) {
        BasicDBObject document = new BasicDBObject();
        document.put("name", "mkyong");
        document.put("age", 30);
        document.put("createdDate", new Date());
        dbCollection.insert(document);
    }

    private static void findDocument(DBCollection dbCollection) {
        System.out.println("Found Documents:-----");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "mkyong-updated");

        DBCursor cursor = dbCollection.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void updateDocument(DBCollection dbCollection) {
        System.out.println("Updating Document:-----");
        BasicDBObject query = new BasicDBObject();
        query.put("name", "mkyong");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "mkyong-updated");

        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        dbCollection.update(query, updateObj);
    }



    private static void dropDocument(DBCollection dbCollection) {
        System.out.println("Droping Document:-----");
        BasicDBObject deleteQuery = new BasicDBObject();
        deleteQuery.put("name", "mkyong-updated");

        dbCollection.remove(deleteQuery);

    }
}
