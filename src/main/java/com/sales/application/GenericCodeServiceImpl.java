package com.sales.application;

import com.sales.application.bean.GenericCodeServiceBean;
import com.sales.domain.genericcode.GenericCodeDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GenericCodeServiceImpl implements GenericCodeService{
    private final GenericCodeDomainService genericCodeDomainService;

    @Autowired
    public GenericCodeServiceImpl(GenericCodeDomainService genericCodeDomainService) {
        this.genericCodeDomainService = genericCodeDomainService;
    }

    @Override
    public GenericCodeServiceBean getGenericCodeListInStaff() {
        GenericCodeServiceBean genericCodeServiceBean = new GenericCodeServiceBean();

        genericCodeServiceBean.setGenders(this.genericCodeDomainService.getGenderList());
        genericCodeServiceBean.setBloodTypes(this.genericCodeDomainService.getBloodTypeList());
        genericCodeServiceBean.setAddressPrefectures(this.genericCodeDomainService.getAddressPrefectureList());

        return genericCodeServiceBean;
    }
}
