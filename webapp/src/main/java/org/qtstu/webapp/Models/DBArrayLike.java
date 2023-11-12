package org.qtstu.webapp.Models;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class DBArrayLike<Type extends UserRecord, VideoRecord>{
    ArrayList<Long> index = new ArrayList<Long>();
    Integer maxCacheCap = 10;
    Queue<element<Type>> cachedElements;
    private record element<Type>(Integer index,Type object){}
    RandomAccessFile f;

    public DBArrayLike(String path){
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            f = new RandomAccessFile(file,"rws");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private element<Type> checkCache(Integer i){
        for (element<Type> iterator:cachedElements) {
            if(iterator.index.equals(i)){
                return iterator;
            }
        }
        return new element<Type>(-1,null);
    }

    private void addCache(element<Type> toAdd){
        if(checkCache(toAdd.index).index.equals(-1)){
            cachedElements.add(toAdd);
            if(cachedElements.size() > maxCacheCap){
                cachedElements.remove();
            }
        }
    }

    private element<Type> read(Integer i){
        element<Type> element = new element<Type>(-1,null);

        try {
            for (int ii = 0; ii < index.size(); ii++) {
                f.seek(index.get(ii));
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return element;
    }

    private void write(element<Type> toWrite){

    }



    public Type get(Integer i) {
        element<Type> obj = checkCache(i);
        if (obj.index.equals(-1)){
            return read(i).object;
        }else {
            return obj.object;
        }
    }

    //TODO
    public Type set(Integer i, Type type) {
        return null;
    }

    public int size() {
        return index.size();
    }

    //TODO
    public boolean add(Type type) {
        return false;
    }

    //TODO
    public boolean remove(Type o) {
        return false;
    }

    //TODO
    public Type remove(Integer i) {
        return null;
    }

    //TODO
    public boolean addAll(Collection<? extends Type> collection) {
        return false;
    }
}
