package org.aurd.user.controllers;

import com.amazonaws.services.s3.model.S3Object;
import com.mongodb.client.gridfs.GridFSDownloadStream;

import org.aurd.user.constant.Constants;
import org.bson.types.ObjectId;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

import static org.aurd.MongoService.imageBucket;
import static org.aurd.MongoService.s3;


@Path("/image")
public class ImageController {

    @Path("/{id}")
    @GET
    @Produces({"image/png", "image/jpg", "image/gif"})
    public Response getImage(@PathParam("id") String imageId) throws IOException {
        System.out.println(imageId);
//        GridFSDownloadStream downloadStream = imageBucket.openDownloadStream(new ObjectId(imageId));
        final String bucketName = Constants.BUCKET_NAME;


        byte[] data = s3.getObject(bucketName,imageId).getObjectContent().readAllBytes();

        return Response.ok(data).build();
    }

    @Path("/compounds/{id}")
    @GET
    @Produces({"image/png", "image/jpg", "image/gif"})
    public Response getCompoundImage(@PathParam("id") String imageId) throws IOException {
        System.out.println(imageId);
//        GridFSDownloadStream downloadStream = imageBucket.openDownloadStream(new ObjectId(imageId));
        final String bucketName = Constants.BUCKET_NAME;


        byte[] data = s3.getObject(bucketName+"/compounds",imageId).getObjectContent().readAllBytes();

        return Response.ok(data).build();
    }



}