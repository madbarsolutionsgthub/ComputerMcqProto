package madbarsoft.com.computershortquestionforitjob.test;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAndAnswerService;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.utility.INextBtnClickListener;

public class McqTestActivity extends AppCompatActivity implements INextBtnClickListener {

    int startPoint = 0;
    int numberOfCorrectAns = 0;
    int categoryId = 10;
    int takenNumberOfQuestion=1;
    List<McqQuestionAnswerModel> mcqQuestionAndAnsList = new ArrayList<>();
    McqQuestionAnswerModel mcqQuestionAnswerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_test);

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId",10);
        startPoint = intent.getIntExtra("currentDataPosition",0);
        try {
            mcqQuestionAndAnsList =  new McqQuestionAndAnswerService().getQuestionAndAnsListFromJson(this, categoryId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mcqQuestionAnswerModel = mcqQuestionAndAnsList.get(startPoint);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        McqTestFragment mcqTestFragment = new McqTestFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("takenNumberOfQuestion", takenNumberOfQuestion);
        bundle.putInt("currentDataPosition", startPoint);
        bundle.putSerializable("mcqQuestionAnswerModel", mcqQuestionAnswerModel);
        mcqTestFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.fragmentContainerId, mcqTestFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void nextData(McqQuestionAnswerModel mcqQuestionAnswerModel, int currentDataPosition, int isCorrectAns, int isFinishTest) {
        if(isFinishTest==0){
            takenNumberOfQuestion++;
            this.numberOfCorrectAns+=isCorrectAns;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            McqTestFragment mcqTestFragment = new McqTestFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("currentDataPosition", currentDataPosition);
            bundle.putInt("takenNumberOfQuestion", takenNumberOfQuestion);
            bundle.putInt("numberOfCorrectAns", numberOfCorrectAns);
            bundle.putSerializable("mcqQuestionAnswerModel", mcqQuestionAnswerModel);
            mcqTestFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentContainerId, mcqTestFragment);
            fragmentTransaction.commit();
        }else{
            this.numberOfCorrectAns+=isCorrectAns;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            McqTestFinishFragment mcqTestFinishFragment = new McqTestFinishFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("currentDataPosition", currentDataPosition);
            bundle.putInt("takenNumberOfQuestion", takenNumberOfQuestion);
            bundle.putInt("numberOfCorrectAns", numberOfCorrectAns);
            bundle.putSerializable("mcqQuestionAnswerModel", mcqQuestionAnswerModel);
            mcqTestFinishFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentContainerId, mcqTestFinishFragment);
            fragmentTransaction.commit();

        }
    }
}
