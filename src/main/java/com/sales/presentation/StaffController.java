package com.sales.presentation;

import com.sales.application.DepartmentService;
import com.sales.application.StaffService;
import com.sales.application.bean.DepartmentServiceBean;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
public class StaffController {
    private final StaffService staffService;
    private final DepartmentService departmentService;

    @Autowired
    public StaffController(StaffService staffService, DepartmentService departmentService) {
        this.staffService = staffService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String top(HttpServletRequest request, Model model) {
        CommonDisplay.setHeaderParameter(request, model);
        return "staff.html";
    }

    @RequestMapping(value = "/staff/search", method = RequestMethod.GET)
    public String getStaffs(HttpServletRequest request, Model model, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        setRequestedParam(model, param);

        DepartmentServiceBean departmentServiceBean = this.departmentService.getAllDepartments();
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENTS.getValue(), departmentServiceBean.getDepartments());

        if (param.getPage() > 0) {
            Errors errors = new Errors();

            if (!checkBeanValidationAndSetErrorMessages(model, result, errors)) return "staff-search.html";

            Map<String, Object> map = new HashMap<>();
            setFindStaffsParameter(param, map);

            StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(model, errors, map);
            if (staffServiceBean == null) return "staff-search.html";

            setResultAsAttribute(model, staffServiceBean);
        }

        return "staff-search.html";
    }

    private void setRequestedParam(Model model, StaffControllerGetStaffsRequest param) {
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), param.getUserId());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), param.getUserName());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), param.getDepartmentCd());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (param.getParamExpirationStart() != null) {
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), dateFormat.format(param.getParamExpirationStart()));
        }
        if (param.getParamExpirationEnd() != null) {
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(), dateFormat.format(param.getParamExpirationEnd()));
        }
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
        this.setFindStaffsParamOnlyUserId(param, map);
        map.put(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), param.getUserName());
        map.put(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), param.getDepartmentCd());
        if (param.getParamExpirationStart() != null) {
            map.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), DateUtils.truncate(param.getParamExpirationStart(), Calendar.DAY_OF_MONTH));
        }
        if (param.getParamExpirationEnd() != null) {
            map.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(), DateUtils.truncate(param.getParamExpirationEnd(), Calendar.DAY_OF_MONTH));
        }
    }

    private void setFindStaffsParamOnlyUserId(StaffControllerGetStaffsRequest param, Map<String, Object> map) {
        map.put(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), param.getLimitSize());
        map.put(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), param.getPage());
        map.put(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), param.getUserId());
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

    @RequestMapping(value = "/staff/{pathUserId}", method = RequestMethod.GET)
    public String getStaffsById(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);

        Errors errors = new Errors();

        if (!checkBeanValidationAndSetErrorMessages(model, result, errors)) return "staff-search.html";

        Map<String, Object> map = new HashMap<>();
        param.setUserId(pathUserId);
        setFindStaffsParamOnlyUserId(param, map);

        StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(model, errors, map);
        if (staffServiceBean == null) return "staff-search.html";

        setResultAsAttribute(model, staffServiceBean);

        if (staffServiceBean.getCount() > 0) {
            if (param.getParamExpirationStart() == null) {
                model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staffServiceBean.getStaffs().get(0));
            } else {
                staffServiceBean.getStaffs().forEach(staff -> {
                    if (staff.getExpirationStart().equals(param.getParamExpirationStart())) {
                        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staff);
                    }
                });
            }
        }

        return "staff-detail.html";
    }
}
