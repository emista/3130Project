package com.project.csci3130.dalrs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseListViewActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter adapter;
    private Map<String, List<String>> courseList = new HashMap<>();
    private String[] groupList = new String[]{"Computer Science", "Statistic"};
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelist);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableList);
        InitialData();
        adapter = new ExpandableListViewAdapter();
        expandableListView.setAdapter(adapter);


    }

    private void InitialData(){
        list1.add("CSCI3130");
        list1.add("CSCI2112");
        list1.add("CSCI4144");
        list1.add("CSCI1000");
        list1.add("CSCI3120");
        list2.add("STAT1060");
        list2.add("STAT2060");
        list2.add("STAT2080");

        courseList.put(groupList[0],list1);
        courseList.put(groupList[1],list2);
    }
    public class ExpandableListViewAdapter extends BaseExpandableListAdapter{

        @Override
        //get one item from one groupList.
        public Object getChild(int parentPosition, int childPosition){
            return courseList.get(groupList[parentPosition]).get(childPosition);
        }

        @Override
        //Get the number of groups
        public int getGroupCount(){
            return courseList.size();
        }

        @Override
        //Get the number of child elements in the specified group
        public int getChildrenCount(int parentPosition){
            return courseList.get(groupList[parentPosition]).size();
        }

        @Override
        //Get the data in the specified group
        public Object getGroup(int parentPosition){
            return courseList.get(groupList[parentPosition]);
        }

        @Override
        //Get the id of a child of a group
        public long getChildId(int parentPosition, int childPosition){
            return childPosition;
        }

        @Override
        //Get the id of the specified group
        public long getGroupId(int parentPosition){
            return parentPosition;
        }

        @Override
        public boolean hasStableIds(){
            return false;
        }

        @Override
        public View getGroupView(int parentPosition, boolean isExpand,
                                 View convertView, ViewGroup viewGroup){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_grouplist,null);
            }
            convertView.setTag(R.layout.activity_grouplist, parentPosition);

            convertView.setTag(R.layout.activity_childlist, -1);

            TextView textView = (TextView) convertView.findViewById(R.id.groupView);

            textView.setText(groupList[parentPosition]);

            return convertView;
        }

        @Override
        public View getChildView(int parentPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup viewGroup){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_childlist,null);
            }
            convertView.setTag(R.layout.activity_grouplist, parentPosition);

            convertView.setTag(R.layout.activity_childlist, childPosition);

            TextView textView = (TextView) convertView.findViewById(R.id.childView);

            textView.setText(courseList.get(groupList[parentPosition]).get(childPosition));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int parentPosition, int childPosition){
            return false;  //not in interaction 1
        }
    }

}