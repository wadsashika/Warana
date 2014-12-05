package com.cse.warana.service;

import com.cse.warana.dto.ResumesToProcessDto;

import java.util.List;

/**
 * Created by Nadeeshaan on 12/4/2014.
 */
public interface ResumesToProcessService {
    public List<ResumesToProcessDto> getResumesToProcess(String status);
    public int uploadedResumeStatusUpdate(String fileName, String status);
}
