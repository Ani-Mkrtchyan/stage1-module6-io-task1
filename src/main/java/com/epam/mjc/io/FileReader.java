package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        return new Profile();
    }

    private String[] readFile(File file) {
        String[] finalOutput = null;
        String absolutePath = file.getAbsolutePath();

        try (FileInputStream inputStream = new FileInputStream(absolutePath);) {
            finalOutput = new String[4];

            int c, count = 0;
            while ((c = inputStream.read()) != -1) {
                if (c == 10) {
                    count++;
                    continue;
                }

                finalOutput[count] = finalOutput[count] + (char) c;

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return finalOutput;
    }
}