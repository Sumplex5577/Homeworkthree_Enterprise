package com.example.homeworkthree.services;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Scope("prototype")
public class CsvWriterImpl<T> implements CsvWriter<T> {
    private final CsvMapper csvMapper = new CsvMapper();
    private boolean isHeaderWritten = false;
    @Override
    public void write(String path, T data) {
        File file = new File(path);
        if (!isHeaderWritten) {
            writeHeader(file, data);
            doWrite(file, data);
        } else {
            doWrite(file, data);
        }
    }

    private void writeHeader(File file,T data) {
        CsvSchema schema = csvMapper.schemaFor(data.getClass()).withHeader();
        try {
            OutputStream outputStream = new FileOutputStream(file, true);
            csvMapper.writer().with(schema).writeValue(outputStream, null);
            isHeaderWritten = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWrite(File file, T data) {
        CsvSchema schema = csvMapper.schemaFor(data.getClass());
        try {
            OutputStream outputStream = new FileOutputStream(file, true);
            csvMapper.writer().with(schema).writeValue(outputStream, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
