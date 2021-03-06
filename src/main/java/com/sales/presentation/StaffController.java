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
import com.sales.presentation.dto.StaffControllerGetStaffsResponse;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
public class StaffController {
    private final StaffService staffService;
    private final DepartmentService departmentService;
    private final GenericCodeService genericCodeService;

    @Autowired
    public StaffController(StaffService staffService, DepartmentService departmentService, GenericCodeService genericCodeService) {
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

        if (com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SEARCH_EXECUTE.getValue().equals(param.getSubmitType())) {
            Errors errors = new Errors();

            if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) return "staff-search.html";

            getStaffListForSeachResultAndSetErrorsToModel(param.getLimitSize(), param.getPage(), param.getUserId(), param.getUserName(), param.getDepartmentCd(), param.getParamExpirationStart(), param.getParamExpirationEnd(), model, errors);
        }

        return "staff-search.html";
    }

    private StaffServiceBean getStaffListForSeachResultAndSetErrorsToModel(int limitSize, int page, String userId, String userName, String departmentCd, Date paramExirationStart, Date paramExpirationEnd, Model model, Errors errors) {
        StaffServiceBean staffServiceBean = getStaffListForSeachResult(limitSize, page, userId, userName, departmentCd, paramExirationStart, paramExpirationEnd, errors);
        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
        }
        if (staffServiceBean != null) setStaffListAsAttribute(model, staffServiceBean);
        return staffServiceBean;
    }

    private StaffServiceBean getStaffListForSeachResult(int limitSize, int page, String userId, String userName, String departmentCd, Date paramExirationStart, Date paramExpirationEnd, Errors errors) {
        Map<String, Object> findStaffsParamMap = new HashMap<>();
        setFindStaffsParameter(limitSize, page, userId, userName, departmentCd, paramExirationStart, paramExpirationEnd, findStaffsParamMap);
        StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(errors, findStaffsParamMap);
        return staffServiceBean;
    }

    private StaffServiceBean getStaffListByIdAndSetErrorsToModel(int limitSize, int page, String userId, Model model, Errors errors) {
        Map<String, Object> findStaffsParamMap = new HashMap<>();
        setFindStaffsParamUserId(limitSize, page, userId, findStaffsParamMap);
        StaffServiceBean staffServiceBean = getStaffServiceBeanOrSetErrorMessages(errors, findStaffsParamMap);
        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
        }
        if (staffServiceBean != null) setStaffListAsAttribute(model, staffServiceBean);
        return staffServiceBean;
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

    private void setStaffListAsAttribute(Model model, StaffServiceBean staffServiceBean) {
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.STAFFS.getValue(), staffServiceBean.getStaffs());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.COUNT.getValue(), staffServiceBean.getCount());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), staffServiceBean.getLimitSize());
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), staffServiceBean.getPage());
    }

    private StaffServiceBean getStaffServiceBeanOrSetErrorMessages(Errors errors, Map<String, Object> findStaffsParamMap) {
        StaffServiceBean staffServiceBean = null;
        try {
            staffServiceBean = this.staffService.findStaffs(findStaffsParamMap);
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }
        return staffServiceBean;
    }

    private void setFindStaffsParameter(
            int limitSize,
            int page,
            String userId,
            String userName,
            String departmentCd,
            Date paramExpirationStart,
            Date paramExpirationEnd,
            Map<String, Object> findStaffsParamMap) {
        this.setFindStaffsParamUserId(limitSize, page, userId, findStaffsParamMap);
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.USER_NAME.getValue(), userName);
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.DEPARTMENT_CD.getValue(), departmentCd);
        if (paramExpirationStart != null) {
            findStaffsParamMap.put(
                    Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(),
                    DateUtils.truncate(paramExpirationStart, Calendar.DAY_OF_MONTH));
        }
        if (paramExpirationEnd != null) {
            findStaffsParamMap.put(
                    Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_END.getValue(),
                    DateUtils.truncate(paramExpirationEnd, Calendar.DAY_OF_MONTH));
        }
    }

    private void setFindStaffsParamUserId(int limitSize, int page, String userId, Map<String, Object> findStaffsParamMap) {
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.LIMIT_SIZE.getValue(), limitSize);
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.PAGE.getValue(), page);
        findStaffsParamMap.put(Constant.API_SEARCH_PARAM_STAFF.USER_ID.getValue(), userId);
    }


    private boolean checkBeanValidationAndSetErrorMessagesToModel(Model model, BindingResult result, Errors errors) {
        boolean isPassed = true;
        if (!checkBeanValidationAndSetErrorMessages(result, errors)) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.FIELD_ERROR_MESSAGES.getValue(), errors.getField());
            isPassed = false;
        }
        return isPassed;
    }

    private boolean checkBeanValidationAndSetErrorMessages(BindingResult result, Errors errors) {
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
            isPassed = false;
        }
        return isPassed;
    }

    private List<Map<String, String>> getAlternativeErrorMessages() {
        List<Map<String, String>> messageTransformList = new ArrayList<>();

        addMessageTransformToList(messageTransformList,
                "???????????? \"^[a-zA-Z0-9]*$\" ?????????????????????????????????",
                "??????????????????????????????????????????");
        addMessageTransformToList(messageTransformList,
                "???????????? \"^[!-~]*$\" ?????????????????????????????????",
                "?????????????????????????????????????????????");

        return messageTransformList;
    }

    private void addMessageTransformToList(List<Map<String, String>> messageTransformList, String before, String after) {
        Map<String, String> halfWidthAlphanumeric = new HashMap<>();
        halfWidthAlphanumeric.put("before", before);
        halfWidthAlphanumeric.put("after", after);
        messageTransformList.add(halfWidthAlphanumeric);
    }

    @RequestMapping(value = "/staff/{pathUserId}", method = RequestMethod.GET)
    public String getStaffsById(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        setRequestedSearchParam(model, param, pathUserId);

        Errors errors = new Errors();

        if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) return "staff-search.html";

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), pathUserId, model, errors);

        if (staffServiceBean != null && staffServiceBean.getCount() != 0) {
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

            return "staff-detail.html";
        } else {
            return "staff-search.html";
        }
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
    public String addStaffDetailInit(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);

        Errors errors = new Errors();

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), pathUserId, model, errors);

        if (staffServiceBean != null && staffServiceBean.getCount() != 0) {
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), pathUserId);
            setDepartmentToModel(model);
            setGenericCodeToModel(model);

            staffServiceBean.getStaffs().forEach(staff -> {
                if (staff.getExpirationStart().equals(param.getParamExpirationStart())) {
                    model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staff);
                }
            });
            if (model.getAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue()) == null) {
                model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), getBlankStaffWithUserId(pathUserId));
            }

            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
        }
        return "staff-add.html";
    }

    private Staff getBlankStaffWithUserId(String userId) {
        Map<String, Object> staffParams = new HashMap<>();
        staffParams.put(Constant.API_FIELD_NAME_STAFF.USER_ID.getValue(), userId);
        return this.staffService.getStaffByParamWithoutValidation(staffParams);
    }

    @RequestMapping(value = "/staff/{pathUserId}/add", method = RequestMethod.POST)
    public String addStaffDetailExecute(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @ModelAttribute @Validated StaffControllerDetailRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), pathUserId);

        Errors errors = new Errors();

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), pathUserId, model, errors);

        if (staffServiceBean != null && staffServiceBean.getCount() != 0) {
            setDepartmentToModel(model);
            setGenericCodeToModel(model);
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(),
                    this.staffService.getStaffByParamWithoutValidation(getStaffParamByRequestParam(param)));

            if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_ADD_CONFIRM.getValue())) {
                boolean isSuccess = checkAddInputAndSetFormState(model, param, result, errors);
                if (!isSuccess) return "staff-add.html";
            } else if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_ADD_CANCEL.getValue())) {
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                        com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
            } else if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_ADD_EXECUTE.getValue())) {
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                        com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_EXECUTE.getValue());
                boolean isSuccess = addStaffAndSetFormState(model, param, result, errors);
                if (!isSuccess) return "staff-add.html";
            }
        }

        return "staff-add.html";
    }

    private boolean addStaffAndSetFormState(Model model, StaffControllerDetailRequest param, BindingResult result, Errors errors) {
        if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
            return false;
        }

        try {
            this.staffService.addStaff(getStaffParamByRequestParam(param));
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), param.getUserId(), model, errors);
        if (staffServiceBean == null || staffServiceBean.getCount() == 0) return false;

        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
        } else {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_EXECUTE.getValue());
        }
        return true;
    }

    private boolean checkAddInputAndSetFormState(Model model, StaffControllerDetailRequest param, BindingResult result, Errors errors) {
        if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_ADD_INIT.getValue());
            return false;
        }

        try {
            this.staffService.checkAddStaff(getStaffParamByRequestParam(param));
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
        return true;
    }

    private Map<String, Object> getStaffParamByRequestParam(StaffControllerDetailRequest param) {
        Map<String, Object> staffParams = new HashMap<>();

        staffParams.put(Constant.API_FIELD_NAME_STAFF.USER_ID.getValue(), param.getUserId());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.FAMILY_NAME.getValue(), param.getFamilyName());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.FIRST_NAME.getValue(), param.getFirstName());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.DEPARTMENT_CD.getValue(), param.getDepartmentCd());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.GENDER_CD.getValue(), param.getGenderCd());
        if (param.getBirthdate() != null) staffParams.put(Constant.API_FIELD_NAME_STAFF.BIRTHDATE.getValue(), new java.sql.Date(param.getBirthdate().getTime()));
        staffParams.put(Constant.API_FIELD_NAME_STAFF.BLOOD_TYPE_CD.getValue(), param.getBloodTypeCd());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.ADDRESS_PREFECTURE_CD.getValue(), param.getAddressPrefectureCd());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.ADDRESS_MUNICIPALITY.getValue(), param.getAddressMunicipality());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.PRIVATE_TEL_NO.getValue(), param.getPrivateTelNo());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.PRIVATE_EMAIL.getValue(), param.getPrivateEmail());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.WORKPLACE_TEL_NO.getValue(), param.getWorkplaceTelNo());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.WORKPLACE_EMAIL.getValue(), param.getWorkplaceEmail());
        if (param.getExpirationStart() != null) staffParams.put(Constant.API_FIELD_NAME_STAFF.EXPIRATION_START.getValue(), new java.sql.Date(param.getExpirationStart().getTime()));
        if (param.getExpirationEnd() != null) staffParams.put(Constant.API_FIELD_NAME_STAFF.EXPIRATION_END.getValue(), new java.sql.Date(param.getExpirationEnd().getTime()));
        staffParams.put(Constant.API_FIELD_NAME_STAFF.INSERT_USER.getValue(), param.getUserId());
        staffParams.put(Constant.API_FIELD_NAME_STAFF.UPDATE_USER.getValue(), param.getUserId());

        return staffParams;
    }

    private Map<String, Object> getStaffByPrimaryKey(String userId, String pathExpirationStart) {
        Map<String, Object> staffParams = new HashMap<>();

        staffParams.put(Constant.API_FIELD_NAME_STAFF.USER_ID.getValue(), userId);
        staffParams.put(Constant.API_FIELD_NAME_STAFF.EXPIRATION_START.getValue(), java.sql.Date.valueOf(pathExpirationStart));

        return staffParams;
    }

    @RequestMapping(value = "/staff/{pathUserId}/{pathExpirationStart}/delete", method = RequestMethod.GET)
    public String deleteStaffDetailInit(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @PathVariable("pathExpirationStart") String pathExpirationStart, @ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), pathUserId);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), pathExpirationStart);

        Errors errors = new Errors();

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), pathUserId, model, errors);

        if (staffServiceBean != null && staffServiceBean.getCount() != 0) {
            setDepartmentToModel(model);
            setGenericCodeToModel(model);
            staffServiceBean.getStaffs().forEach(staff -> {
                if (staff.getExpirationStart().toString().equals(pathExpirationStart)) {
                    model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staff);
                }
            });

            boolean isSuccess = checkDeleteInputAndSetFormState(model, pathUserId, pathExpirationStart, result, errors);
            if (!isSuccess) return "staff-detail.html";
        } else {
            return "staff-detail.html";
        }

        return "staff-delete.html";
    }

    private boolean checkDeleteInputAndSetFormState(Model model, String pathUserId, String pathExpirationStart, BindingResult result, Errors errors) {
        if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) {
            return false;
        }

        try {
            this.staffService.checkDeleteStaff(getStaffByPrimaryKey(pathUserId, pathExpirationStart));
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }

        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
            return false;
        } else {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_DELETE_CONFIRM.getValue());
        }
        return true;
    }

    @RequestMapping(value = "/staff/{pathUserId}/{pathExpirationStart}/delete", method = RequestMethod.POST)
    public String deleteStaffDetailExecute(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @PathVariable("pathExpirationStart") String pathExpirationStart, @ModelAttribute @Validated StaffControllerDetailRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), pathUserId);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), pathExpirationStart);

        Errors errors = new Errors();

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), pathUserId, model, errors);

        if (staffServiceBean != null && staffServiceBean.getCount() != 0) {
            setDepartmentToModel(model);
            setGenericCodeToModel(model);
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(),
                    this.staffService.getStaffByParamWithoutValidation(getStaffParamByRequestParam(param)));

            if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_DELETE_CANCEL.getValue())) {
                return "staff-detail.html";
            } else if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_DELETE_EXECUTE.getValue())) {
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                        com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_DELETE_EXECUTE.getValue());
                boolean isSuccess = deleteStaffAndSetFormState(model, pathUserId, pathExpirationStart, param, result, errors);
                if (!isSuccess) return "staff-detail.html";
                model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), null);
            }

            return "staff-delete.html";
        } else {
            return "staff-detail.html";
        }
    }

    private boolean deleteStaffAndSetFormState(Model model, String pathUserId, String pathExpirationStart, StaffControllerDetailRequest param, BindingResult result, Errors errors) {
        if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) {
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), getSqlDate(param.getExpirationStart()));
            return false;
        }

        try {
            this.staffService.deleteStaff(getStaffByPrimaryKey(pathUserId, pathExpirationStart));
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), param.getUserId(), model, errors);
        if (staffServiceBean == null || staffServiceBean.getCount() == 0) return false;

        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), getSqlDate(param.getExpirationStart()));
            return false;
        } else {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_DELETE_EXECUTE.getValue());
        }

        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), null);

        return true;
    }

    private java.sql.Date getSqlDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new java.sql.Date(calendar.getTime().getTime());
    }

    @RequestMapping(value = "/staff/{pathUserId}/{pathExpirationStart}/update", method = RequestMethod.GET)
    public String updateStaffDetailInit(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @PathVariable("pathExpirationStart") String pathExpirationStart,@ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), pathUserId);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), pathExpirationStart);

        Errors errors = new Errors();

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), pathUserId, model, errors);

        if (staffServiceBean != null && staffServiceBean.getCount() != 0) {
            setDepartmentToModel(model);
            setGenericCodeToModel(model);

            staffServiceBean.getStaffs().forEach(staff -> {
                if (staff.getExpirationStart().toString().equals(pathExpirationStart)) {
                    model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), staff);
                }
            });
            if (model.getAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue()) == null) {
                model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(), getBlankStaffWithUserId(param.getUserId()));
            }

            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_INIT.getValue());
        }
        return "staff-update.html";
    }

    @RequestMapping(value = "/staff/{pathUserId}/{pathExpirationStart}/update", method = RequestMethod.POST)
    public String updateStaffDetailExecute(HttpServletRequest request, Model model, @PathVariable("pathUserId") String pathUserId, @PathVariable("pathExpirationStart") String pathExpirationStart, @ModelAttribute @Validated StaffControllerDetailRequest param, BindingResult result) {
        CommonDisplay.setHeaderParameter(request, model);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PATH_PARAM_USER_ID.getValue(), pathUserId);
        model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.PARAM_EXPIRATION_START.getValue(), pathExpirationStart);

        Errors errors = new Errors();

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), pathUserId, model, errors);

        if (staffServiceBean != null && staffServiceBean.getCount() != 0) {
            setDepartmentToModel(model);
            setGenericCodeToModel(model);
            model.addAttribute(Constant.API_SEARCH_PARAM_STAFF.DETAIL.getValue(),
                    this.staffService.getStaffByParamWithoutValidation(getStaffParamByRequestParam(param)));

            if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_UPDATE_CONFIRM.getValue())) {
                boolean isSuccess = checkUpdateInputAndSetFormState(model, param, result, errors);
                if (!isSuccess) return "staff-update.html";
            } else if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_UPDATE_CANCEL.getValue())) {
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                        com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_INIT.getValue());
            } else if (param.getSubmitType().equals(com.sales.presentation.Constant.REQUEST_SUBMIT_TYPE.SUBMIT_TYPE_UPDATE_EXECUTE.getValue())) {
                model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                        com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_EXECUTE.getValue());
                boolean isSuccess = updateStaffAndSetFormState(model, param, result, errors);
                if (!isSuccess) return "staff-update.html";
            }
        }

        return "staff-update.html";
    }

    private boolean checkUpdateInputAndSetFormState(Model model, StaffControllerDetailRequest param, BindingResult result, Errors errors) {
        if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_INIT.getValue());
            return false;
        }

        try {
            this.staffService.checkUpdateStaff(getStaffParamByRequestParam(param));
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }

        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_INIT.getValue());
        } else {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_CONFIRM.getValue());
        }
        return true;
    }

    private boolean updateStaffAndSetFormState(Model model, StaffControllerDetailRequest param, BindingResult result, Errors errors) {
        if (!checkBeanValidationAndSetErrorMessagesToModel(model, result, errors)) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_INIT.getValue());
            return false;
        }

        try {
            this.staffService.updateStaff(getStaffParamByRequestParam(param));
        } catch (DomainRuleIllegalException e) {
            errors.getGlobal().addAll(e.getMessages());
        }

        StaffServiceBean staffServiceBean = getStaffListByIdAndSetErrorsToModel(param.getLimitSize(), param.getPage(), param.getUserId(), model, errors);
        if (staffServiceBean == null || staffServiceBean.getCount() == 0) return false;

        if (!errors.getGlobal().isEmpty()) {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_COMMON.GLOBAL_ERROR_MESSAGES.getValue(), errors.getGlobal());
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_INIT.getValue());
        } else {
            model.addAttribute(com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE.getValue(),
                    com.sales.presentation.Constant.RESPONSE_FORM_STATE.FORM_STATE_UPDATE_EXECUTE.getValue());
        }
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/api/staffs", method = RequestMethod.GET)
    public ResponseEntity<StaffControllerGetStaffsResponse> getStaffsAPI(@ModelAttribute @Validated StaffControllerGetStaffsRequest param, BindingResult result) {
        StaffControllerGetStaffsResponse staffControllerGetStaffsResponse = new StaffControllerGetStaffsResponse();
        Errors errors = new Errors();

        if (!checkBeanValidationAndSetErrorMessages(result, errors)) {
            staffControllerGetStaffsResponse.getErrors().setField(errors.getField());
            return new ResponseEntity<>(staffControllerGetStaffsResponse, HttpStatus.BAD_REQUEST);
        }

        StaffServiceBean staffServiceBean = getStaffListForSeachResult(param.getLimitSize(), param.getPage(), param.getUserId(), param.getUserName(), param.getDepartmentCd(), param.getParamExpirationStart(), param.getParamExpirationEnd(), errors);
        if (!errors.getGlobal().isEmpty()) {
            staffControllerGetStaffsResponse.getErrors().setGlobal(errors.getGlobal());
            return new ResponseEntity<>(staffControllerGetStaffsResponse, HttpStatus.BAD_REQUEST);
        }

        staffControllerGetStaffsResponse.setCount(staffServiceBean.getCount());
        staffControllerGetStaffsResponse.setLimitSize(staffServiceBean.getLimitSize());
        staffControllerGetStaffsResponse.setPage(staffServiceBean.getPage());
        staffControllerGetStaffsResponse.setStaffs(staffServiceBean.getStaffs());
        return new ResponseEntity<>(staffControllerGetStaffsResponse, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/staffs/{pathUserId}", method = RequestMethod.GET)
    public ResponseEntity<StaffControllerGetStaffsResponse> getStaffDetailsAPI(@ModelAttribute @Validated StaffControllerGetStaffsRequest param, @PathVariable("pathUserId") String pathUserId, BindingResult result) {
        StaffControllerGetStaffsResponse staffControllerGetStaffsResponse = new StaffControllerGetStaffsResponse();
        Errors errors = new Errors();

        if (!checkBeanValidationAndSetErrorMessages(result, errors)) {
            staffControllerGetStaffsResponse.getErrors().setField(errors.getField());
            return new ResponseEntity<>(staffControllerGetStaffsResponse, HttpStatus.BAD_REQUEST);
        }

        StaffServiceBean staffServiceBean = getStaffListForSeachResult(param.getLimitSize(), param.getPage(), pathUserId, "", "", null, null, errors);
        if (!errors.getGlobal().isEmpty()) {
            staffControllerGetStaffsResponse.getErrors().setGlobal(errors.getGlobal());
            return new ResponseEntity<>(staffControllerGetStaffsResponse, HttpStatus.BAD_REQUEST);
        }

        staffControllerGetStaffsResponse.setCount(staffServiceBean.getCount());
        staffControllerGetStaffsResponse.setLimitSize(staffServiceBean.getLimitSize());
        staffControllerGetStaffsResponse.setPage(staffServiceBean.getPage());
        staffControllerGetStaffsResponse.setStaffs(staffServiceBean.getStaffs());
        return new ResponseEntity<>(staffControllerGetStaffsResponse, HttpStatus.OK);
    }
}
