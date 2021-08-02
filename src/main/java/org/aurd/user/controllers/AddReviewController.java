package org.aurd.user.controllers;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.gridfs.GridFSUploadStream;
import org.aurd.user.modal.entity.ReviewModal;
import org.aurd.user.modal.request.AddReviewRequest;
import org.aurd.user.modal.response.AddReviewResponse;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONArray;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.aurd.MongoService.imageBucket;
import static org.aurd.MongoService.s3;

@Path("/addReview")
public class AddReviewController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)



    public AddReviewResponse addReview(@MultipartForm MultipartFormDataInput inputRequest ) {

try {


    System.out.println(inputRequest.getFormDataMap());

    Map<String, List<InputPart>> input = inputRequest.getFormDataMap();
    AddReviewRequest request = new AddReviewRequest();
    request.setReviewername(input.get("reviewername").get(0).getBodyAsString());
    request.setUserId(input.get("userId").get(0).getBodyAsString());
    request.setRent(input.get("rent").get(0).getBodyAsString());
    request.setPros(input.get("pros").get(0).getBodyAsString());
    request.setCons(input.get("cons").get(0).getBodyAsString());
    request.setFloorplan(input.get("floorplan").get(0).getBodyAsString());
    request.setReview(input.get("review").get(0).getBodyAsString());
    request.setCompoundID(input.get("compoundID").get(0).getBodyAsString());
    request.setCompoundID(input.get("compoundID").get(0).getBodyAsString());
    request.setFacility(input.get("facility").get(0).getBodyAsString());
    request.setValue(input.get("value").get(0).getBodyAsString());
    request.setLocation(input.get("location").get(0).getBodyAsString());
    request.setManagement(input.get("management").get(0).getBodyAsString());
    request.setDesign(input.get("design").get(0).getBodyAsString());
    request.setRating(input.get("rating").get(0).getBodyAsString());
    request.setTimestamp(input.get("timestamp").get(0).getBodyAsString());
    request.setCompoundName(input.get("compoundName").get(0).getBodyAsString());
    request.setBedRooms(input.get("bedRooms").get(0).getBodyAsString());
    request.setBathRooms(input.get("bathRooms").get(0).getBodyAsString());


    List<InputPart> list=input.get("imageData");








    // Use the following code if you would like to add your access and secret keys in the code.

    // Use the following code to fetch the aws profile credentials.
//        AWSCredentialsProvider credentials = new
//
//                ProfileCredentialsProvider("wasabi");

    // connect to Wasabi s3


    // General bucket information
    final String bucketName = "revue";


    // Upload object to bucket


    ReviewModal reviewModal = new ReviewModal();
    ArrayList<String> imagesList = new ArrayList();
    System.out.println(list.size());

    if (list.isEmpty()) {
        reviewModal.setImages(imagesList);
    } else {
       list.forEach(bodyPart -> {
//                GridFSUploadStream uploadStream = imageBucket.openUploadStream(request.getUserId()+
//                        System.currentTimeMillis());
            InputPart inputPart = ((InputPart) bodyPart);
            InputStream inputStream = null;
            try {
                byte aByte[] =inputPart.getBody(InputStream.class,null).readAllBytes();
                inputStream=new ByteArrayInputStream(aByte);
//                uploadStream.write(aByte);
//               uploadStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String ImageId = String.valueOf(System.currentTimeMillis());
            s3.putObject(bucketName, ImageId, inputStream, new ObjectMetadata());


            imagesList.add(ImageId);
            System.out.println(ImageId);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//                uploadStream.close();
        });
       System.out.println(imagesList);
        reviewModal.setImages(imagesList);
    }


    JSONArray prosArray = new JSONArray(request.getPros());
    ArrayList pList = new ArrayList();
    for (int i = 0; i < prosArray.length(); i++) {
        pList.add(prosArray.get(i));
    }
    System.out.println(prosArray);

    JSONArray consArray = new JSONArray(request.getCons());
    ArrayList cList = new ArrayList();
    for (int i = 0; i < consArray.length(); i++) {
        cList.add(consArray.get(i));
    }
    System.out.println(consArray);

    reviewModal.setPros(pList);
    reviewModal.setCons(cList);
    reviewModal.setReview(request.getReview());
    reviewModal.setReviewerName(request.getReviewername());
    reviewModal.setCompoundID(request.getCompoundID());
    reviewModal.setFloorplan(request.getFloorplan());
    reviewModal.setPrice(request.getRent());
    reviewModal.setFacility(Double.parseDouble(request.getFacility()));
    reviewModal.setDesign(Double.parseDouble(request.getDesign()));
    reviewModal.setManagement(Double.parseDouble(request.getManagement()));
    reviewModal.setLocation(Double.parseDouble(request.getLocation()));
    reviewModal.setValue(Double.parseDouble(request.getValue()));
    reviewModal.setRating(Double.parseDouble(request.getRating()));
    reviewModal.setTimestamp(Long.parseLong(request.getTimestamp()));
    reviewModal.setUserID(request.getUserId());
    reviewModal.setCompoundName(request.getCompoundName());
    reviewModal.setBedRooms(Integer.parseInt(request.getBedRooms()));
    reviewModal.setBathRooms(Integer.parseInt(request.getBathRooms()));


    AddReviewResponse addReviewResponse = reviewModal.addReviewtoDB();

    return addReviewResponse;
}catch (Exception e)
{
    e.printStackTrace();
    return null;
}
    }

}
