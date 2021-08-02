package org.aurd.user.controllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.request.SearchRequest;
import org.aurd.user.modal.response.SearchResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.compounds;

@Path("/searchCompound")
public class SearchController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public SearchResponse getCompound(SearchRequest searchRequest){
        System.out.println(searchRequest.getString());
        SearchResponse searchResponse = new SearchResponse();
        Document queryDoc1 = new Document();
        queryDoc1.append("compoundname",new Document().append("$regex",searchRequest.getString()).append("$options","i"));

       Document queryDoc2 = new Document();
       queryDoc2.append("address",new Document().append("$regex",searchRequest.getString()).append("$options","i"));

        Document searchDoc= new Document();
        ArrayList docArrayList= new ArrayList();
        docArrayList.add(queryDoc1);
        docArrayList.add(queryDoc2);
        searchDoc.append("$or",docArrayList);
//        queryDoc.append("$or",
//                new Document("compoundname",
//                        new Document().append("$regex",searchRequest.getString())).
//                        append("address",
//                                new Document().append("$regex",searchRequest.getString())))
//                .append("$options","i");

        System.out.println(searchDoc.toJson());

//        boolean exist = compounds.find(searchDoc).cursor().hasNext();
//        System.out.println(exist);

       MongoCursor cursor =  compounds.find(searchDoc).iterator();
        ArrayList<CompoundModal> arrayList = new ArrayList();
       if(cursor.hasNext()){
           while(cursor.hasNext()){
               Document cursorDoc = (Document) cursor.next();
               arrayList.add(new Gson().fromJson(cursorDoc.toJson(),CompoundModal.class));
           }
           searchResponse.setCode(Constants.SUCCESS_CODE);
           searchResponse.setStatus(Constants.STATUS_SUCCESS);
           searchResponse.setMessage(Constants.GET_COMPOUNDLIST_SUCCESS);
           searchResponse.setCompoundList(arrayList);
           return  searchResponse;

           }else{

           searchResponse.setStatus(Constants.STATUS_FAIL);
           searchResponse.setMessage(Constants.GET_COMPOUNDLIST_FAILED);
           searchResponse.setCode(Constants.FAILED_FETCH_COMPOUNDLIST);

           return searchResponse;
       }

    }

}
