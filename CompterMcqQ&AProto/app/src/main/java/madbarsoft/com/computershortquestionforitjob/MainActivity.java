package madbarsoft.com.computershortquestionforitjob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.contactus.ContactUsActivity;
import madbarsoft.com.computershortquestionforitjob.practise.PractiseActivity;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryModel;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryService;
import madbarsoft.com.computershortquestionforitjob.rateus.RateUsActivity;
import madbarsoft.com.computershortquestionforitjob.test.McqTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclickPractiseHandeler(View view){
        Intent inten = new Intent(MainActivity.this, PractiseActivity.class );
        switch (view.getId()){
            case R.id.computerHistory10practiseBtnId:
                inten.putExtra("categoryId",10);
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
                break;
            case R.id.computerHardware11practiseBtnId:
                inten.putExtra("categoryId",100);
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
                break;
        }
    }

    public void onclickTestHandeler(View view){
        Intent inten = new Intent(MainActivity.this, McqTestActivity.class );
        switch (view.getId()){
            case R.id.computerHistory10testBtnId:
                inten.putExtra("categoryId",10);
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
                break;
            case R.id.computerHardware11testBtnId:
                inten.putExtra("categoryId",100);
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tool_bar_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.shareUsId){
            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
            return true;
        }if(item.getItemId()==R.id.rateUsId){
            Intent inten = new Intent(MainActivity.this, RateUsActivity.class );
            inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inten);
            return true;
        }if(item.getItemId()==R.id.contactUsId){
            Intent inten = new Intent(MainActivity.this, ContactUsActivity.class );
            inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inten);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to exit ?");
        builder.setMessage("This will be closed application");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.show();

    }

}
