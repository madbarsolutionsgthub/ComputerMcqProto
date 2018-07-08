package madbarsoft.com.computershortquestionforitjob.practise;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import madbarsoft.com.computershortquestionforitjob.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{
    private TextView mcqAnswerListHolderId;
    private TextView showMore;
    private TextView contentLayout;
    private TextView questionTitleId;

    public RecyclerViewHolder(final View v) {
        super(v);
        mcqAnswerListHolderId = (TextView) v.findViewById(R.id.mcqAnswerListHolderId);
        contentLayout = (TextView) v.findViewById(R.id.content);
        showMore = (TextView) v.findViewById(R.id.show_more);
        questionTitleId = (TextView) v.findViewById(R.id.questionTitleId);
    }

    public TextView getMcqAnswerListHolderId() {
        return mcqAnswerListHolderId;
    }

    public void setMcqAnswerListHolderId(TextView mcqAnswerListHolderId) {
        this.mcqAnswerListHolderId = mcqAnswerListHolderId;
    }

    public TextView getShowMore() {
        return showMore;
    }

    public void setShowMore(TextView showMore) {
        this.showMore = showMore;
    }

    public TextView getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(TextView contentLayout) {
        this.contentLayout = contentLayout;
    }

    public TextView getQuestionTitleId() {
        return questionTitleId;
    }

    public void setQuestionTitleId(TextView questionTitleId) {
        this.questionTitleId = questionTitleId;
    }
}

