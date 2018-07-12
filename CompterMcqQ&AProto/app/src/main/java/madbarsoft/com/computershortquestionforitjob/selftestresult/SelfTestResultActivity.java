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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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
    private LineChart lineChart;
    LineDataSet lineDataSet;
    LimitLine lowerLimitLine,upperLimitLine;

    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> barEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;

    float barWidth;
    float barSpace;
    float groupSpace;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test_result);

         //bar chart


        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        chart = (BarChart)findViewById(R.id.barChart);
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        int groupCount = 3;

        ArrayList xVals = new ArrayList();

        xVals.add("Jan");
        xVals.add("Feb");
        xVals.add("Mar");


        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        yVals1.add(new BarEntry(1, (float) 1));
        yVals2.add(new BarEntry(1, (float) 2));
        yVals1.add(new BarEntry(2, (float) 3));
        yVals2.add(new BarEntry(2, (float) 4));
        yVals1.add(new BarEntry(3, (float) 5));
        yVals2.add(new BarEntry(3, (float) 6));

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "False");
        set1.setColor(Color.RED);
        set2 = new BarDataSet(yVals2, "Correct");
        set2.setColor(Color.LTGRAY);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

//X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(3);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
//Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);



         //bar chart


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

    public void addValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(1, 10));
        BARENTRY.add(new BarEntry(2, 30));
        BARENTRY.add(new BarEntry(3, 20));


    }

    public void addValuesToBarEntryLabels(){

        barEntryLabels.add("January");
        barEntryLabels.add("February");
        barEntryLabels.add("March");

    }





}
