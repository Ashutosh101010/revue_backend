package org.aurd.user.controllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.request.GetCompoundDetailsRequest;
import org.aurd.user.modal.response.GetCompoundDetailResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.reviews;


@Path("/getCompoundDetails")
public class GetCompoundDetailsController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public GetCompoundDetailResponse compoundDetails(GetCompoundDetailsRequest compoundDetailsRequest){
      System.out.println(compoundDetailsRequest.getId());
        MongoCursor cursor =  compounds.find(new Document("_id",new ObjectId(compoundDetailsRequest.getId()))).cursor();
        GetCompoundDetailResponse getCompoundDetailResponse = new GetCompoundDetailResponse();
        if(cursor.hasNext()){
            Document doc = (Document) cursor.next();

            getCompoundDetailResponse.setStatus(Constants.STATUS_SUCCESS);
            getCompoundDetailResponse.setErrorCode(Constants.SUCCESS_CODE);


           AtomicReference<Double> excellentCount= new AtomicReference<>((double) 0);
           AtomicReference<Double> vgoodCount= new AtomicReference<>((double) 0);
           AtomicReference<Double> averageCount= new AtomicReference<>((double) 0);
           AtomicReference<Double> poorCount= new AtomicReference<>((double) 0);
           AtomicReference<Double> verypoortCount= new AtomicReference<>((double) 0);


            double excellent=0;
            double veryGood=0;
            double average=0;
            double poor=0;
            double veryPoor=0;
            AtomicInteger count= new AtomicInteger();
           MongoCursor cur= reviews.find(new Document("compoundID",doc.get("_id").toString())).cursor();
           cur.forEachRemaining(o -> {
               count.addAndGet(1);
               Document document=((Document) o);
               double rating=document.getDouble("rating");
               if(rating>4&&rating<=5)
               {
               excellentCount.updateAndGet(v -> (double) (v + 1));

               }
              else if(rating>3&&rating<=4)
               {
                  vgoodCount.updateAndGet(v -> (double) (v + 1));
               }
             else  if(rating>2&&rating<=3)
               {
                averageCount.updateAndGet(v -> (double) (v + 1));
               }
             else  if(rating>1&&rating<=2)
               {
                   poorCount.updateAndGet(v -> (double) (v + 1));
               } else  if(rating>0&&rating<=1)
               {
                   verypoortCount.updateAndGet(v -> (double) (v + 1));
               }
           });


            CompoundModal compoundModal = new Gson().fromJson(doc.toJson(),CompoundModal.class);
            compoundModal.setReviewCount(count.get());
            if(excellentCount.get()>0)
            {
                excellent=excellentCount.get()/count.get();
            }
            if(vgoodCount.get()>0)
            {
                veryGood=vgoodCount.get()/count.get();
            }
            if(averageCount.get()>0)
            {
                average=averageCount.get()/count.get();
            }
 if(poorCount.get()>0)
            {
                poor=poorCount.get()/count.get();
            }
 if(verypoortCount.get()>0)
            {
                veryPoor=verypoortCount.get()/count.get();
            }
 compoundModal.setExcellent(excellent);
 compoundModal.setVeryGood(veryGood);
 compoundModal.setAverage(average);
 compoundModal.setPoor(poor);
 compoundModal.setVeryPoor(veryPoor);


            getCompoundDetailResponse.setCompoundModal(compoundModal);
            return getCompoundDetailResponse;
        }else{
            getCompoundDetailResponse.setStatus(Constants.STATUS_FAIL);
            getCompoundDetailResponse.setErrorCode(Constants.ERROR_CODE);
            return getCompoundDetailResponse;
        }
    }


}
