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

import madbarsoft.com.computershortquestionforitjob.MainActivity;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;


public class McqTestFinishFragment extends Fragment {
    Button  btnHome;
    private Context context;
    private int numberOfCorrectAns=0;
    private int takenNumberOfQuestion;
    private McqQuestionAnswerModel mcqQuestionAnswerModel;

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
        mcqQuestionAnswerModel = (McqQuestionAnswerModel) getArguments().getSerializable("mcqQuestionAnswerModel");
        takenNumberOfQuestion = getArguments().getInt("takenNumberOfQuestion");
        numberOfCorrectAns = getArguments().getInt("numberOfCorrectAns");
        View vu = inflater.inflate(R.layout.fragment_mcq_test_finish, container, false);
        TextView examStatusId = vu.findViewById(R.id.examStatusId);

        examStatusId.setText("Take Question : "+takenNumberOfQuestion+" Correct Ans: "+numberOfCorrectAns);

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
