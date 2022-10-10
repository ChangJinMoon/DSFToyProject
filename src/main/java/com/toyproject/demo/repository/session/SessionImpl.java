package com.toyproject.demo.repository.session;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class SessionImpl implements Session{

    private static HashMap<String,Long> map = new HashMap<>();

    @Override
    public void save(String key, Long save) {
        if(map.containsKey(key))
            map.replace(key,save);
        else
            map.put(key,save);
    }

    @Override
    public void delete(String key) {
        map.remove(key);
    }

    @Override
    public Long getInfo(String key) {
        return (map.containsKey(key) == true ? map.get(key) : 0L);
    }
}
