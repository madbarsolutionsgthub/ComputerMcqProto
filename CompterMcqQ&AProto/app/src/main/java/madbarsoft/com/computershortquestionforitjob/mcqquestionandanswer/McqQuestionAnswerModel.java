package madbarsoft.com.computershortquestionforitjob.mcqquestionandanswer;

import java.io.Serializable;
import java.util.List;

public class McqQuestionAnswerModel implements Serializable{

    private int id;
    private int categoryId;
    private String title;
    private List<McqAnswerModel> mcqAnswerModelList;

    public McqQuestionAnswerModel(int id, int categoryId, String title, List<McqAnswerModel> mcqAnswerModelList) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.mcqAnswerModelList = mcqAnswerModelList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<McqAnswerModel> getMcqAnswerModelList() {
        return mcqAnswerModelList;
    }

    public void setMcqAnswerModelList(List<McqAnswerModel> mcqAnswerModelList) {
        this.mcqAnswerModelList = mcqAnswerModelList;
    }
}
