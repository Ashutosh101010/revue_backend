//package org.aurd.Admin.adminControllers;
//
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Path("/addCompound")
//public class AddCompoundController {
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//
//    public AddCompoundResponse addCompound(@FormDataParam("imageData") List<FormDataBodyPart> list,
//                                           @FormDataParam("name") String compoundName,
//                                           @FormDataParam("address") String address,
//                                           @FormDataParam("description") String description,
//                                           @FormDataParam("amenities") String amenities,
//                                           @FormDataParam("type") String type,
//                                           @FormDataParam("location") String position) throws IOException {
//        CompoundModal compoundModal = new CompoundModal();
//
//        System.out.println(amenities);
//        System.out.println(compoundName);
//        System.out.println(description);
////        ArrayList<String> imagesList=new ArrayList();
////        System.out.println(list.size());
////
////        list.forEach(bodyPart -> {
////            GridFSUploadStream uploadStream = imageBucket.openUploadStream(new ObjectId().toHexString());
////            InputStream inputStream = bodyPart.getEntityAs(InputStream.class);
////            try {
////                uploadStream.write(inputStream.readAllBytes());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            String ImageId=uploadStream.getObjectId().toHexString();
////            imagesList.add(ImageId);
////            uploadStream.close();
////        });
//
////        JSONArray jsonArray= new JSONArray(amenities);
////        ArrayList amenityList = new ArrayList();
////        for(int i=0;i<jsonArray.length();i++){
////            amenityList.add(jsonArray.get(i));
////        }
//
////        compoundModal.setImages(imagesList);
//
//        ArrayList<Double> positionList = new ArrayList();
////        JSONArray positionArray = new JSONArray(position);
////        for(int i=0;i<positionArray.length();i++){
////            positionList.add(positionArray.getDouble(i));
////        }
//        Position positionModal = new Position();
//        positionModal.setCoordinates(positionList);
//        positionModal.setType("Point");
//
//        compoundModal.setCompoundname(compoundName);
//        compoundModal.setAddress(address);
//        compoundModal.setDescription(description);
////        compoundModal.setAmenities(amenityList);
//        compoundModal.setCategory(type);
//        compoundModal.setPosition(positionModal);
//
//        AddCompoundResponse addCompoundResponse = CompoundModal.addCompoundToDB();
//        return  addCompoundResponse;
//    }
//}
