package org.aurd.Admin.adminControllers;

import org.aurd.Admin.adminModal.request.AddAboutUsRequest;
import org.aurd.Admin.adminModal.request.AddPrivacyPolicyRequest;
import org.aurd.Admin.adminModal.request.AddRefundPolicyRequest;
import org.aurd.Admin.adminModal.request.AddTermsAndConditionRequest;
import org.aurd.Admin.adminModal.response.GeneralResponse;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.metaDataCollection;


@Path("/admin/addMeta")
public class AddTermsAndConditionController {
     @Path("/terms")
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public GeneralResponse addTermsAndCondition(AddTermsAndConditionRequest request)
     {
         metaDataCollection.findOneAndUpdate(new Document(),new Document("$set",new Document("terms",request.getTerms())));
         GeneralResponse response=new GeneralResponse();
         response.setMessage("added");
         response.setErrorCode(0);
         response.setStatus(true);
         return response;
     }

     @Path("/privacyPolicy")
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public GeneralResponse addPrivacy(AddPrivacyPolicyRequest request)
     {
         metaDataCollection.findOneAndUpdate(new Document(),new Document("$set",new Document("privacyPolicy",request.getPrivacyPolicy())));
         GeneralResponse response=new GeneralResponse();
         response.setMessage("added");
         response.setErrorCode(0);
         response.setStatus(true);
         return response;
     }

     @Path("/aboutUs")
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public GeneralResponse addAboutUs(AddAboutUsRequest request)
     {
         metaDataCollection.findOneAndUpdate(new Document(),new Document("$set",new Document("aboutUs",request.getAboutUs())));
         GeneralResponse response=new GeneralResponse();
         response.setMessage("added");
         response.setErrorCode(0);
         response.setStatus(true);
         return response;
     }

     @Path("/refundPolicy")
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     public GeneralResponse addTermsAndCondition(AddRefundPolicyRequest request)
     {
         metaDataCollection.findOneAndUpdate(new Document(),new Document("$set",new Document("refundPolicy",request.getRefundPolicy())));
         GeneralResponse response=new GeneralResponse();
         response.setMessage("added");
         response.setErrorCode(0);
         response.setStatus(true);
         return response;
     }

}