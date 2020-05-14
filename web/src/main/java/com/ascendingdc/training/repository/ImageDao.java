package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Image;

import java.util.List;

public interface ImageDao {
    Image save(Image image);
    int delByUserId(Long id);
    List<Image> getByEmployeeId(Long id);
}
