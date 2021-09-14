package org.aurd.user.controllers;


import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.gson.Gson;
import org.aurd.Admin.adminModal.request.AddCompoundRequest;
import org.aurd.Admin.adminModal.request.AddCompoundResponse;
import org.aurd.Admin.adminModal.response.AddPropertyResponse;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.entity.ReviewModal;
import org.aurd.user.modal.response.AddReviewResponse;
import org.bson.Document;
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
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.s3;

@Path("/addProperty")
public class AddPropertyController {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public AddPropertyResponse addCompoundResponse(@MultipartForm MultipartFormDataInput inputRequest){

        AddPropertyResponse addPropertyResponse = null;
        try{
            Map<String, List<InputPart>> input = inputRequest.getFormDataMap();
            AddCompoundRequest compoundRequest = new AddCompoundRequest();
            compoundRequest.setCompoundname(input.get("compoundName").get(0).getBodyAsString());
            compoundRequest.setAddress(input.get("address").get(0).getBodyAsString());
            compoundRequest.setDescription(input.get("description").get(0).getBodyAsString());
            compoundRequest.setCategory(input.get("category").get(0).getBodyAsString());
            compoundRequest.setFacility(0);
            compoundRequest.setLocation(0);
            compoundRequest.setManagement(0);
            compoundRequest.setValue(0);
            compoundRequest.setDesign(0);
            compoundRequest.setRating(0);
//            compoundRequest.setPosition(in);

            List<InputPart> list=input.get("imageData");






            final String bucketName = "revue";

            CompoundModal compoundModal = new CompoundModal();
            ArrayList<String> imagesList = new ArrayList();
            System.out.println(list.size());

            if (list.isEmpty()) {
                compoundModal.setImages(imagesList);
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
                compoundModal.setImages(imagesList);
            }

            JSONArray amenitiesArray = new JSONArray(compoundRequest.getAmenities());
            ArrayList pList = new ArrayList();
            for (int i = 0; i < amenitiesArray.length(); i++) {
                pList.add(amenitiesArray.get(i));
            }

            compoundModal.setCompoundname(compoundRequest.getCompoundname());
            compoundModal.setAddress(compoundRequest.getAddress());

            Document document = Document.parse(new Gson().toJson(compoundModal));
            compounds.insertOne(document);

             addPropertyResponse = new AddPropertyResponse();
            addPropertyResponse.setErrorcode(Constants.ERROR_CODE);
            addPropertyResponse.setMessage("Property Added Successfully");
            addPropertyResponse.setStatus(Constants.STATUS_SUCCESS);


        }
        catch (Exception error){
            System.out.println(error);
        }
        return  addPropertyResponse;
    }
}
