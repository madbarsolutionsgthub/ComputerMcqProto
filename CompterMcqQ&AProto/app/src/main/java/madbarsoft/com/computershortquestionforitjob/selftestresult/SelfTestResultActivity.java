package madbarsoft.com.computershortquestionforitjob.selftestresult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
    private LineChart mChart;
    private LineChart lineChart;
    LineDataSet lineDataSet;
    LimitLine lowerLimitLine,upperLimitLine;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test_result);

         //line chart

        //        lineChart.getAxisLeft().addLimitLine(lowerLimitLine(2,"Lower Limit",2,12,Color.YELLOW,Color.BLUE));
//        lineChart.getAxisLeft().addLimitLine(upperLimitLine(5,"Upper Limit",2,12,Color.YELLOW,Color.BLUE));

//        LimitLine upper_limit = new LimitLine(10, "Upper Limit");
//        upper_limit.setLineWidth(2);
//        upper_limit.enableDashedLine(10f, 10f, 0f);
//        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
//        upper_limit.setTextSize(10f);
//
//        LimitLine lower_limit = new LimitLine(10f, "Lower Limit");
//        lower_limit.setLineWidth(4f);
//        lower_limit.enableDashedLine(10f, 10f, 0f);
//        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        lower_limit.setTextSize(10f);

        ArrayList<Entry> lineEntries = new ArrayList<>();

        lineChart = (LineChart) findViewById(R.id.linechart);

        lineEntries = new ArrayList<Entry>();
        lineEntries.add(new Entry(1, 10));
        lineEntries.add(new Entry(2, 40));
        lineEntries.add(new Entry(3, 20));
        lineEntries.add(new Entry(4, 30));
        lineEntries.add(new Entry(5, 30));
//        lineEntries.add(new Entry(5, 3));
//        lineEntries.add(new Entry(6, 1));
//        lineEntries.add(new Entry(7, 5));
//        lineEntries.add(new Entry(8, 7));
//        lineEntries.add(new Entry(9, 6));
//        lineEntries.add(new Entry(10, 4));
//        lineEntries.add(new Entry(11, 5));

        lineDataSet = new LineDataSet(lineEntries, "Oil Price");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
      // lineDataSet.setLineWidth(5);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.GREEN);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.RED);

        LineData lineData = new LineData(lineDataSet);

        lineChart.getDescription().setText("Price in last 12 days");
        lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);
     //   lineChart.setMarker(markerView(context));
//        lineChart.getAxisLeft().addLimitLine(lowerLimitLine(2,"Lower Limit",2,12,Color.YELLOW,Color.BLUE));
//        lineChart.getAxisLeft().addLimitLine(upperLimitLine(5,"Upper Limit",2,12,Color.YELLOW,Color.BLUE));

//        lineChart.getAxisLeft().addLimitLine(lower_limit);
//        lineChart.getAxisLeft().addLimitLine(upper_limit);


        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.animateY(2000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());
        lineChart.setData(lineData);



         //line chart


        computerHardWareTestResultHolderId = findViewById(R.id.computerHardWareTestResultHolderId);
        testService = new TestService(getApplicationContext(), 10);
        HashMap<String, String> user = testService.getUserDetails();
        String name10 = user.get(testService.CORRECT_ANS);
        String tekenQuestion10 = user.get(testService.TAKEN_QUESTION);

        testService = new TestService(getApplicationContext(), 11);
        HashMap<String, String> user2 = testService.getUserDetails();
        String name11 = user2.get(testService.CORRECT_ANS);
        String tekenQuestion11 = user2.get(testService.TAKEN_QUESTION);

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
      //  Legend l = pieChart.getLegend();
//       l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        l.setXEntrySpace(10);
//        l.setYEntrySpace(7);

        // customize legends
//        Legend l2 = pieChart2.getLegend();
//        l2.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        l2.setXEntrySpace(10);
//        l2.setYEntrySpace(7);

        int resultOne = (Integer.parseInt(tekenQuestion10) - Integer.parseInt(name10));

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(resultOne, "False"));
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

        int resultTwo = (Integer.parseInt(tekenQuestion11) - Integer.parseInt(name11));

        ArrayList<PieEntry> yValues2 = new ArrayList<>();
        yValues2.add(new PieEntry(resultTwo, "False"));
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
