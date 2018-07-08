package madbarsoft.com.computershortquestionforitjob.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.MainActivity;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqAnswerModel;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAndAnswerService;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryModel;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryService;
import madbarsoft.com.computershortquestionforitjob.utility.INextBtnClickListener;

public class McqTestFragment extends Fragment {

    Button btnNext, btnHome;
    RadioGroup mcqRadioBtnGroupId;
    private INextBtnClickListener iNextBtnClickListener;
    private Context context;
    private int currentDataPosition=0;
    private int isCorrectAns=0;
    private int numberOfCorrectAns=0;
    private int takenNumberOfQuestion;
    private int currentAnsId;
    private McqQuestionAnswerModel mcqQuestionAnswerModel;
    List<McqQuestionAnswerModel> mcqQuestionAndAnsList;
    private CategoryModel currentCategory;
    List<CategoryModel> categoryList = new ArrayList<>();

    public McqTestFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
        iNextBtnClickListener= (INextBtnClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mcqQuestionAnswerModel = (McqQuestionAnswerModel) getArguments().getSerializable("mcqQuestionAnswerModel");
        currentDataPosition = getArguments().getInt("currentDataPosition");
        takenNumberOfQuestion = getArguments().getInt("takenNumberOfQuestion");
        numberOfCorrectAns = getArguments().getInt("numberOfCorrectAns");

        try {
            mcqQuestionAndAnsList =  new McqQuestionAndAnswerService().getQuestionAndAnsListFromJson((AppCompatActivity) context, mcqQuestionAnswerModel.getCategoryId());
            categoryList = new CategoryService().getCategoryJsonData((AppCompatActivity)this.getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(CategoryModel category:categoryList){
            if(category.getId()==mcqQuestionAnswerModel.getCategoryId()){
                this.currentCategory=category;
            }
        }

        View vu = inflater.inflate(R.layout.fragment_mcq_test, container, false);

        mcqRadioBtnGroupId = (RadioGroup)vu.findViewById(R.id.mcqRadioBtnGroupId);

        TextView mcqTestQuesTitleId = vu.findViewById(R.id.mcqTestQuesTitleId);
        TextView currentStatusId = vu.findViewById(R.id.currentStatusId);
        TextView examStatusId = vu.findViewById(R.id.examStatusId);
        TextView categoryNameHolderId = vu.findViewById(R.id.categoryNameHolderId);

        if(mcqQuestionAnswerModel !=null){
            if(currentCategory !=null){
                categoryNameHolderId.setText(currentCategory.getTitle().toString());
            }
            currentStatusId.setText("Question : "+takenNumberOfQuestion+" of "+mcqQuestionAndAnsList.size());
            examStatusId.setText("Take Question : "+takenNumberOfQuestion+" Correct Ans: "+numberOfCorrectAns);
            mcqTestQuesTitleId.setText(mcqQuestionAnswerModel.getTitle());
            for(McqAnswerModel obj:mcqQuestionAndAnsList.get(currentDataPosition).getMcqAnswerModelList()){
                if(obj.getIsRightAns()==1){
                    currentAnsId = obj.getId();
                }
                    final RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(obj.getTitle().toString());
                    radioButton.setId(obj.getId());
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(currentAnsId==radioButton.getId()) {
                                isCorrectAns = 1;
                                Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                            } else{
                                isCorrectAns = 0;
                                Toast.makeText(getContext(), "False Answer" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                mcqRadioBtnGroupId.addView(radioButton);
            }
        }

        btnNext = (Button)vu.findViewById(R.id.btnNextId);
        btnHome = (Button)vu.findViewById(R.id.btnHomeId);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((mcqQuestionAndAnsList.size()-1) != currentDataPosition) {
                    iNextBtnClickListener.nextData(mcqQuestionAndAnsList.get((currentDataPosition + 1)), (currentDataPosition + 1), isCorrectAns, 0);
                    return;
                }
                iNextBtnClickListener.nextData(mcqQuestionAndAnsList.get((currentDataPosition)), (currentDataPosition), isCorrectAns,1);
                btnNext.setEnabled(false);
                btnNext.setBackgroundColor(Color.GRAY);

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



}
