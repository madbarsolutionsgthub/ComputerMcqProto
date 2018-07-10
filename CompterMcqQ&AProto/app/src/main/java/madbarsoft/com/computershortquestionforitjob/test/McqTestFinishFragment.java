package madbarsoft.com.computershortquestionforitjob.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;

import madbarsoft.com.computershortquestionforitjob.MainActivity;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;


public class McqTestFinishFragment extends Fragment {
    Button  btnHome;
    private Context context;
    private int categoryId=0;
    private int numberOfCorrectAns=0;
    private int takenNumberOfQuestion;
    private McqQuestionAnswerModel mcqQuestionAnswerModel;

    TestService testService;
    TestModel testModel;


    public McqTestFinishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        categoryId = getArguments().getInt("categoryId");
        takenNumberOfQuestion = getArguments().getInt("takenNumberOfQuestion");
        numberOfCorrectAns = getArguments().getInt("numberOfCorrectAns");

        testModel = new TestModel();
        testModel.setCategoryId(categoryId);
        testModel.setTakenQuestion(takenNumberOfQuestion);
        testModel.setCorrectAns(numberOfCorrectAns);
        testModel.setTestData(new Date());

        testService = new TestService(context.getApplicationContext(), testModel.getCategoryId());
        testService.createUser(testModel.getCategoryId(), testModel.getTakenQuestion(), testModel.getCorrectAns(), testModel.getTestData());

        HashMap<String, String> user = testService.getUserDetails();
        String name = user.get(testService.CORRECT_ANS);

        Toast.makeText(context, "Correct Ans : "+name, Toast.LENGTH_SHORT).show();


        View vu = inflater.inflate(R.layout.fragment_mcq_test_finish, container, false);
        TextView examStatusId = vu.findViewById(R.id.examStatusId);

        examStatusId.setText("Take Question : "+takenNumberOfQuestion+" Correct Ans: "+numberOfCorrectAns+" CategoryId: "+categoryId);

        btnHome = (Button)vu.findViewById(R.id.btnHomeId);
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
