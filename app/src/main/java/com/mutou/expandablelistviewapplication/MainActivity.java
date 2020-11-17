package com.mutou.expandablelistviewapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.mutou.expandablelistviewapplication.adapter.StudentExpandableAdapter;
import com.mutou.expandablelistviewapplication.entity.DataEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private Button btn_select;
    private boolean selectAll;
    private List<DataEntity>  dataEntityList=new ArrayList<>();
    private StudentExpandableAdapter  studentExpandableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView =findViewById(R.id.listView);
        btn_select=findViewById(R.id.btn_select);
        initData();
        initAdapter();
        setOnClickEvent();
    }

    private void initData() {

        for(int i=0;i<5;i++){
            List<DataEntity.ChildrenData> childrenData=new ArrayList<>();
            for(int j=0;j<8;j++){
                DataEntity.ChildrenData children=new DataEntity.ChildrenData("学生"+(j+1),false);
                childrenData.add(children);
            }
            DataEntity dataEntity=new DataEntity((i+1)+"班",childrenData);
            dataEntityList.add(dataEntity);
        }
    }

    private void setOnClickEvent() {
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAll=!selectAll;
                if(selectAll){
                    //遍历设置全选
                    for(int i=0;i<dataEntityList.size();i++){
                        for(int j=0;j<dataEntityList.get(i).getChildrenDataList().size();j++){
                            dataEntityList.get(i).getChildrenDataList().get(j).setSelect(true);
                        }
                    }
                }else {
                    //遍历设置取消全选
                    for(int i=0;i<dataEntityList.size();i++){
                        for(int j=0;j<dataEntityList.get(i).getChildrenDataList().size();j++){
                            dataEntityList.get(i).getChildrenDataList().get(j).setSelect(false);
                        }
                    }
                }


                studentExpandableAdapter.reFreshData(dataEntityList);

                btn_select.setText(selectAll? "取消全选":"全选");

            }
        });
    }

    private void initAdapter() {
        studentExpandableAdapter=new StudentExpandableAdapter(this,dataEntityList);
        expandableListView.setAdapter(studentExpandableAdapter);

        studentExpandableAdapter.setCheckBoxListener(new StudentExpandableAdapter.CheckBoxListener() {
            @Override
            public void checkStateListener(int groupPosition, int childPosition, boolean isChecked) {
                Log.e("MainActivity","isChecked:"+isChecked);
                dataEntityList.get(groupPosition).getChildrenDataList().get(childPosition).setSelect(isChecked);
                studentExpandableAdapter.reFreshData(dataEntityList);
            }
        });


        /**
         * 默认展开某个item
         * */
        //expandableListView.expandGroup(1);


    }
}
