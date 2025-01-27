package com.epam.mjc.io;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;


public class FileReader {

    public Profile getDataFromFile(File file) {

        return createProfile(file);
    }

    private String[] readFile (File file) {
        String[] finalOutput = null;
        String absolutePath = file.getAbsolutePath();

        try (FileInputStream inputStream = new FileInputStream(absolutePath);) {
            finalOutput = new String[4];

            int c =0;
            int count = 0;
            while ((c = inputStream.read()) != -1) {
                if (c == 10) {
                    count++;
                    continue;
                }

                finalOutput[count] = finalOutput[count] + (char)c;

            }

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return finalOutput;
    }

    private @NotNull HashMap<String, String> parseKeyValue (String @NotNull [] inputString)  {
        HashMap<String, String> finalOutput = new HashMap<>();

        finalOutput.put("Name", inputString[0].substring(inputString[0].indexOf(':') + 2));
        finalOutput.put("Age", inputString[1].substring(inputString[1].indexOf(':') + 2));
        finalOutput.put("Email", inputString[2].substring(inputString[2].indexOf(':') + 2));
        finalOutput.put("Phone", inputString[3].substring(inputString[3].indexOf(':') + 2));

        return finalOutput;
    }

    private @NotNull Profile createProfile (File file) {
        HashMap<String, String> map = parseKeyValue(readFile(file));
        return new Profile (map.get("Name"), Integer.parseInt(map.get("Age")),
                map.get("Email"), Long.parseLong(map.get("Phone")));
    }
}