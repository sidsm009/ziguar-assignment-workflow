package com.ziguar.repository;

import java.io.IOException;
import java.util.List;

public interface Repository {

    public Object find(String id) throws IOException;
    public List<? extends  Object> getAllObjects() throws IOException;
    List<Integer> getAllIds() throws IOException;
    public Integer getLastId() throws IOException;
}
