package org.aurd.user.constant;

public class Constants {

    public static  int SUCCESS_LOGIN_CODE = 1;
    public static  int  SUCCESS_REGISTER_CODE = 1;
    public static int SUCCESS_FETCH_COMPOUNDLIST = 1;
    public static int FAILED_FETCH_COMPOUNDLIST = 2;
    public static  int  ERROR_CODE =0;
    public static int SUCCESS_CODE= 1;

    public static boolean STATUS_SUCCESS=true;
    public static boolean STATUS_FAIL=false;

  public  static String REGISTER_FAILED_ALREADY_EXIST ="User Already Exist";
    public static String LOGIN_FAILED_DESCRIPTION_USER_NOT_FOUND ="User Not Found";
    public static String LOGIN_FAILED_DESCRIPTION_PASSWORD_UNMATCHED = "Password Not Match";

    public static String MESSAGE_REGISTRATION_SUCCESS="Registration Successful";
    public static String MESSAGE_REGISTRATION_FAILED="Registration Failed";

    public static String MESSAGE_LOGIN_SUCCESS="Login Successful";
    public static String MESSAGE_LOGIN_FAILED="Login Failed";

    public  static  String GET_COMPOUNDLIST_SUCCESS = "Successfully Fetch Compoundlist";
    public  static  String GET_COMPOUNDLIST_FAILED = "Unable To Fetch Compounds";

    public static String FETCH_ALLREVIEW_SUCCESS = "Successfully fetch reviews";

    public static String ADD_COMPOUND_SUCCESS= "Compound Added Successfully";
    public static String ADD_COMPOUND_FAILED= "Compound Is Not Added";

    public static String ADD_REVIEW_SUCCESS = "Review Added Successfully";
    public static String ADD_REVIEW_FAILURE = "Unable to add Review, Something went wrong";

    public static String ASK_QUESTION_SUCCESS = "Question is added successfully";
    public  static String ANSWER_QUESTION_SUCCESS = "Answer to the question successfully";

    public static String FETCH_ALL_QUESTION_SUCCESS = "Fetch all questions successfully";
    public static String FETCH_ALL_QUESTION_FAILED = "Unable to Fetch Questions";

    public static String LIKE_DISLIKE_SUCCESS = "Like Dislike Success";
    public static String LIKE_DISLIKE_FAILED = "Like Dislike failure";
    public static String INVALID_OPERATION = "Invalid Operations";
    public static int ERROR_CODE_LIKE_DISLIKE= 1;

    public static String FETCH_MY_REVIEW_SUCCESS = "Fetch User Review Successfully";
    public static String FETCH_MY_REVIEW_FAILURE = "Fetch User Review Failed";

    public static  String REPORT_ANSWER_SUCCESS = "Answer is Reported Successhfully";
    public static  String GET_ALL_ANSWERS_SUCCESS = "Fetch Answers Successfully";

    public static String REPORT_REVIEW_SUCCESS = "Review Reported Successfully";
    public static  String ADD_TO_FAVORITE = "Add to Favorite";
    public static String REMOVE_FROM_FAVORITE_SUCCESS =    "Compound Remove successfully";
  public static String REMOVE_FROM_FAVORITE_FAILURE =    "Failed to remove compound";

  public static String FETCH_FAVORITES_SUCCESS = "Get All Favorite Compound Successfully";

    public static String FETCH_FAVORITES_FAILURE = "Get All Favorite Compound Failed";

    public static String GMAIL_USER_NAME="revueappqa";
    public static String GMAIL_PASSWORD="R3vu3app2020";

    public static String GMAIL_SUBJECT="AUTHENTICATION";



    public static  int DOCUMENT_NUMBER_PAGE=10;

    public static int RECOMMENDED_COMPOUNDS_RADIUS=25;
    public static double RECOMMENDED_COMPOUNDS_DEFAULT_LATITUDE=25.3548;
    public static double RECOMMENDED_COMPOUNDS_DEFAULT_LONGITUDE=25;


}
