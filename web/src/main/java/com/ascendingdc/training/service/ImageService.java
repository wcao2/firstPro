package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Image;
import com.ascendingdc.training.repository.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageDao imageDao;

    public Image save(Image image){
        return imageDao.save(image);
    }

    public int delByEmployeeId(Long id){
        return imageDao.delByUserId(id);
    }

    public List<Image> getByEmployeeId(Long id){
        return imageDao.getByEmployeeId(id);
    }
}
