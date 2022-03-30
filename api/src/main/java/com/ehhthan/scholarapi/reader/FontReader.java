package com.ehhthan.scholarapi.reader;

public interface FontReader<T> {
    int width(T read);

    int height(T read);
}
