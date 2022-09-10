package com.example.homeworkthree.services;

public interface CsvWriter<T> {
    void write(String path, T data);
}
