package com.fastie4.etsyclient.domain.mapper;

import java.util.List;

public interface Mapper<T,R> {
    T transformR(R from);
    R transformL(T from);
    List<R> transformListL(List<T> from);
    List<T> transformListR(List<R> from);
}