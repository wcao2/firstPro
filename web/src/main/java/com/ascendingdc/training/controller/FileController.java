package com.ascendingdc.training.controller;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.model.Image;
import com.ascendingdc.training.service.EmployeeService;
import com.ascendingdc.training.service.FileService;
import com.ascendingdc.training.service.ImageService;
import com.ascendingdc.training.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value={"/files"})
public class FileController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ImageService imageService;


    @RequestMapping(value="",method= RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest req){
        logger.info("test file name: "+file.getOriginalFilename());
        HttpSession session=req.getSession();
        Long id =(Long)session.getAttribute("EmployeeId");
        Employee employee=employeeService.getEmployeeById(id);
        //upload file and generate s3Key
        String s3Key=fileService.uploadFile("ascending-weicao",file);
        Image image=new Image(file.getOriginalFilename(), s3Key, LocalDateTime.now(), employee);
        imageService.save(image);//save to local db
        Map map=new HashMap();
        map.put("id",image.getId());
        map.put("email",employee.getEmail());
        map.put("fileName",image.getFileName());
        map.put("s3Key",image.getS3Key());
        map.put("time",image.getCreateTime().toString());
        messageService.sendMessage(map.toString(),5);
        return s3Key;
    }
}












