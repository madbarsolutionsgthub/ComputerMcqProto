package madbarsoft.com.computershortquestionforitjob.practise;

import android.content.Context;
import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import at.blogc.android.views.ExpandableTextView;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqAnswerModel;
import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.utility.DelaTimer;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    List<McqQuestionAnswerModel> mcqQuestionAndAnsList;
    private LayoutInflater layoutInflater;
    private Context context;

    private Animation animShow, animHide;

    public RecyclerAdapter(Context context, List<McqQuestionAnswerModel> mcqQuestionAndAnsList) {
        this.layoutInflater = LayoutInflater.from(context);
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

        animShow = AnimationUtils.loadAnimation( context, R.anim.slide_down);
        animHide = AnimationUtils.loadAnimation( context, R.anim.slie_up);

        for(McqAnswerModel obj:mcqQuestionAndAnsList.get(position).getMcqAnswerModelList()){
            mcqAnsList+= obj.getTitle().toString()+"\n";
            if(obj.getIsRightAns()==1){
                correctAns+= obj.getTitle();
            }
        }
        holder.getQuestionTitleId().setText(mcqQuestionAndAnsList.get(position).getTitle() );
        holder.getMcqAnswerListHolderId().setText(mcqAnsList);
        holder.getExpandableTextView().setText(correctAns);

        final ExpandableTextView expandableTextView =  holder.getExpandableTextView();

// set animation duration via code, but preferable in your layout files by using the animation_duration attribute
        expandableTextView.setAnimationDuration(750L);

        // set interpolators for both expanding and collapsing animations
        expandableTextView.setInterpolator(new OvershootInterpolator());

// or set them separately
        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());

        holder.getShow_more().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView.isExpanded())
                {
                    expandableTextView.collapse();
                    holder.getShow_more().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
                }
                else
                {
                    expandableTextView.expand();
                    holder.getShow_more().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mcqQuestionAndAnsList.size();
    }


}