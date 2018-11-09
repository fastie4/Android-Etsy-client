package com.fastie4.etsyclient.domain.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<T,R> implements Mapper<T,R> {
    @Override
    public List<R> transformListL(List<T> from) {
        List<R> list = new ArrayList<>();
        for (T t : from) {
            list.add(transformL(t));
        }
        return list;
    }

    @Override
    public List<T> transformListR(List<R> from) {
        List<T> list = new ArrayList<>();
        for (R t : from) {
            list.add(transformR(t));
        }
        return list;
    }
}
