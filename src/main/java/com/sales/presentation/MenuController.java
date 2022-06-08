package com.sales.presentation;

import com.sales.common.SessionConstant;
import com.sales.presentation.dto.MenuInitResponse;
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
    public String init(HttpServletRequest request, Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("id", auth.getName());

//        model.addAttribute("userId", ThreadVariables.threadLocal.get().getUserId());
//        model.addAttribute("name", "test");
//        model.addAttribute("role", ThreadVariables.threadLocal.get().getRole());

        MenuInitResponse menuInitResponse = new MenuInitResponse();
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_ID.getValue()))
                .ifPresent(object -> menuInitResponse.setUserId(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.USER_NAME.getValue()))
                .ifPresent(object -> menuInitResponse.setUserName(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.ROLE.getValue()))
                .ifPresent(object -> menuInitResponse.setRole(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.DEPARTMENT_NAME.getValue()))
                .ifPresent(object -> menuInitResponse.setDepartmentName(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.PRIVATE_EMAIL.getValue()))
                .ifPresent(object -> menuInitResponse.setPrivateEmail(object.toString()));
        Optional.ofNullable(request.getSession().getAttribute(SessionConstant.ATTRIBUTE.WORKPLACE_EMAIL.getValue()))
                .ifPresent(object -> menuInitResponse.setWorkplaceEmail(object.toString()));
        model.addAttribute("response", menuInitResponse);

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
