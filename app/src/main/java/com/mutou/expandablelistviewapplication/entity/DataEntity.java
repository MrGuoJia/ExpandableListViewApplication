package com.mutou.expandablelistviewapplication.entity;

import java.util.List;

/**
 * Created by MrGuo
 * on 2020/11/13
 */
public class DataEntity {
    private String title;//一级列表内容
    private List<ChildrenData> childrenDataList;


    public DataEntity(String title, List<ChildrenData> childrenDataList) {
        this.title = title;
        this.childrenDataList = childrenDataList;
    }

    public List<ChildrenData> getChildrenDataList() {
        return childrenDataList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChildrenDataList(List<ChildrenData> childrenDataList) {
        this.childrenDataList = childrenDataList;
    }

    public static class ChildrenData{
        private String subContent;//子内容
        private boolean select;//是否选中

        public ChildrenData(String subContent, boolean select) {
            this.subContent = subContent;
            this.select = select;
        }

        public String getSubContent() {
            return subContent;
        }

        public void setSubContent(String subContent) {
            this.subContent = subContent;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }
}
