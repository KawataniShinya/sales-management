package com.sales.presentation;

import com.sales.application.StaffService;
import com.sales.application.bean.StaffServiceBean;
import com.sales.common.DomainRuleIllegalException;
import com.sales.common.Errors;
import com.sales.domain.staff.Constant;
import com.sales.presentation.dto.StaffControllerGetStaffsRequest;
import org.apache.commons.lang3.time.DateUtils;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

//        param.setLimitSize(25);
//        param.setPage(4);
//        param.setUserId("STF0000081");
//        param.setUserName("瀬菜");
//        param.setDepartmentCd("D10001");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            param.setParamExpirationStart(simpleDateFormat.parse("2022-06-01"));
//            param.setParamExpirationEnd(simpleDateFormat.parse("2022-09-01"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        if (param.getPage() > 0) {
            Errors errors = new Errors();

            if (!checkBeanValidationAndSetErrorMessages(model, result, errors)) return "staff-search.html";

            Map<String, Object> map = new HashMap<>();
            setFindStaffsParameter(param, map);

            StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(model, errors, map);
            if (staffServiceBean == null) return "staff-search.html";

            setResultAsAttribute(model, staffServiceBean);
        }

        List<Map<String, String>> deptList = new ArrayList<>();
        Map<String, String> deptMap = new HashMap<>();
        deptMap.put("departmentCd", "D10001");
        deptMap.put("departmentNameJa", "人事部");
        deptList.add(deptMap);
        deptMap = new HashMap<>();
        deptMap.put("departmentCd", "D20001");
        deptMap.put("departmentNameJa", "技術開発部");
        deptList.add(deptMap);
        deptMap = new HashMap<>();
        deptMap.put("departmentCd", "D30001");
        deptMap.put("departmentNameJa", "営業部");
        deptList.add(deptMap);
        deptMap = new HashMap<>();
        deptMap.put("departmentCd", "D40001");
        deptMap.put("departmentNameJa", "総務部");
        deptList.add(deptMap);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENTS.getValue(), deptList);
        return "staff-search.html";
    }

    private void setResultAsAttribute(Model model, StaffServiceBean staffServiceBean) {
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.STAFFS.getValue(), staffServiceBean.getStaffs());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.COUNT.getValue(), staffServiceBean.getCount());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), staffServiceBean.getLimitSize());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), staffServiceBean.getPage());
    }

    private StaffServiceBean getStaffServiceBeanOrSetErrorMessages(Model model, Errors errors, Map<String, Object> map) {
        StaffServiceBean staffServiceBean = null;
        try {
            staffServiceBean = this.staffService.findStaffs(map);
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }
        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
        }
        return staffServiceBean;
    }

    private void setFindStaffsParameter(StaffControllerGetStaffsRequest param, Map<String, Object> map) {
        map.put(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), param.getLimitSize());
        map.put(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), param.getPage());
        map.put(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), param.getUserId());
        map.put(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), param.getUserName());
        map.put(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), param.getDepartmentCd());
        if (param.getParamExpirationStart() != null) {
            map.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), DateUtils.truncate(param.getParamExpirationStart(), Calendar.DAY_OF_MONTH));
        }
        if (param.getParamExpirationStart() != null) {
            map.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(), DateUtils.truncate(param.getParamExpirationStart(), Calendar.DAY_OF_MONTH));
        }
    }

    private boolean checkBeanValidationAndSetErrorMessages(Model model, BindingResult result, Errors errors) {
        boolean isPassed = true;
        for (ObjectError error : result.getAllErrors()) {
            if (error instanceof FieldError) {
                errors.getField().put(((FieldError) error).getField(), error.getDefaultMessage());
            }
        }
        if (!errors.getField().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.FIELD_ERROR_MESSAGES.getValue(), errors.getField());
            isPassed = false;
        }
        return isPassed;
    }
}
