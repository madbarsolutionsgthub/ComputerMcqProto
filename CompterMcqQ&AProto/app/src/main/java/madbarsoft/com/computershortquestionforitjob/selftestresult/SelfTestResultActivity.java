package madbarsoft.com.computershortquestionforitjob.selftestresult;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
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
    PieChart pieChart, pieChart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test_result);

        computerHistoryTestResultHolderId = findViewById(R.id.computerHistoryTestResultHolderId);
        computerHardWareTestResultHolderId = findViewById(R.id.computerHardWareTestResultHolderId);
        testService = new TestService(getApplicationContext(), 10);
        HashMap<String, String> user = testService.getUserDetails();
        String name10 = user.get(testService.CORRECT_ANS);
        String tekenQuestion10 = user.get(testService.TAKEN_QUESTION);

        testService = new TestService(getApplicationContext(), 11);
        HashMap<String, String> user2 = testService.getUserDetails();
        String name11 = user2.get(testService.CORRECT_ANS);
        String tekenQuestion11 = user2.get(testService.TAKEN_QUESTION);

        computerHistoryTestResultHolderId.setText("Total Question: "+tekenQuestion11+"\n Correct Ans: "+name11);
        computerHardWareTestResultHolderId.setText("Total Question: "+tekenQuestion10+"\n Correct Ans: "+name10);

        pieChart = (PieChart) findViewById(R.id.idPieChart);
        pieChart2 = (PieChart) findViewById(R.id.idPieChart2);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(50);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        // customize legends
        Legend l = pieChart.getLegend();
       l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(10);
        l.setYEntrySpace(7);

        // customize legends
        Legend l2 = pieChart2.getLegend();
        l2.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l2.setXEntrySpace(10);
        l2.setYEntrySpace(7);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(Integer.parseInt(tekenQuestion10), "False"));
        yValues.add(new PieEntry(Integer.parseInt(name10), "Correct"));

        PieDataSet pieDataSet = new PieDataSet(yValues, "History");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);
          pieChart.setData(pieData);


        pieChart2.setUsePercentValues(true);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.setExtraOffsets(5,10,5,5);

        pieChart2.setDragDecelerationFrictionCoef(0.95f);

        pieChart2.setDrawHoleEnabled(true);
        pieChart2.setHoleColor(Color.WHITE);
        pieChart2.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues2 = new ArrayList<>();
        yValues2.add(new PieEntry(Integer.parseInt(tekenQuestion11), "False"));
        yValues2.add(new PieEntry(Integer.parseInt(name11), "Correct"));

        PieDataSet pieDataSet2 = new PieDataSet(yValues2, "Hardware");
        pieDataSet2.setSliceSpace(3f);
        pieDataSet2.setSelectionShift(5f);
        pieDataSet2.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData2 = new PieData(pieDataSet2);
        pieData2.setValueTextSize(10f);
        pieData2.setValueTextColor(Color.YELLOW);
        pieChart2.setData(pieData2);
















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
