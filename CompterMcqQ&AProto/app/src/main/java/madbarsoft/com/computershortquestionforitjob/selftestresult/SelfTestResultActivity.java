package madbarsoft.com.computershortquestionforitjob.selftestresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;

import madbarsoft.com.computershortquestionforitjob.MainActivity;
import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.contactus.ContactUsActivity;
import madbarsoft.com.computershortquestionforitjob.test.TestModel;
import madbarsoft.com.computershortquestionforitjob.test.TestService;

public class SelfTestResultActivity extends AppCompatActivity {

    Button btnHome;
    TestService testService;
    TestModel testModel;
    TextView computerHistoryTestResultHolderId, computerHardWareTestResultHolderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test_result);

        computerHistoryTestResultHolderId = findViewById(R.id.computerHistoryTestResultHolderId);
        computerHardWareTestResultHolderId = findViewById(R.id.computerHardWareTestResultHolderId);


        testService = new TestService(getApplicationContext(), 10);
        HashMap<String, String> user = testService.getUserDetails();
        String name1 = user.get(testService.CORRECT_ANS);

        testService = new TestService(getApplicationContext(), 11);
        HashMap<String, String> user2 = testService.getUserDetails();
        String name = user2.get(testService.CORRECT_ANS);

        computerHistoryTestResultHolderId.setText(name);
        computerHardWareTestResultHolderId.setText(name1);




        btnHome = (Button)findViewById(R.id.btnHomeId);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(SelfTestResultActivity.this, MainActivity.class );
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);

            }
        });




    }
}
