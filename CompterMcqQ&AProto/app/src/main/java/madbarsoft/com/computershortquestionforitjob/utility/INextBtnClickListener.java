package madbarsoft.com.computershortquestionforitjob.utility;

import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;

public interface INextBtnClickListener {
    void nextData(McqQuestionAnswerModel questionAndAns, int currentDataPosition, int isCorrectAns, int isFinishTest);
}
