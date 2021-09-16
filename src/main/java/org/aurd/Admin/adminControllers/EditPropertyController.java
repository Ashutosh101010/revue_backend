package org.aurd.Admin.adminControllers;


import org.aurd.Admin.adminModal.request.EditPropertyRequest;
import org.aurd.Admin.adminModal.response.EditPropertyResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.compounds;

@Path("/admin/editProperty")
public class EditPropertyController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EditPropertyResponse editPropertyResponse(EditPropertyRequest editPropertyRequest){
        EditPropertyResponse editPropertyResponse = new EditPropertyResponse();
        Document doc = new Document();
        if(editPropertyRequest.getCompoundName()!=null){
            doc.append("compoundname", editPropertyRequest.getCompoundName());
        }
        if(editPropertyRequest.getAddress()!=null) {
            doc.append("address", editPropertyRequest.getAddress());
        }
        if(editPropertyRequest.getDescription()!=null) {
            doc.append("description", editPropertyRequest.getDescription());
        }
        if(editPropertyRequest.getCategory()!=null) {
            doc.append("category", editPropertyRequest.getCategory());
        }
        if(editPropertyRequest.getAmenities()!=null) {
            doc.append("amenities", editPropertyRequest.getAmenities());
        }
        System.out.println("this is ");
        System.out.println(editPropertyRequest.getId());
        Document findDoc = new Document("_id", new ObjectId(editPropertyRequest.getId()));
        compounds.findOneAndUpdate(findDoc,new Document("$set",doc));
        editPropertyResponse.setStatus(true);
        editPropertyResponse.setErrorCode(0);
        editPropertyResponse.setMessage("Property Updated");
        return editPropertyResponse;
    }


}
