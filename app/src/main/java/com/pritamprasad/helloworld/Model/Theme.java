package com.pritamprasad.helloworld.Model;

/**
 * Created by jarvis on 1/28/17.
 */

public class Theme {

    private int id = -1;
    private String themeName;
    private String themeDesc;

    public Theme(){

    }

    public Theme(String name, String desc)
    {
        this.themeName = name;
        this.themeDesc = desc;
    }

    public Theme(int id, String name, String desc)
    {
        this.id = id;
        this.themeName = name;
        this.themeDesc = desc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeDesc() {
        return themeDesc;
    }

    public void setThemeDesc(String themeDesc) {
        this.themeDesc = themeDesc;
    }
}
