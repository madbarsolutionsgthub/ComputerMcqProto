package madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer;

public class McqAnswerModel {
    private int id;
    private int questionId;
    private String title;
    private int isRightAns;

    public McqAnswerModel(int id, int questionId, String title, int isRightAns) {
        this.id = id;
        this.questionId = questionId;
        this.title = title;
        this.isRightAns = isRightAns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsRightAns() {
        return isRightAns;
    }

    public void setIsRightAns(int isRightAns) {
        this.isRightAns = isRightAns;
    }
}
