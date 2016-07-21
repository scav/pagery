package com.devbugger.pagery.transform.pagery;

public interface TransformPageryPostPage<T, S> {

    T transform(T t, S s);
}
