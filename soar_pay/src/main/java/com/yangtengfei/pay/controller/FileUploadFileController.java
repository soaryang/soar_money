package com.yangtengfei.pay.controller;

import com.yangtengfei.pay.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Calendar;

@Slf4j
@Controller
@RequestMapping("/cuiyu123456789/file")
public class FileUploadFileController {


    @Value("${uploadfilePathRoot}")
    private String filePath;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        Calendar calendarTemp = Calendar.getInstance();
        FileOutputStream out = null;
        String date = DateUtil.calendarToString(calendarTemp, DateUtil.YYYY_MM_DD);
        if (!file.isEmpty()) {
            try {
                String path = filePath + File.separator + date;
                File targetFile = new File(path);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                String fileName = file.getOriginalFilename();
                //File dest = new File(filePath + fileName);

                int mark = fileName.lastIndexOf(".");
                String suffix = fileName.substring(mark);
                String name = fileName.substring(fileName.lastIndexOf("\\") + 1, mark)
                        + "-" + DateUtil.calendarToString(calendarTemp, DateUtil.YYYY_MM_DD_HH_MM_SS_2);

                out = new FileOutputStream(filePath + File.separator + date + File.separator + name + suffix);
                out.write(file.getBytes());
                //file.transferTo(dest);
                //FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } finally {
                if (out != null) {
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
}
