package org.aurd.Admin.adminControllers;

import com.mongodb.client.MongoCursor;

import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.aurd.MongoService.metaDataCollection;

@Path("/getMetaData")
public class GetMetaDataController {

    @GET
    public Response getMetaData()
    {
        MongoCursor cursor=metaDataCollection.find().limit(1).cursor();

        Response response=Response.status(Response.Status.OK).entity("No Data Found").build();

        if(cursor.hasNext())
        {
            Document doc=((Document) cursor.next());
            doc.remove("_id");
            response=Response.status(Response.Status.OK).
                    type(MediaType.APPLICATION_JSON).entity(doc.toJson()).build();
        }
        return response;
    }

}
