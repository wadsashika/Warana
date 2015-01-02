package com.cse.warana.controller;

import com.cse.warana.service.DocUploadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Nadeeshaan on 11/21/2014.
 */

@Controller
public class DocUploadController {
    private static Logger LOG = LoggerFactory.getLogger(DocUploadController.class);

    @Autowired
    @Qualifier("storeUploadedResumeService")
    DocUploadService docUploadService;

    @Value("${warana.resources.root}")
    private String root;

    @Value("${UPLOADS.PATH}")
    private String uploadsPath;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView uploadDocument() {
        String UPLOAD_VIEW = "upload-doc";

        LOG.info("Starting document upload view");

        ModelAndView model = new ModelAndView();
        model.setViewName(UPLOAD_VIEW);

        return model;
    }

    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    public
    @ResponseBody
    String uploadFilesToServer(MultipartHttpServletRequest request) {

        String baseUploadDirectory = root + uploadsPath;
        ArrayList<String> fileNamesList = new ArrayList<>();
        ArrayList<String> missedFiles = new ArrayList<>();
        HashMap<Object, Object> returnJson = new HashMap<>();
        boolean success = true;
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonString = "";

        Iterator<String> itr = request.getFileNames();
        MultipartFile multipartFile;
        String filePath = "";

        while (itr.hasNext()) {
            multipartFile = request.getFile(itr.next());
            filePath = multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(baseUploadDirectory + filePath));
                fileNamesList.add(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                missedFiles.add(filePath);
                success = false;
                continue;
            }
        }

        docUploadService.storeDocData(fileNamesList);
        if (success) {
            returnJson.put("status", "true");
        } else {
            returnJson.put("status", "false");
        }
        returnJson.put("files", missedFiles);
        jsonString = gson.toJson(returnJson);
        System.out.println(jsonString);
        return jsonString;
    }
}
