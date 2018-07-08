//package madbarsoft.com.computershortquestionforitjob.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//
//import java.util.List;
//
//import madbarsoft.com.computershortquestionforitjob.R;
//import madbarsoft.com.computershortquestionforitjob.practise.RecyclerViewHolderForPractiseListView;
//import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolderForPractiseListView> {
//    List<QuestionAnswerModel> questionAndAnsList;
//    private LayoutInflater layoutInflater;
//    private Animation animationUp, animationDown;
//    private Context context;
//    private final int COUNTDOWN_RUNNING_TIME = 100;
//
//    public RecyclerAdapter(Context context, Animation animationUp, Animation animationDown, List<QuestionAnswerModel> questionAndAnsList) {
//        this.layoutInflater = LayoutInflater.from(context);
//        this.animationDown = animationDown;
//        this.animationUp = animationUp;
//        this.context = context;
//        this.questionAndAnsList = questionAndAnsList;
//    }
//
//    @Override
//    public RecyclerViewHolderForPractiseListView onCreateViewHolder(ViewGroup parent, int viewType) {
//        View item = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);
//        return new RecyclerViewHolderForPractiseListView(item);
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerViewHolderForPractiseListView holder, int position) {
//        holder.getShort_content().setText( questionAndAnsList.get(position).getQst());
//        holder.getContentLayout().setText( questionAndAnsList.get(position).getAns());
//        holder.getShowMore().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (!holder.getContentLayout().isShown()) {
//                    holder.getContentLayout().setVisibility(View.VISIBLE);
//                    holder.getShowMore().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
//                } else {
//
////                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 3) {
////                        @Override
////                        public void onTick(long millisUntilFinished) {
////                        }
////
////                        @Override
////                        public void onFinish() {
////                            holder.getContentLayout().setVisibility(View.GONE);
////                        }
////                    };
////                    countDownTimerStatic.start();
//                    holder.getContentLayout().setVisibility(View.GONE);
//                    holder.getShowMore().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
//
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return questionAndAnsList.size();
//    }
//
//
//}