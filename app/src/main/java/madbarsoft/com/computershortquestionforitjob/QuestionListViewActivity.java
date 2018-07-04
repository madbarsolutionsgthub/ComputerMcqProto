package madbarsoft.com.computershortquestionforitjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.adapter.CustomAdapterForListView;
import madbarsoft.com.computershortquestionforitjob.detailshowquestionandanswer.DetailsShowActivity;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerService;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryModel;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryService;

public class QuestionListViewActivity extends AppCompatActivity {

    private ListView customListView;
    List<QuestionAnswerModel> questionAndAnsList = new ArrayList<>();
    private int position;
    private int categoryId;
    private CategoryModel currentCategory;
    List<CategoryModel> categoryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list_view);
        TextView categoryNameHolderId = findViewById(R.id.categoryNameHolderId);
        TextView currentStatusId = findViewById(R.id.currentStatusId);
        Intent intent = getIntent();
        position = intent.getIntExtra("itemPosition",-1);
        categoryId = intent.getIntExtra("categoryId",-1);

        try {
            questionAndAnsList =  new QuestionAnswerService().getQuestionAndAnsList(this, categoryId);
            categoryList = new CategoryService().getCategoryJsonData(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(CategoryModel category:categoryList){
            if(category.getId()==categoryId){
                this.currentCategory=category;
            }
        }
        if(currentCategory !=null){
            categoryNameHolderId.setText(currentCategory.getTitle().toString());
        }
        currentStatusId.setText("Number Of Question: "+questionAndAnsList.size());
        customListView = (ListView)findViewById(R.id.listViewCustomAdapter);
        CustomAdapterForListView customAdapter = new CustomAdapterForListView(this, questionAndAnsList);
        customListView.setAdapter(customAdapter);

        // create onClickListener
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long id) {
                Intent inten = new Intent(QuestionListViewActivity.this, DetailsShowActivity.class );
                inten.putExtra("itemPosition",itemPosition);
                inten.putExtra("categoryId",categoryId);
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);

            }
        });

    }

}

