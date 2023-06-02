package com.example.apiuniversity;

import java.util.List;

public class University {
    private String name;
    private String country;
    private List<String> web_pages;
    private List<String> domains;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getWebPages() {
        return web_pages;
    }

    public List<String> getDomains() {
        return domains;
    }
}

