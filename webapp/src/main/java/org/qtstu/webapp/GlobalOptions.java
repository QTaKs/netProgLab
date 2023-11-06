package org.qtstu.webapp;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class GlobalOptions {
    public static Long lastUserId = 0L;
    public static Long lastVideoId = 0L;
    private static Random random = new Random();
    public static String randomName(String prefix){
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
    public static Duration randomDuration(){
        return java.time.Duration.ofSeconds(random.nextInt(10,6000));
    }
    public static int randomInteger(int from,int to){
        return random.nextInt(from,to+1);
    }
}
