package com.example.appgestiondesconges;

import java.awt.*;
import java.sql.Date;

public class Leave1 {
    int idl;
    String type,idemp,state;
    String dd,df;
    long duree;
    String service,file;
    //Button b;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }



    public Leave1(int idl, String type, String idemp, String state, String dd, String df, long duree, String service, String file) {
        this.idl = idl;
        this.type = type;
        this.idemp = idemp;
        this.state = state;
        this.dd = dd;
        this.df = df;
        this.duree = duree;
        this.service = service;
        this.file = file;
        //this.b=new Button("File");
    }

    /*public Button getB() {
        return b;
    }

    public void setB(Button b) {
        this.b = b;
    }*/

    public int getIdl() {
        return idl;
    }

    public String getType() {
        return type;
    }

    public String getIdemp() {
        return idemp;
    }

    public String getState() {
        return state;
    }

    public String getDd() {
        return dd;
    }

    public String  getDf() {
        return df;
    }

    public long getDuree() {
        return duree;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdemp(String idemp) {
        this.idemp = idemp;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public void setDuree(long duree) {
        this.duree = duree;
    }
}
