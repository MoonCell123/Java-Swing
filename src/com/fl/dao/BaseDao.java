package com.fl.dao;

import java.util.List;

public interface BaseDao<M, I> {
    void insert(M e);

    void update(M e);

    void delete(I i);

    List<M> findAll();

}
