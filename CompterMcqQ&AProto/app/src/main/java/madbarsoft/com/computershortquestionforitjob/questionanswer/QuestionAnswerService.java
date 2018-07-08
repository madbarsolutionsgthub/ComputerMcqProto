package madbarsoft.com.computershortquestionforitjob.questionanswer;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.R;

public class QuestionAnswerService {

    public List<QuestionAnswerModel> getQuestionAndAnsList(AppCompatActivity appCompatActivity, int categoryId) throws IOException, JSONException {
        List<QuestionAnswerModel> questionAnsList = new ArrayList<>();
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
            questionAnsList.add(new QuestionAnswerModel(Integer.parseInt(obj.getString("sNo")), Integer.parseInt(obj.getString("categoryId")), obj.getString("qst").toString(), obj.getString("ans").toString()));
        }

        return questionAnsList;
    }
}
