package com.andsf.sampleproject;

import java.util.List;

public class BookResponse
{
    private List<Results> results;

    public List<Results> getResults ()
    {
        return results;
    }

    public void setResults (List<Results> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+"]";
    }
}