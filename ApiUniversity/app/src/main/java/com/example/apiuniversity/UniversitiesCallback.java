package com.example.apiuniversity;

import java.util.List;

public interface UniversitiesCallback {
    void onSuccess(List<University> universities);
    void onError(String message);

    void onError(Throwable error);
}