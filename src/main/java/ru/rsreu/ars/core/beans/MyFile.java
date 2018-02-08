package ru.rsreu.ars.core.beans;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MyFile {
    private File file;

    public MyFile(@NotNull  File file){
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
