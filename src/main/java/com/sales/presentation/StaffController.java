package com.sales.presentation;

import com.sales.application.StaffService;
import com.sales.application.bean.StaffServiceBean;
import com.sales.domain.staff.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/staff")
    public String top(HttpServletRequest request, Model model) {
        CommonDisplay.setHeaderParameter(request, model);
        return "staff.html";
    }

    @RequestMapping("/staff/search")
    public String getStaffs(HttpServletRequest request, Model model) {
        CommonDisplay.setHeaderParameter(request, model);

        Map<String, Object> map = new HashMap<>();
        map.put(Constant.API_FIELD_NAME_STAFF.LIMIT_SIZE.getValue(), 20);
        map.put(Constant.API_FIELD_NAME_STAFF.PAGE.getValue(), 8);
        StaffServiceBean staffServiceBean = this.staffService.findStaffs(map);
        model.addAttribute("staffs", staffServiceBean.getStaffs());
        model.addAttribute("page", staffServiceBean.getPage());
        model.addAttribute("limitSize", staffServiceBean.getLimitSize());
        return "staff-search.html";
    }
}
