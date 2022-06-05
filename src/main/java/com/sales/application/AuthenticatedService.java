package com.sales.application;

import javax.servlet.http.HttpSession;

public interface AuthenticatedService {
    void setSessionAttribute(HttpSession session);
}
