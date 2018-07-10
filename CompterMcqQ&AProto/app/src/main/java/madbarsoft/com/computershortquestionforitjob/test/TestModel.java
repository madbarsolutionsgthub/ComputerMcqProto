package madbarsoft.com.computershortquestionforitjob.test;

import java.util.Date;

public class TestModel {

    private int categoryId;
    private int takenQuestion;
    private int correctAns;
    private Date testData;




    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTakenQuestion() {
        return takenQuestion;
    }

    public void setTakenQuestion(int takenQuestion) {
        this.takenQuestion = takenQuestion;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public Date getTestData() {
        return testData;
    }

    public void setTestData(Date testData) {
        this.testData = testData;
    }
}
