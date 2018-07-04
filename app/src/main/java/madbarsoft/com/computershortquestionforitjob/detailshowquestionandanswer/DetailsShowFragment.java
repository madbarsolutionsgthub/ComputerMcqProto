package madbarsoft.com.computershortquestionforitjob.detailshowquestionandanswer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.MainActivity;
import madbarsoft.com.computershortquestionforitjob.QuestionListViewActivity;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerService;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryModel;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryService;
import madbarsoft.com.computershortquestionforitjob.utility.NextBackDetailsBtnListenerIntF;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsShowFragment extends Fragment {

    Button btnNext, btnBack, btnHome, btnList;
    private NextBackDetailsBtnListenerIntF nextAndBackDetailsListenerInteF;
    private Context context;
    private int currentDataPosition;
    private QuestionAnswerModel questionAndAns;
    List<QuestionAnswerModel> questionAndAnsList = new ArrayList<>();
    int currentSno=1;
    private CategoryModel currentCategory;
    List<CategoryModel> categoryList = new ArrayList<>();

    public DetailsShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questionAndAns = (QuestionAnswerModel) getArguments().getSerializable("questionAndAns");
        try {
            questionAndAnsList =  new QuestionAnswerService().getQuestionAndAnsList((AppCompatActivity) this.getActivity(), questionAndAns.getCategoryId());
            categoryList = new CategoryService().getCategoryJsonData((AppCompatActivity)this.getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(CategoryModel category:categoryList){
            if(category.getId()==questionAndAns.getCategoryId()){
                this.currentCategory=category;
            }
        }

        this.currentDataPosition = getArguments().getInt("currentDataPosition");
        View vu = inflater.inflate(R.layout.fragment_details_show, container, false);
        TextView ansTextView = vu.findViewById(R.id.detailsShowTxVuAnsId);
        TextView currentStatusId = vu.findViewById(R.id.currentStatusId);
        TextView categoryNameHolderId = vu.findViewById(R.id.categoryNameHolderId);
        if(questionAndAns !=null){
            this.currentSno = questionAndAns.getsNo();
            if(currentCategory !=null){
                categoryNameHolderId.setText(currentCategory.getTitle().toString());
            }
            currentStatusId.setText("Question : "+currentSno+" of "+questionAndAnsList.size());
            ansTextView.setText("Qst ) "+questionAndAns.getQst().toString()+"\n\nAns ) "+questionAndAns.getAns().toString());
            //textView.setText("Q) "+questionAndAns.getQst().toString()+"\n Ans: "+questionAndAns.getAns().toString());
        }

        btnNext = (Button)vu.findViewById(R.id.btnNextId);
        btnBack = (Button)vu.findViewById(R.id.btnBackId);
        btnList = (Button)vu.findViewById(R.id.btnListId);
        btnHome = (Button)vu.findViewById(R.id.btnHomeId);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getContext(), "Msg:(Next Btn) Current Pos:"+currentSno, Toast.LENGTH_SHORT).show();
                if((questionAndAnsList.size()-1) != currentDataPosition) {
                    nextAndBackDetailsListenerInteF.nextData(questionAndAnsList.get((currentDataPosition + 1)), (currentDataPosition + 1));
                    return;
                }
                nextAndBackDetailsListenerInteF.nextData(questionAndAnsList.get((currentDataPosition)), (currentDataPosition));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getContext(), "Msg: (Btn Back )Current Pos:"+currentSno, Toast.LENGTH_SHORT).show();
                if(currentDataPosition!=0){
                    nextAndBackDetailsListenerInteF.backData(questionAndAnsList.get(currentDataPosition-1), currentDataPosition-1);
                    return;
                }
                nextAndBackDetailsListenerInteF.backData(questionAndAnsList.get(currentDataPosition), currentDataPosition);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, "Data CateId: "+questionAndAns.getCategoryId(), Toast.LENGTH_SHORT).show();
                Intent inten = new Intent(context, QuestionListViewActivity.class );
                inten.putExtra("itemPosition",1);
                inten.putExtra("categoryId",questionAndAns.getCategoryId());
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(context, MainActivity.class );
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
            }
        });

        return vu;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
        nextAndBackDetailsListenerInteF= (NextBackDetailsBtnListenerIntF) context;
    }


}
