package madbarsoft.com.computershortquestionforitjob.practise;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import java.util.List;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqAnswerModel;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    List<McqQuestionAnswerModel> mcqQuestionAndAnsList;
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 100;

    public RecyclerAdapter(Context context, Animation animationUp, Animation animationDown, List<McqQuestionAnswerModel> mcqQuestionAndAnsList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
        this.mcqQuestionAndAnsList = mcqQuestionAndAnsList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);
        return new RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        String mcqAnsList="";
        String correctAns="";
        for(McqAnswerModel obj:mcqQuestionAndAnsList.get(position).getMcqAnswerModelList()){
            mcqAnsList+= obj.getTitle().toString()+"\n";
            if(obj.getIsRightAns()==1){
                correctAns+= obj.getTitle();
            }
        }
        holder.getQuestionTitleId().setText(mcqQuestionAndAnsList.get(position).getTitle() );
        holder.getMcqAnswerListHolderId().setText(mcqAnsList);
        holder.getContentLayout().setText(correctAns);
        holder.getShowMore().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!holder.getContentLayout().isShown()) {
                    holder.getContentLayout().setVisibility(View.VISIBLE);
                    holder.getShowMore().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                } else {
                    holder.getContentLayout().setVisibility(View.GONE);
                    holder.getShowMore().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mcqQuestionAndAnsList.size();
    }


}