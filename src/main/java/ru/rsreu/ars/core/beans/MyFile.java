package ru.rsreu.ars.core.beans;

import java.io.File;

public class MyFile {
    private File file;

    public MyFile(File file){
        this.file = file;
    }

    public File getFile(){
        return file;
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
