package madbarsoft.com.computershortquestionforitjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.adapter.CustomAdapterForGridView;
import madbarsoft.com.computershortquestionforitjob.contactus.ContactUsActivity;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryModel;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryService;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    List<CategoryModel> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.homePageWithGridViewId);
        try {
            categoryList = new CategoryService().getCategoryJsonData(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomAdapterForGridView customAdapter = new CustomAdapterForGridView(this, categoryList);
        gridView.setAdapter(customAdapter);

        // create onClickListener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long id) {
                Intent inten = new Intent(MainActivity.this, QuestionListViewActivity.class );
                inten.putExtra("itemPosition",itemPosition);
                inten.putExtra("categoryId",categoryList.get(itemPosition).getId());
                inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inten);

            }
        });

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
            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
            return true;
        }if(item.getItemId()==R.id.contactUsId){
            Intent inten = new Intent(MainActivity.this, ContactUsActivity.class );
            inten.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inten);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
