package org.aurd.Admin.adminControllers;

import org.aurd.Admin.adminModal.response.GetDashBoardDataResponse;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.reviews;
import static org.aurd.MongoService.users;

@Path("/admin/getDashboardData")
public class GetDashboardDataController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetDashBoardDataResponse getDashBoardData (){
        int totalUsers = Math.toIntExact(users.countDocuments());
        int totalReviews = Math.toIntExact(reviews.countDocuments());
        int totalActiveUsers = Math.toIntExact(users.countDocuments());
        int newUsers = Math.toIntExact(users.countDocuments());
        int totalVisitors = Math.toIntExact(users.countDocuments());
        int weeklyVisitors = Math.toIntExact(users.countDocuments());
        int usersOnDesktop = Math.toIntExact(users.countDocuments());
        int usersOnMobile = Math.toIntExact(users.countDocuments());
        int usersOnIos = Math.toIntExact(users.countDocuments());
        GetDashBoardDataResponse getDashBoardDataResponse = new GetDashBoardDataResponse();
        getDashBoardDataResponse.setTotalUsers(totalUsers);
        getDashBoardDataResponse.setTotalReviews(totalReviews);
        getDashBoardDataResponse.setTotalVisitors(totalActiveUsers);
        getDashBoardDataResponse.setNewUsers(newUsers);
        getDashBoardDataResponse.setTotalVisitors(totalVisitors);
        getDashBoardDataResponse.setWeeklyVisitors(weeklyVisitors);
        getDashBoardDataResponse.setUsersOnDesktop(usersOnDesktop);
        getDashBoardDataResponse.setUsersOnMobile(usersOnMobile);
        getDashBoardDataResponse.setUsersOnIos(usersOnIos);
        return getDashBoardDataResponse;

    }

}
