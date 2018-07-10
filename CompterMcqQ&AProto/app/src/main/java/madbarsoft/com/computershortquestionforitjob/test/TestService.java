package madbarsoft.com.computershortquestionforitjob.test;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.HashMap;

public class TestService {

    // Shared Preferences reference
    SharedPreferences userPref;
    // Edi5tor ref for Shared Preferences
    SharedPreferences.Editor editor;
    Context context;
    int categoryId;
    int PRIVATE_MODE = 0;

    // shared file name
    private static final String CATEGORY_ID_10="shPrefFileComputerHistoryCategoryId_10";
    private static final String CATEGORY_ID_11="shPrefFileComputerHardwareCategoryId_11";

    // user email (make variable public to access from outside)
    public static final String CATEGORY_ID = "categoryId";
    public static final String TAKEN_QUESTION = "takenQuestion";
    public static final String CORRECT_ANS = "correctAns";
    public static final String DATE_OF_TEST = "testDate";

    //constructor
    public TestService(Context context, int categoryId){
        this.context = context;
        this.categoryId = categoryId;
    }


    public void initSharedPrefFile(){
        if(categoryId==10){
            userPref = context.getSharedPreferences(CATEGORY_ID_10, PRIVATE_MODE);
        }else{
            userPref = context.getSharedPreferences(CATEGORY_ID_11, PRIVATE_MODE);
        }
        editor = userPref.edit();
    }

    // Get Stored Session User data
    public HashMap<String, String> getUserDetails(){
        initSharedPrefFile();
        HashMap<String, String> userData = new HashMap<>();
        userData.put(CATEGORY_ID, userPref.getString(CATEGORY_ID, null));
        userData.put(TAKEN_QUESTION, userPref.getString(TAKEN_QUESTION, null));
        userData.put(CORRECT_ANS, userPref.getString(CORRECT_ANS, null));
        userData.put(DATE_OF_TEST, userPref.getString(DATE_OF_TEST, null));
        return userData;
    }

    // Create login session
    public void createUser(int categoryId, int takenNumberofQuestion, int numberofCurrectAns, Date testDate){
        initSharedPrefFile();
        // Storing email and pass in Pref
        editor.putString(CATEGORY_ID, String.valueOf(categoryId));
        editor.putString(TAKEN_QUESTION, String.valueOf(takenNumberofQuestion));
        editor.putString(CORRECT_ANS, String.valueOf(numberofCurrectAns));
        editor.putString(DATE_OF_TEST, testDate.toString());
        // commit change
        editor.commit();
    }

    // Clearing all user data from Shared Preferences registeredUserDataFile
    public void clearAllData(){
        editor.clear();
        editor.commit();
    }
}

