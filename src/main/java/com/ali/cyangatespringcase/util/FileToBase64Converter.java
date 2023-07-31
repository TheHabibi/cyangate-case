package com.ali.cyangatespringcase.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileToBase64Converter {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/data/Sample_Employees.xlsx";
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        String base64String = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(base64String);
    }
}
