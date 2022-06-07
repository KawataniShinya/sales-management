package com.sales.presentation;

import com.sales.common.SessionConstant;
import com.sales.common.ThreadVariables;
import com.sales.presentation.dto.MenuControllerResultResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@Scope("prototype")
public class MenuController {
    @RequestMapping("/menu")
    public String result(HttpServletRequest request, Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("id", auth.getName());

//        model.addAttribute("userId", ThreadVariables.threadLocal.get().getUserId());
//        model.addAttribute("name", "test");
//        model.addAttribute("role", ThreadVariables.threadLocal.get().getRole());

        MenuControllerResultResponse menuControllerResultResponse = new MenuControllerResultResponse();
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_ID.getValue()))
                .ifPresent(object -> menuControllerResultResponse.setUserId(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_NAME.getValue()))
                .ifPresent(object -> menuControllerResultResponse.setUserName(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.ROLE.getValue()))
                .ifPresent(object -> menuControllerResultResponse.setRole(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.DEPARTMENT_NAME.getValue()))
                .ifPresent(object -> menuControllerResultResponse.setDepartmentName(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.PRIVATE_EMAIL.getValue()))
                .ifPresent(object -> menuControllerResultResponse.setPrivateEmail(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.WORKPLACE_EMAIL.getValue()))
                .ifPresent(object -> menuControllerResultResponse.setWorkplaceEmail(object.toString()));
        model.addAttribute("response", menuControllerResultResponse);

//        model.addAttribute("userId", request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_ID.getValue()));
//        model.addAttribute("userName", request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_NAME.getValue()));
//        model.addAttribute("role", request.getSession().getAttribute(SessionConstant.ATTRIBUTE.ROLE.getValue()));
//        model.addAttribute("departmentName", request.getSession().getAttribute(SessionConstant.ATTRIBUTE.DEPARTMENT_NAME.getValue()));
//        model.addAttribute("privateEmail", request.getSession().getAttribute(SessionConstant.ATTRIBUTE.PRIVATE_EMAIL.getValue()));
//        model.addAttribute("workplaceEmail", request.getSession().getAttribute(SessionConstant.ATTRIBUTE.WORKPLACE_EMAIL.getValue()));

//        switch (ThreadVariables.threadLocal.get().getRole()) {
//            case ROLE_USER :
//            case ROLE_GUEST:
//                return "menu_customer.html";
//            case ROLE_ADMIN:
//            case ROLE_STAFF:
//                return "menu_inernal.html";
//            default:
//                return "Internal Error";
//        }
        return "menu.html";
    }
}
