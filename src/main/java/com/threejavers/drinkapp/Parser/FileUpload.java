package com.threejavers.drinkapp.Parser;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
@Slf4j
public class FileUpload{

    private static String SETTINGS_FILE = "settings.properties";
    private static String UPLOAD_KEY = "Upload.Path";

    public File uploadFile(Part filePart) throws IOException {
        String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        File file = new File(getUploadFilesPath() + filename);
        Files.deleteIfExists(file.toPath());

        InputStream inputStream = filePart.getInputStream();
        Files.copy(inputStream, file.toPath());
        log.info("Json file uploaded");
        return file;
    }

    public String getUploadFilesPath() throws IOException {
        Properties settings = new Properties();
        settings.load(Thread.currentThread().getContextClassLoader().getResource(SETTINGS_FILE).openStream());
        log.info("Getting json file path");
        return settings.getProperty(UPLOAD_KEY);
    }
}
