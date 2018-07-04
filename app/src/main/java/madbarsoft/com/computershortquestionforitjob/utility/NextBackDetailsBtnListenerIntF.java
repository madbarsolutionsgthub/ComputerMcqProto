package madbarsoft.com.computershortquestionforitjob.utility;

import madbarsoft.com.computershortquestionforitjob.questionanswer.QuestionAnswerModel;

public interface NextBackDetailsBtnListenerIntF {
    void nextData(QuestionAnswerModel questionAndAns, int currentDataPosition);
    void backData(QuestionAnswerModel questionAndAns, int currentDataPosition);
}
