package com.cse.warana.dao;

import com.cse.warana.dto.DataToGenerateMapDTO;
import com.cse.warana.dto.ResumesToAnalyseDto;

import java.util.List;

/**
 * Created by Nadeeshaan on 12/5/2014.
 */
public interface AnalyzeResumeDao {

    public List<ResumesToAnalyseDto> getResumesToBeAnalyzed();

    public List<DataToGenerateMapDTO> getDataForGraph(String[] idList);

    public List<String> getTechnologyListOfCandidate(Long id);
}
