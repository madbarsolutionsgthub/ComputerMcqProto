package madbarsoft.com.computershortquestionforitjob.utility;

import madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer.McqQuestionAnswerModel;
import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;

public interface NextBackDetailsBtnListenerIntF {
    void nextData(McqQuestionAnswerModel questionAndAns, int currentDataPosition, int isCorrectAns);
    void backData(McqQuestionAnswerModel questionAndAns, int currentDataPosition);
}
