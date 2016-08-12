package br.com.checker.emag.xml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;

public @Data class Result{
    
    private String url;
    private Date date;
    private String html;
    
    @Getter private List<CheckPoint> checkPoints;
    
    public Result(){ this.checkPoints =  new ArrayList<CheckPoint>(); }
    
    public void addCheckPoint(CheckPoint checkPoint) { this.checkPoints.add(checkPoint) ; }
    
}
