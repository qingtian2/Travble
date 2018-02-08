package com.travbao.news.travbao.bean;

/**
 * 城市排序
 */
public class CitySortModel {

    private String name;//显示的数据
    private String sortLetters;//显示数据拼音的首字母

    public CitySortModel() {
    }

    public CitySortModel(String name, String sortLetters) {
        this.name = name;
        this.sortLetters = sortLetters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
