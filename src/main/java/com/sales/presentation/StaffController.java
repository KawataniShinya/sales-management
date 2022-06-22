package com.sales.presentation;

import com.sales.application.DepartmentService;
import com.sales.application.GenericCodeService;
import com.sales.application.StaffService;
import com.sales.application.bean.DepartmentServiceBean;
import com.sales.application.bean.GenericCodeServiceBean;
import com.sales.application.bean.StaffServiceBean;
import com.sales.common.DomainRuleIllegalException;
import com.sales.common.Errors;
import com.sales.domain.staff.Constant;
import com.sales.domain.staff.Staff;
import com.sales.presentation.dto.StaffControllerDetailRequest;
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
    private final Staff staff;
    private final StaffService staffService;
    private final DepartmentService departmentService;
    private final GenericCodeService genericCodeService;

    @Autowired
    public StaffController(Staff staff, StaffService staffService, DepartmentService departmentService, GenericCodeService genericCodeService) {
        this.staff = staff;
        this.staffService = staffService;
        this.departmentService = departmentService;
        this.genericCodeService = genericCodeService;
    }

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String top(HttpServletRequest request, Model model) {
        CommonDisplay.setHeaderParameter(request, model);
        return "staff.html";
    }

    @RequestMapping(value = "/staff/search", method = RequestMethod.GET)
    public String getStaffs(HttpServletRequest request, Model model, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        setRequestedSearchParam(model, param, "");

        setDepartmentToModel(model);

        if (param.getPage() > 0) {
            Errors errors = new Errors();

            if (!checkBeanValidationAndSetErrorMessages(model, result, errors)) return "staff-search.html";

            Map<String, Object> findStaffsParamMap = new HashMap<>();
            setFindStaffsParameter(param, findStaffsParamMap);

            StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(model, errors, findStaffsParamMap);
            if (staffServiceBean == null) return "staff-search.html";

            setResultAsAttribute(model, staffServiceBean);
        }

        return "staff-search.html";
    }

    private void setRequestedSearchParam(Model model, StaffControllerGetStaffsRequest param, String pathParamUserId) {
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), param.getUserId());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), param.getUserName());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), param.getDepartmentCd());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), pathParamUserId);

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

    private StaffServiceBean getStaffServiceBeanOrSetErrorMessages(Model model, Errors errors, Map<String, Object> findStaffsParamMap) {
        StaffServiceBean staffServiceBean = null;
        try {
            staffServiceBean = this.staffService.findStaffs(findStaffsParamMap);
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }
        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
        }
        return staffServiceBean;
    }

    private void setFindStaffsParameter(StaffControllerGetStaffsRequest param, Map<String, Object> findStaffsParamMap) {
        this.setFindStaffsParamUserId(param.getLimitSize(), param.getPage(), param.getUserId(), findStaffsParamMap);
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), param.getUserName());
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), param.getDepartmentCd());
        if (param.getParamExpirationStart() != null) {
            findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), DateUtils.truncate(param.getParamExpirationStart(), Calendar.DAY_OF_MONTH));
        }
        if (param.getParamExpirationEnd() != null) {
            findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(), DateUtils.truncate(param.getParamExpirationEnd(), Calendar.DAY_OF_MONTH));
        }
    }

    private void setFindStaffsParamUserId(int limitSize, int page, String userId, Map<String, Object> findStaffsParamMap) {
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), limitSize);
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), page);
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), userId);
    }


    private boolean checkBeanValidationAndSetErrorMessages(Model model, BindingResult result, Errors errors) {
        boolean isPassed = true;
        List<Map<String, String>> messageTransformList = getAlternativeErrorMessages();

        for (ObjectError error : result.getAllErrors()) {
            if (error instanceof FieldError) {
                String message = error.getDefaultMessage();
                for (Map<String, String> map : messageTransformList) {
                    if (error.getDefaultMessage().equals(map.get("before"))) {
                        message = map.get("after");
                    }
                }
                errors.getField().put(((FieldError) error).getField(), message);
            }
        }
        if (!errors.getField().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.FIELD_ERROR_MESSAGES.getValue(), errors.getField());
            isPassed = false;
        }
        return isPassed;
    }

    private List<Map<String, String>> getAlternativeErrorMessages() {
        List<Map<String, String>> messageTransformList = new ArrayList<>();
        Map<String, String> halfWidthAlphanumeric = new HashMap<>();
        halfWidthAlphanumeric.put("before", "正規表現 \"^[a-zA-Z0-9]*$\" にマッチさせてください");
        halfWidthAlphanumeric.put("after", "半角英数字で入力してください");
        messageTransformList.add(halfWidthAlphanumeric);
        return messageTransformList;
    }

    @RequestMapping(value = "/staff/{pathUserId}", method = RequestMethod.GET)
    public String getStaffsById(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        setRequestedSearchParam(model, param, pathUserId);

        Errors errors = new Errors();

        if (!checkBeanValidationAndSetErrorMessages(model, result, errors)) return "staff-search.html";

        Map<String, Object> findStaffsParamMap = new HashMap<>();
        setFindStaffsParamUserId(param.getLimitSize(), param.getPage(), pathUserId, findStaffsParamMap);

        StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(model, errors, findStaffsParamMap);
        if (staffServiceBean == null) return "staff-search.html";

        setResultAsAttribute(model, staffServiceBean);

        if (staffServiceBean.getCount() > 0) {
            setDepartmentToModel(model);
            setGenericCodeToModel(model);

            if (param.getParamExpirationStart() == null) {
                model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staffServiceBean.getStaffs().get(0));
                model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), staffServiceBean.getStaffs().get(0).getExpirationStart());
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

    private void setGenericCodeToModel(Model model) {
        GenericCodeServiceBean genericCodeServiceBean = this.genericCodeService.getGenericCodeListInStaff();
        model.addAttribute(com.sales.domain.genericcode.Constant.API_FIELD_NAME_GENERIC_CD.GENDERS.getValue(), genericCodeServiceBean.getGenders());
        model.addAttribute(com.sales.domain.genericcode.Constant.API_FIELD_NAME_GENERIC_CD.BLOOD_TYPES.getValue(), genericCodeServiceBean.getBloodTypes());
        model.addAttribute(com.sales.domain.genericcode.Constant.API_FIELD_NAME_GENERIC_CD.ADDRESS_PREFECTURES.getValue(), genericCodeServiceBean.getAddressPrefectures());
    }

    private void setDepartmentToModel(Model model) {
        DepartmentServiceBean departmentServiceBean = this.departmentService.getAllDepartments();
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENTS.getValue(), departmentServiceBean.getDepartments());
    }

    @RequestMapping(value = "/staff/{pathUserId}/add", method = RequestMethod.GET)
    public String addStaffDetailAddInit(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        setRequestedSearchParam(model, param, pathUserId);

        Errors errors = new Errors();

        if (!checkBeanValidationAndSetErrorMessages(model, result, errors)) return "staff-add.html";

        Map<String, Object> findStaffsParamMap = new HashMap<>();
        setFindStaffsParamUserId(param.getLimitSize(), param.getPage(), pathUserId, findStaffsParamMap);

        StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(model, errors, findStaffsParamMap);
        if (staffServiceBean == null) return "staff-add.html";

        setResultAsAttribute(model, staffServiceBean);

        if (staffServiceBean.getCount() > 0) {
            setDepartmentToModel(model);
            setGenericCodeToModel(model);

            staffServiceBean.getStaffs().forEach(staff -> {
                if (staff.getExpirationStart().equals(param.getParamExpirationStart())) {
                    model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staff);
                }
            });
        }

        model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
        return "staff-add.html";
    }

    @RequestMapping(value = "/staff/{pathUserId}/add", method = RequestMethod.POST)
    public String addStaffDetailAddExecute(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @ModelAttribute @Validated StaffControllerDetailRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        setRequestedStaffParam(model, param);

        Errors errors = new Errors();

        if (!checkBeanValidationAndSetErrorMessages(model, result, errors)) return "staff-add.html";

        Map<String, Object> findStaffsParamMap = new HashMap<>();
        setFindStaffsParamUserId(param.getLimitSize(), param.getPage(), param.getUserId(), findStaffsParamMap);

        StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(model, errors, findStaffsParamMap);
        if (staffServiceBean == null) return "staff-add.html";

        setResultAsAttribute(model, staffServiceBean);

        if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_ADD_CONFIRM.getValue())) {

            Map<String, Object> addStaffsParamMap = new HashMap<>();
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.USER_ID.getValue(), param.getUserId());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.FAMILY_NAME.getValue(), param.getFamilyName());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.FIRST_NAME.getValue(), param.getFirstName());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue(), param.getDepartmentCd());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.GENDER_CD.getValue(), param.getGenderCd());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.BIRTHDATE.getValue(), param.getBirthdate());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.BLOOD_TYPE_CD.getValue(), param.getBloodTypeCd());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.ADDRESS_PREFECTURE_CD.getValue(), param.getAddressPrefectureCd());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.ADDRESS_MUNICIPALITY.getValue(), param.getAddressMunicipality());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.PRIVATE_TEL_NO.getValue(), param.getPrivateTelNo());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.PRIVATE_EMAIL.getValue(), param.getPrivateEmail());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.WORKPLACE_TEL_NO.getValue(), param.getWorkplaceTelNo());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.WORKPLACE_EMAIL.getValue(), param.getWorkplaceEmail());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.EXPIRATION_START.getValue(), new java.sql.Date(param.getExpirationStart().getTime()));
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.EXPIRATION_END.getValue(), new java.sql.Date(param.getExpirationEnd().getTime()));
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.INSERT_USER.getValue(), param.getUserId());
            addStaffsParamMap.put(Constant.API_FIELD_NAME_STAFF.UPDATE_USER.getValue(), param.getUserId());
            try {
                // TODO add domein check
                this.staffService.checkAddStaff(addStaffsParamMap);
            } catch (DomainRuleIllegalException e) {
                errors.getGlobal().addAll(e.getMessages());
            }

            if (!errors.getGlobal().isEmpty()) {
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                        com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
            } else {
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                        com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_CONFIRM.getValue());
            }
        } else if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_ADD_CANCEL.getValue())) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
        }

        return "staff-add.html";
    }

    private void setRequestedStaffParam(Model model, StaffControllerDetailRequest param) {
        Staff staff = this.staff.createStaff();

        staff.setUserId(param.getUserId());
        staff.setFamilyName(param.getFamilyName());
        staff.setFirstName(param.getFirstName());
        staff.setDepartmentCd(param.getDepartmentCd());
        staff.setGenderCd(param.getGenderCd());
        staff.setBirthdate(new java.sql.Date(param.getBirthdate().getTime()));
        staff.setBloodTypeCd(param.getBloodTypeCd());
        staff.setAddressPrefectureCd(param.getAddressPrefectureCd());
        staff.setAddressMunicipality(param.getAddressMunicipality());
        staff.setPrivateTelNo(param.getPrivateTelNo());
        staff.setPrivateEmail(param.getPrivateEmail());
        staff.setWorkplaceTelNo(param.getWorkplaceTelNo());
        staff.setWorkplaceEmail(param.getWorkplaceEmail());
        staff.setExpirationStart(new java.sql.Date(param.getExpirationStart().getTime()));
        staff.setExpirationEnd(new java.sql.Date(param.getExpirationEnd().getTime()));

        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staff);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), param.getUserId());
    }
}
