package com.cse.warana.dao;

import com.cse.warana.dto.ResumesToProcessDto;

import java.util.List;

/**
 * Created by Nadeeshaan on 12/4/2014.
 */

public interface ResumesToProcessDao {
    public List<ResumesToProcessDto> getUploadedResumes(String status);
}
