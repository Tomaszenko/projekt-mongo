package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Tomek on 04.06.2017.
 */

@Service
//@PropertySource(value = "classpath:/storage.properties")
public class FileServiceImpl implements FileService {

//    @Autowired
//    private Environment env;

//    private final Path storageDir = Paths.get(URI.create(env.getProperty("storage")));

    @Override
    public File saveFile(MultipartFile multipartFile) {
        return null;
    }

//    private String findPossibleLocation() {
//        File f = new File(storageDir.toString()+);
//        if(f.exists() && !f.isDirectory()) {
//            // do something
//        }
//    }

//    private String uploadFileHandler(MultipartFile file) {
//
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//
//                MultipartFile file = article.getFileData();  //Will return CommonsMultipartFile
//                String filePath = "C:/MyApplication/UploadedImages/" + file.getOriginalFilename(); //Please note that I am going to remove hardcoaded path to get it from resource/property file
//                File dest = new File(filePath);
//                file.transferTo(dest);
//
//                // Creating the directory to store file
//                String rootPath = System.getProperty("catalina.home");
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if (!dir.exists())
//                    dir.mkdirs();
//
//                // Create the file on server
//                File serverFile = new File(dir.getAbsolutePath()
//                        + File.separator + name);
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(serverFile));
//                stream.write(bytes);
//                stream.close();
//
////                logger.info("Server File Location="
////                        + serverFile.getAbsolutePath());
//
//                return "You successfully uploaded file=" + name;
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name
//                    + " because the file was empty.";
//        }
//    }
}