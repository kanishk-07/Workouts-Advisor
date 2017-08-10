package com.example.kanishk.workoutsapp;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//
//
///**
// * Created by Kanishk on 14-07-2017.
// */
//
//public class WorkoutsListAdapter extends BaseAdapter {
//
//    ArrayList<WorkoutItem> mArrayList;
//    Context mContext;
//    //String[] Titles;
//    //int[] images;
//
//    public WorkoutsListAdapter(Context c) {
//
//        mArrayList = new ArrayList<>();
//        Resources res = c.getResources();
//
//        String []Titles = res.getStringArray(R.array.Titles);
//
//        int[] images = {R.drawable.icon_abdominal_crunches,R.drawable.icon_arm_circles,R.drawable.icon_arm_raises,
//                R.drawable.icon_backward_lunges, R.drawable.icon_bicycle_crunch,R.drawable.icon_bird_dog,
//                R.drawable.icon_box_push_up,R.drawable.icon_bridge,R.drawable.icon_burpee,
//                R.drawable.icon_cobras,R.drawable.icon_plank,R.drawable.icon_push_up};
//
//        for(int i=0; i<12; i++)
//        {
//            mArrayList.add(new WorkoutItem(Titles[1], images[1]));
//        }
//    }
//
//    @Override
//    public int getCount() { //In getCount we need to return how many items our array har --> there
//                            //can be only 1 array or something like that so we have to decide a complex datatype.
//                            //We will make a new class
//        return mArrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mArrayList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //View customView = inflater.inflate(R.layout.single_row,,false);
//        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = li.inflate(R.layout.single_row,parent,false);
//        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView2);
//        TextView textView = (TextView)convertView.findViewById(R.id.textView5);
//        //imageView.setImageResource(image[position]);
//        return null;
//    }
//}
//
//
//class WorkoutItem{
//
//    String Title;
//    int image;
//
//    public WorkoutItem(String title, int image) {
//        Title = title;
//        this.image = image;
//    }
//}



import com.example.kanishk.workoutsapp.R;

//public class WorkoutsListAdapter extends ArrayAdapter<String> {
//
//    Resources res = getContext().getResources();
//    String[] Titles = res.getStringArray(R.array.Titles);
//    int[] images = {R.drawable.icon_abdominal_crunches, R.drawable.icon_arm_circles, R.drawable.icon_arm_raises,
//            R.drawable.icon_backward_lunges, R.drawable.icon_bicycle_crunch, R.drawable.icon_bird_dog,
//            R.drawable.icon_box_push_up, R.drawable.icon_bridge, R.drawable.icon_burpee,
//            R.drawable.icon_cobras, R.drawable.icon_plank, R.drawable.icon_push_up};
//
//    public WorkoutsListAdapter(@NonNull Context context, @LayoutRes int resource) {
//        super(context, R.layout.single_row);
//
//    }
//    @Override
//    public int getCount() { //In getCount we need to return how many items our array har --> there
//        //can be only 1 array or something like that so we have to decide a complex datatype.
//        //We will make a new class
//        return mArrayList.size();
//
//    }
//}
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
class WorkoutsListAdapter extends ArrayAdapter<String>
{
    private Context context;
    private int[] images;
    private String[] titleArray;
    WorkoutsListAdapter(Context c, String[] titles,int imgs[])
    {
        super(c, R.layout.single_row,R.id.textView5,titles);
        this.context = c;
        this.images = imgs;
        this.titleArray = titles;
    }

    class ViewHolder {

        ImageView imageView;
        TextView textView;
        ViewHolder(View v)
        {
            imageView = (ImageView) v.findViewById(R.id.imageView2);
            textView = (TextView) v.findViewById(R.id.textView5);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//mat be nullable

        View row = convertView;
        ViewHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row,parent,false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }

        holder.imageView.setImageResource(images[position]);
        Log.d("WorkoutsApp","LINE 77");
        holder.textView.setText("\t\t"+titleArray[position]);

        return row;
    }
}
