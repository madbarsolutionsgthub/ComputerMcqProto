package madbarsoft.com.computershortquestionforitjob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import madbarsoft.com.computershortquestionforitjob.R;
import madbarsoft.com.computershortquestionforitjob.questioncategory.CategoryModel;

public class CustomAdapterForGridView extends BaseAdapter{

    private List<CategoryModel> categoryList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapterForGridView(Context context, List<CategoryModel> categoryList){
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertViewForPersonAdp, ViewGroup viewGroup) {
        if(convertViewForPersonAdp==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertViewForPersonAdp =  layoutInflater.inflate(R.layout._custom_grid_view_holder, viewGroup, false);
        }
        TextView title = (TextView)convertViewForPersonAdp.findViewById(R.id.categoryNameHolderId);
        TextView categoryStatusId = (TextView)convertViewForPersonAdp.findViewById(R.id.categoryStatusHolderId);
        title.setText(categoryList.get(position).getTitle().toString());
        categoryStatusId.setText("Total Question: "+categoryList.get(position).getNumberOfQuestion());
        return convertViewForPersonAdp;
    }
}
