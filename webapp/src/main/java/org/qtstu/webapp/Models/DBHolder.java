package org.qtstu.webapp.Models;

import org.qtstu.webapp.Hack.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class DBHolder {
    private boolean switcher = false;
    private DBI db;
    private ApplicationContextHolder holder;

    @Autowired
    public DBHolder(ApplicationContextHolder context){
        holder = context;
        setDb(ApplicationContextHolder.getContext().getBean(DB.class));
        switcher = false;
    }

    public void switchDB(){
        switcher = !switcher;
    }

    public DBI getDb() {
        if(switcher){
            setDb(ApplicationContextHolder.getContext().getBean(DBHiber.class));
            System.out.println("Hiber db");
        }else {
            setDb(ApplicationContextHolder.getContext().getBean(DB.class));
            System.out.println("JDBC template db");
        }
        return db;
    }

    private void setDb(DBI db) {
        this.db = db;
    }
}
