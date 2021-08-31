package org.aurd;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.bson.Document;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;


public class MongoService {
    @Inject
    MongoClient mongoClient;

    public static MongoDatabase mongoDatabase;
    public static MongoCollection users;
    public static  MongoCollection compounds;
    public  static MongoCollection reviews;
    public static GridFSBucket imageBucket;
    public static  MongoCollection questionCollection;
    public static MongoCollection answerCollection;
    public static  MongoCollection reportCollection;
    public static  MongoCollection likeUnlikeCollection;
    public static MongoCollection favCollection;
    public static MongoCollection authCollection;
    public static MongoCollection adminCollection;
    public static AmazonS3 s3;





    public void onStart(@Observes StartupEvent event){
        System.out.println("start");

//        mongoClient = new MongoClient(new MongoClientURI("mongodb://ashutosh:Ashutosh96@94.237.121.70:27017/test?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        mongoDatabase = mongoClient.getDatabase("RevueStage");
        users = mongoDatabase.getCollection("Users");
        compounds = mongoDatabase.getCollection("Compounds");
        compounds.createIndex(new Document("position","2dsphere"));
        reviews = mongoDatabase.getCollection("Reviews");
        imageBucket= GridFSBuckets.create(mongoDatabase,"images");
        questionCollection =mongoDatabase.getCollection("Question");
        answerCollection = mongoDatabase.getCollection("Answers");
        reportCollection = mongoDatabase.getCollection("Report");
        likeUnlikeCollection = mongoDatabase.getCollection("Like");
        favCollection = mongoDatabase.getCollection("Favorite");
        authCollection=mongoDatabase.getCollection("AuthCollection");
        adminCollection=mongoDatabase.getCollection("Admin");

        authCollection.createIndex(Indexes.ascending("date"),new IndexOptions().expireAfter(3L, TimeUnit.MINUTES));

        final String s3Endpoint = "https://s3.wasabisys.com";
        final String region = "us-east-1";

        AWSCredentialsProvider credentials =
                new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("RO3PDA6CRJSQC4JH65QH", "dtB4aV9tharNtWaW2eaZMK08zCzqHlleBMvlmRof"));

         s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new

                        AwsClientBuilder.EndpointConfiguration(s3Endpoint, region))
                .withCredentials(credentials)
                .build();
    }


    public void onFinish(@Observes ShutdownEvent event){
        mongoClient.close();
    }

}
