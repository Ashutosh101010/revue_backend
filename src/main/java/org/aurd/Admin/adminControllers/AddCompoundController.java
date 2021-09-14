package org.aurd.Admin.adminControllers;




import org.aurd.Admin.adminModal.request.AddCompoundRequest;
import org.aurd.Admin.adminModal.request.AddCompoundResponse;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.entity.Position;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/addCompound")
public class AddCompoundController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public AddCompoundResponse addCompound(@MultipartForm AddCompoundRequest addCompoundRequest) throws IOException {
        CompoundModal compoundModal = new CompoundModal();

//        ArrayList<String> imagesList=new ArrayList();
//        System.out.println(list.size());
//
//        list.forEach(bodyPart -> {
//            GridFSUploadStream uploadStream = imageBucket.openUploadStream(new ObjectId().toHexString());
//            InputStream inputStream = bodyPart.getEntityAs(InputStream.class);
//            try {
//                uploadStream.write(inputStream.readAllBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String ImageId=uploadStream.getObjectId().toHexString();
//            imagesList.add(ImageId);
//            uploadStream.close();
//        });

//        JSONArray jsonArray= new JSONArray(amenities);
//        ArrayList amenityList = new ArrayList();
//        for(int i=0;i<jsonArray.length();i++){
//            amenityList.add(jsonArray.get(i));
//        }

//        compoundModal.setImages(imagesList);

        ArrayList<Double> positionList = new ArrayList();
//        JSONArray positionArray = new JSONArray(position);
//        for(int i=0;i<positionArray.length();i++){
//            positionList.add(positionArray.getDouble(i));
//        }
        Position positionModal = new Position();
        positionModal.setCoordinates(positionList);
        positionModal.setType("Point");

        compoundModal.setCompoundname(addCompoundRequest.getCompoundname());
        compoundModal.setAddress(addCompoundRequest.getAddress());
        compoundModal.setDescription(addCompoundRequest.getDescription());
//        compoundModal.setAmenities(amenityList);
//        compoundModal.setCategory(addCompoundRequest.getType());
        compoundModal.setPosition(positionModal);

        AddCompoundResponse addCompoundResponse = CompoundModal.addCompoundToDB();
        return  addCompoundResponse;
    }
}
