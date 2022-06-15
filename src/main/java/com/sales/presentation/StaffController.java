package com.sales.presentation;

import com.sales.application.StaffService;
import com.sales.application.bean.StaffServiceBean;
import com.sales.domain.staff.Constant;
import com.sales.presentation.dto.StaffControllerGetStaffsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String top(HttpServletRequest request, Model model) {
        CommonDisplay.setHeaderParameter(request, model);
        return "staff.html";
    }

    @RequestMapping(value = "/staff/search", method = RequestMethod.GET)
    public String getStaffs(HttpServletRequest request, Model model, @ModelAttribute StaffControllerGetStaffsRequest param) {
        CommonDisplay.setHeaderParameter(request, model);

        Map<String, Object> map = new HashMap<>();
        map.put(Constant.API_FIELD_NAME_STAFF.LIMIT_SIZE.getValue(), 20);
        map.put(Constant.API_FIELD_NAME_STAFF.PAGE.getValue(), 7);
        StaffServiceBean staffServiceBean = this.staffService.findStaffs(map);
        model.addAttribute(Constant.API_FIELD_NAME_STAFF.STAFFS.getValue(), staffServiceBean.getStaffs());
        model.addAttribute(Constant.API_FIELD_NAME_STAFF.COUNT.getValue(), staffServiceBean.getCount());
        model.addAttribute(Constant.API_FIELD_NAME_STAFF.LIMIT_SIZE.getValue(), staffServiceBean.getLimitSize());
        model.addAttribute(Constant.API_FIELD_NAME_STAFF.PAGE.getValue(), staffServiceBean.getPage());
        return "staff-search.html";
    }
}
