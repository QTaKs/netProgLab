package org.qtstu.webapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

@Component
public class GlobalOptions {
    private Random random = new Random();
    public String randomName(String prefix){
        StringBuilder str = new StringBuilder(prefix);
        for(int i=0;i < random.nextInt(4,10);i++){
            if(random.nextBoolean()){
                str.append((char)(random.nextInt(26) + 'a'));
            }else{
                str.append((char)(random.nextInt(26) + 'A'));
            }
        }
        return str.toString();
    }
    public Duration randomDuration(){
        return java.time.Duration.ofSeconds(random.nextInt(10,6000));
    }
    public int randomInteger(int from,int to){
        return random.nextInt(from,to+1);
    }
}
