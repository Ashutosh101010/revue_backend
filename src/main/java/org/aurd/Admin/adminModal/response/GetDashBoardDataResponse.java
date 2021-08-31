package org.aurd.Admin.adminModal.response;

public class GetDashBoardDataResponse {
    int totalUsers;
    int totalReviews;
    int totalActiveUsers;
    int newUsers;
    int totalVisitors;
    int weeklyVisitors;
    int usersOnDesktop;
    int usersOnMobile;
    int usersOnIos;

    public int getTotalUsers() {
        return totalUsers;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public int getTotalActiveUsers() {
        return totalActiveUsers;
    }

    public int getNewUsers() {
        return newUsers;
    }

    public int getTotalVisitors() {
        return totalVisitors;
    }

    public int getWeeklyVisitors() {
        return weeklyVisitors;
    }

    public int getUsersOnDesktop() {
        return usersOnDesktop;
    }

    public int getUsersOnMobile() {
        return usersOnMobile;
    }

    public int getUsersOnIos() {
        return usersOnIos;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public void setTotalActiveUsers(int totalActiveUsers) {
        this.totalActiveUsers = totalActiveUsers;
    }

    public void setNewUsers(int newUsers) {
        this.newUsers = newUsers;
    }

    public void setTotalVisitors(int totalVisitors) {
        this.totalVisitors = totalVisitors;
    }

    public void setWeeklyVisitors(int weeklyVisitors) {
        this.weeklyVisitors = weeklyVisitors;
    }

    public void setUsersOnDesktop(int usersOnDesktop) {
        this.usersOnDesktop = usersOnDesktop;
    }

    public void setUsersOnMobile(int usersOnMobile) {
        this.usersOnMobile = usersOnMobile;
    }

    public void setUsersOnIos(int usersOnIos) {
        this.usersOnIos = usersOnIos;
    }
}
