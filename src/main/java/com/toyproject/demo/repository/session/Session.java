package com.toyproject.demo.repository.session;

public interface Session {

    void save(String key,Long save);
    void delete(String key);
    Long getInfo(String key);
}
