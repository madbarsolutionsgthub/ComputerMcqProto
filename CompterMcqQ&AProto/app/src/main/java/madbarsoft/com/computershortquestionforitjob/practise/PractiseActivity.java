package madbarsoft.com.computershortquestionforitjob.practise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAndAnswerService;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerService;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryModel;

public class PractiseActivity extends AppCompatActivity {
    List<QuestionAnswerModel> questionAndAnsList = new ArrayList<>();
    List<McqQuestionAnswerModel> mcqQuestionAndAnsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Animation animationUp, animationDown;
    private int position;
    private int categoryId;
    private CategoryModel currentCategory;
    List<CategoryModel> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practise);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId",-1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            mcqQuestionAndAnsList =  new McqQuestionAndAnswerService().getQuestionAndAnsListFromJson(this, categoryId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slie_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        RecyclerAdapter recyclerViewAdapter = new RecyclerAdapter(this, animationUp, animationDown, mcqQuestionAndAnsList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}

