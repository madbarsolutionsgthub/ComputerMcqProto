//package madbarsoft.com.computershortquestionforitjob.detailshowquestionandanswer;
//
//import android.content.Intent;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.Button;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import madbarsoft.com.computershortquestionforitjob.R;
//import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;
//import madbarsoft.com.computershortquestionforitjob.utility.NextBackDetailsBtnListenerIntF;
//
//public class DetailsShowActivity extends AppCompatActivity implements NextBackDetailsBtnListenerIntF{
//
//    int startPoint = 0;
//    int categoryId = 0;
//    List<QuestionAnswerModel> questionAndAnsList = new ArrayList<>();
//    QuestionAnswerModel questionAndAns;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details_show);
//
//
//        Intent intent = getIntent();
//        startPoint = intent.getIntExtra("itemPosition",-1);
//        categoryId = intent.getIntExtra("categoryId",-1);
//
//        try {
//            generateQuestionAndAnswerData(categoryId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        questionAndAns = questionAndAnsList.get(startPoint);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        DetailsShowFragment detailsFragment = new DetailsShowFragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putInt("currentDataPosition", startPoint);
//        bundle.putSerializable("questionAndAns", questionAndAns);
//        detailsFragment.setArguments(bundle);
//
//        fragmentTransaction.add(R.id.fragmentContainerId, detailsFragment);
//        fragmentTransaction.commit();
//
//    }
//
//    @Override
//    public void nextData(QuestionAnswerModel questionAndAns, int currentDataPosition) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        DetailsShowFragment detailsFragment = new DetailsShowFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("currentDataPosition", currentDataPosition);
//        bundle.putSerializable("questionAndAns", questionAndAns);
//        detailsFragment.setArguments(bundle);
//        fragmentTransaction.replace(R.id.fragmentContainerId, detailsFragment);
//        fragmentTransaction.commit();
//    }
//
//    @Override
//    public void backData(QuestionAnswerModel questionAndAns, int currentDataPosition) {
//        //   Toast.makeText(this, "Msg: You Click Back Button, Pos:"+currentDataPosition+"\n Person Name: "+person.getName(), Toast.LENGTH_SHORT).show();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        DetailsShowFragment detailsFragment = new DetailsShowFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("currentDataPosition", currentDataPosition);
//        bundle.putSerializable("questionAndAns", questionAndAns);
//        detailsFragment.setArguments(bundle);
//        fragmentTransaction.replace(R.id.fragmentContainerId, detailsFragment);
//        fragmentTransaction.commit();
//    }
//
//    public void generateQuestionAndAnswerData(int categoryId) throws IOException, JSONException {
//        String json;
//        InputStream inputStream=null;
//        if(categoryId==11){
//            inputStream = getResources().openRawResource(R.raw.file_networking);
//        }else if(categoryId==22){
//            inputStream = getResources().openRawResource(R.raw.file_operatingsystem);
//        }else if(categoryId==10){
//            inputStream = getResources().openRawResource(R.raw.computer_history);
//        }else{
//            inputStream = getResources().openRawResource(R.raw.question_answer_empty_data);
//        }
//        int size = inputStream.available();
//        byte[] buffer = new byte[size];
//        inputStream.read(buffer);
//        inputStream.close();
//
//        json = new String(buffer, "UTF-8");
//        JSONArray jsonArray=new JSONArray(json);
//
//        for(int i=0; i<jsonArray.length(); i++){
//            JSONObject obj = jsonArray.getJSONObject(i);
//            questionAndAnsList.add(new QuestionAnswerModel(Integer.parseInt(obj.getString("sNo")), Integer.parseInt(obj.getString("categoryId")), obj.getString("qst").toString(), obj.getString("ans").toString()));
//        }
//    }
//
//}
