package com.cse.warana.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Nadeeshaan on 11/21/2014.
 */

@Controller
public class DocUploadController {
    private static Logger LOG = LoggerFactory.getLogger(DocUploadController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView uploadDocument(){
        String UPLOAD_VIEW = "upload-doc";

        LOG.info("Starting document upload view");

        ModelAndView model = new ModelAndView();
        model.setViewName(UPLOAD_VIEW);

        return model;
    }

    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    public @ResponseBody String uploadFilesToServer(MultipartHttpServletRequest request){
        Iterator<String> itr = request.getFileNames();
        MultipartFile multipartFile;
        String filePath = "";

        while (itr.hasNext()){
            multipartFile = request.getFile(itr.next());
            filePath = multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File("E:\\"+filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Done";
    }
}
