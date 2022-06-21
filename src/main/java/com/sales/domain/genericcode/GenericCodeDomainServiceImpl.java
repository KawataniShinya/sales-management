package com.sales.domain.genericcode;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Scope("prototype")
public class GenericCodeDomainServiceImpl implements GenericCodeDomainService{
    @Getter
    @Setter
    private String category;

    private final GenericCodeRepository genericCodeRepository;

    private final GenericCode genericCode;

    @Autowired
    public GenericCodeDomainServiceImpl(GenericCodeRepository genericCodeRepository, GenericCode genericCode) {
        this.genericCodeRepository = genericCodeRepository;
        this.genericCode = genericCode;
    }

    @Override
    public List<GenericCode> getGenderList() {
        if (GenericCodeDomainService.getGenders().isEmpty()) {
            this.category = Constant.CATEGORY.GENDER.getValue();
            List<Map<String, Object>> findResult = this.genericCodeRepository.findGenericCodeByCategoryOrder1(this);
            List<GenericCode> genders = new ArrayList<>();
            findResult.forEach(map -> {
                GenericCode genericCode = this.genericCode.createGenericCode();
                genericCode.setFieldsByMapFromDataSource(map);
                genders.add(genericCode);
            });
            GenericCodeDomainService.setGenders(genders);
        }

        return GenericCodeDomainService.getGenders();
    }

    @Override
    public List<GenericCode> getBloodTypeList() {
        if (GenericCodeDomainService.getBloodType().isEmpty()) {
            this.category = Constant.CATEGORY.BLOOD_TYPE.getValue();
            List<Map<String, Object>> findResult = this.genericCodeRepository.findGenericCodeByCategoryOrder1(this);
            List<GenericCode> bloodTypes = new ArrayList<>();
            findResult.forEach(map -> {
                GenericCode genericCode = this.genericCode.createGenericCode();
                genericCode.setFieldsByMapFromDataSource(map);
                bloodTypes.add(genericCode);
            });
            GenericCodeDomainService.setBloodType(bloodTypes);
        }

        return GenericCodeDomainService.getBloodType();
    }

    @Override
    public List<GenericCode> getAddressPrefectureList() {
        if (GenericCodeDomainService.getAddressPrefecture().isEmpty()) {
            this.category = Constant.CATEGORY.ADDRESS_PREFECTURE.getValue();
            List<Map<String, Object>> findResult = this.genericCodeRepository.findGenericCodeByCategoryOrder1(this);
            List<GenericCode> addressPrefectures = new ArrayList<>();
            findResult.forEach(map -> {
                GenericCode genericCode = this.genericCode.createGenericCode();
                genericCode.setFieldsByMapFromDataSource(map);
                addressPrefectures.add(genericCode);
            });
            GenericCodeDomainService.setAddressPrefecture(addressPrefectures);
        }

        return GenericCodeDomainService.getAddressPrefecture();
    }
}
