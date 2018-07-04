package madbarsoft.com.computershortquestionforitjob.questioncategory;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.R;

public class CategoryService{

    public List<CategoryModel> getCategoryJsonData(AppCompatActivity appCompatActivity) throws IOException, JSONException {
        String json;
        List<CategoryModel> categoryList = new ArrayList<>();
        InputStream inputStream = appCompatActivity.getResources().openRawResource(R.raw.category_json_data);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        json = new String(buffer, "UTF-8");
        JSONArray jsonArray=new JSONArray(json);
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            categoryList.add( new CategoryModel(Integer.parseInt(obj.getString("id")), Integer.parseInt(obj.getString("sNo")),obj.getString("title").toString(), obj.getString("description").toString(), Integer.parseInt(obj.getString("numberOfQuestion")) ));
        }
        return categoryList;
        //   Toast.makeText(this, "Data: "+categoryList.toString(), Toast.LENGTH_SHORT).show();
    }
}
