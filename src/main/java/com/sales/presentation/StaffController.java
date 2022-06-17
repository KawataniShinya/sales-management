package com.sales.presentation;

import com.sales.application.StaffService;
import com.sales.application.bean.StaffServiceBean;
import com.sales.common.DomainRuleIllegalException;
import com.sales.common.Errors;
import com.sales.domain.staff.Constant;
import com.sales.presentation.dto.StaffControllerGetStaffsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.awt.desktop.SystemEventListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String getStaffs(HttpServletRequest request, Model model, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);

        Errors errors = new Errors();

        for (ObjectError error : result.getAllErrors()) {
            if (error instanceof FieldError) {
                errors.getField().put(((FieldError) error).getField(), error.getDefaultMessage());
            }
        }
        if (!errors.getField().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.FIELD_ERROR_MESSAGES.getValue(), errors.getField());
//            return "staff-search.html";
        }
//        errors.getGlobal().add("global message test 1");
//        errors.getGlobal().add("global message test 2");
//        errors.getGlobal().add("global message test 3");
//        errors.getGlobal().add("global message test 4");

        Map<String, Object> map = new HashMap<>();
        map.put(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), 25);
        map.put(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), 4);
        map.put(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), "STF0000081");
        map.put(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), "瀬菜");
        map.put(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), "D10001");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            map.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), simpleDateFormat.parse("2022-09-02"));
            map.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(), simpleDateFormat.parse("2022-09-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StaffServiceBean staffServiceBean = null;
        try {
            staffServiceBean = this.staffService.findStaffs(map);
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }
        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
            return "staff-search.html";
        }

        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.STAFFS.getValue(), staffServiceBean.getStaffs());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.COUNT.getValue(), staffServiceBean.getCount());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), staffServiceBean.getLimitSize());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), staffServiceBean.getPage());

        return "staff-search.html";
    }
}
