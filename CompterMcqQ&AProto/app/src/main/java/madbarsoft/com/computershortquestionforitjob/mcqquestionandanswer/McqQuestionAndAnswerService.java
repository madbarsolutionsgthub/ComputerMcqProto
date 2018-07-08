package madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import madbarsoft.com.computershortquestionforitjob.R;


public class McqQuestionAndAnswerService {


    public List<McqQuestionAnswerModel> getQuestionAndAnsListFromJson(AppCompatActivity appCompatActivity, int categoryId) throws IOException, JSONException {
        List<McqAnswerModel> mcqAnswerModelList;
        List<McqQuestionAnswerModel> questionAnsList = new ArrayList<>();

        String json;
        InputStream inputStream=null;

        if(categoryId==11){
            inputStream = appCompatActivity.getResources().openRawResource(R.raw.file_networking);
        }else if(categoryId==22){
            inputStream = appCompatActivity.getResources().openRawResource(R.raw.file_operatingsystem);
        }else if(categoryId==10){
            inputStream = appCompatActivity.getResources().openRawResource(R.raw.computer_history);
        }else{
            inputStream = appCompatActivity.getResources().openRawResource(R.raw.question_answer_empty_data);
        }

        int size = inputStream.available();

        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        json = new String(buffer, "UTF-8");
        JSONArray jsonArray=new JSONArray(json);

        for(int i=0; i<jsonArray.length(); i++){
             JSONObject obj = jsonArray.getJSONObject(i);
             mcqAnswerModelList= new ArrayList<>();
             JSONArray jsonAnsArray=new JSONArray(obj.get("ansList").toString());
                for(int j=0; j<jsonAnsArray.length(); j++){
                    JSONObject ansObj = jsonAnsArray.getJSONObject(j);
                    mcqAnswerModelList.add(new McqAnswerModel(Integer.parseInt(ansObj.getString("id")),Integer.parseInt(ansObj.getString("questionId")),ansObj.getString("title").toString(),Integer.parseInt(ansObj.getString("isRightAns"))));
                }
             questionAnsList.add(new McqQuestionAnswerModel(Integer.parseInt(obj.getString("id")),Integer.parseInt(obj.getString("categoryId")),obj.getString("questionTitle").toString(), mcqAnswerModelList));
        }

        return questionAnsList;
    }

}
