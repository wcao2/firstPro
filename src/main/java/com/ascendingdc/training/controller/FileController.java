package com.ascendingdc.training.controller;

import com.ascendingdc.training.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/files"})
public class FileController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;
//    @Autowired
//    private MessageService messageService;

    @RequestMapping(value="",method= RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file){
        logger.info("test file name: "+file.getOriginalFilename());
        return fileService.uploadFile("ascending-weicao",file);
    }

}
