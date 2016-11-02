package jp.suesan.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by suesan on 2016/10/29.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestResult {
    public String startDate;
    public String endDate;
    public List<Result> results;
}


