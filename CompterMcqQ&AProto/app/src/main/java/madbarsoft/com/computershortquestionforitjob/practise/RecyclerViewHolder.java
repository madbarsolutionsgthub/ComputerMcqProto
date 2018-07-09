package madbarsoft.com.computershortquestionforitjob.practise;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import at.blogc.android.views.ExpandableTextView;
import madbarsoft.com.computershortquestionforitjob.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{
    private TextView mcqAnswerListHolderId;
    private TextView questionTitleId;
    private TextView show_more;
    private ExpandableTextView expandableTextView;

    public RecyclerViewHolder(final View v) {
        super(v);
        show_more = (TextView) v.findViewById(R.id.show_more);
        mcqAnswerListHolderId = (TextView) v.findViewById(R.id.mcqAnswerListHolderId);
        questionTitleId = (TextView) v.findViewById(R.id.questionTitleId);
        expandableTextView = (ExpandableTextView) v.findViewById(R.id.expandableTextView);
    }


    public TextView getShow_more() {
        return show_more;
    }

    public void setShow_more(TextView show_more) {
        this.show_more = show_more;
    }

    public ExpandableTextView getExpandableTextView() {
        return expandableTextView;
    }

    public void setExpandableTextView(ExpandableTextView expandableTextView) {
        this.expandableTextView = expandableTextView;
    }

    public TextView getMcqAnswerListHolderId() {
        return mcqAnswerListHolderId;
    }

    public void setMcqAnswerListHolderId(TextView mcqAnswerListHolderId) {
        this.mcqAnswerListHolderId = mcqAnswerListHolderId;
    }

    public TextView getQuestionTitleId() {
        return questionTitleId;
    }

    public void setQuestionTitleId(TextView questionTitleId) {
        this.questionTitleId = questionTitleId;
    }
}

