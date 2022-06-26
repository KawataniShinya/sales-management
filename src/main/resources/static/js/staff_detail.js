export function visibleStaffDetailButton(formState) {
    if (formState === 'stateAddInit' || formState === 'stateUpdateInit') {
        const confirms = document.querySelectorAll('.btn-detail__confirm');
        confirms.forEach(confirm => {
            confirm.classList.remove('un-visible');
        });
    }
    else if (formState === 'stateAddConfirm' || formState === 'stateDeleteConfirm' || formState === 'stateUpdateConfirm') {
        const executes = document.querySelectorAll('.btn-detail__execute');
        executes.forEach(execute => {
            execute.classList.remove('un-visible');
        });
    }
    const disUses = document.querySelectorAll('.btn-detail .un-visible');
    disUses.forEach(disUse => {
        disUse.remove();
    });
}
export function disableStaffDetailFields(formState) {
    if (formState === 'stateAddConfirm' || formState === 'stateDeleteConfirm' || formState === 'stateUpdateConfirm') {
        const detailItems = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    }
    else if (formState === 'stateAddExecute' || formState === 'stateDeleteExecute' || formState === 'stateUpdateExecute') {
        const detailItems = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    }
}
export function hilightUpdateField(formState) {
    if (formState === 'stateUpdateConfirm') {
        const toBeDetailItemUserId = document.querySelector('.staff-detail__table__item[name=userId]');
        const toBeDetailItemExpirationStart = document.querySelector('.staff-detail__table__item[name=expirationStart]');
        const toBeDetailItemFamilyName = document.querySelector('.staff-detail__table__item[name=familyName]');
        const toBeDetailItemFirstName = document.querySelector('.staff-detail__table__item[name=firstName]');
        const toBeDetailItemDepartmentCdSelect = document.querySelector('.staff-detail__table__select[name=departmentCd]');
        const toBeDetailItemDepartmentName = toBeDetailItemDepartmentCdSelect.options[toBeDetailItemDepartmentCdSelect.selectedIndex];
        const toBeDetailItemGenderCdSelect = document.querySelector('.staff-detail__table__select[name=genderCd]');
        const toBeDetailItemGenderName = toBeDetailItemGenderCdSelect.options[toBeDetailItemGenderCdSelect.selectedIndex];
        const toBeDetailItemBirthdate = document.querySelector('.staff-detail__table__item[name=birthdate]');
        const toBeDetailItemBloodTypeCdSelect = document.querySelector('.staff-detail__table__select[name=bloodTypeCd]');
        const toBeDetailItemBloodTypeName = toBeDetailItemBloodTypeCdSelect.options[toBeDetailItemBloodTypeCdSelect.selectedIndex];
        const toBeDetailItemAddressPrefectureCdSelect = document.querySelector('.staff-detail__table__select[name=addressPrefectureCd]');
        const toBeDetailItemAddressPrefectureName = toBeDetailItemAddressPrefectureCdSelect.options[toBeDetailItemAddressPrefectureCdSelect.selectedIndex];
        const toBeDetailItemAddressMunicipality = document.querySelector('.staff-detail__table__item[name=addressMunicipality]');
        const toBeDetailItemPrivateTelNo = document.querySelector('.staff-detail__table__item[name=privateTelNo]');
        const toBeDetailItemPrivateEmail = document.querySelector('.staff-detail__table__item[name=privateEmail]');
        const toBeDetailItemWorkplaceTelNo = document.querySelector('.staff-detail__table__item[name=workplaceTelNo]');
        const toBeDetailItemWorkplaceEmail = document.querySelector('.staff-detail__table__item[name=workplaceEmail]');
        const preDetails = document.querySelectorAll('.staff-search__result__list__table tbody tr');
        preDetails.forEach(detail => {
            const preDetailItemUserId = detail.querySelector('.userId');
            const preDetailItemExpirationStart = detail.querySelector('.expirationStart');
            if (toBeDetailItemUserId.value === preDetailItemUserId.innerHTML && toBeDetailItemExpirationStart.value === preDetailItemExpirationStart.innerHTML) {
                if (toBeDetailItemFamilyName.value !== detail.querySelector('.familyName').innerHTML) {
                    toBeDetailItemFamilyName.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemFamilyName.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemFirstName.value !== detail.querySelector('.firstName').innerHTML) {
                    toBeDetailItemFirstName.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemFirstName.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemDepartmentName.text !== detail.querySelector('.departmentName').innerHTML) {
                    toBeDetailItemDepartmentName.parentElement.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemDepartmentName.parentElement.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemGenderName.text !== detail.querySelector('.genderName').innerHTML) {
                    toBeDetailItemGenderName.parentElement.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemGenderName.parentElement.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemBirthdate.value !== detail.querySelector('.birthdate').innerHTML) {
                    toBeDetailItemBirthdate.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemBirthdate.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemBloodTypeName.text !== detail.querySelector('.bloodTypeName').innerHTML) {
                    toBeDetailItemBloodTypeName.parentElement.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemBloodTypeName.parentElement.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemAddressPrefectureName.text !== detail.querySelector('.addressPrefectureName').innerHTML) {
                    toBeDetailItemAddressPrefectureName.parentElement.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemAddressPrefectureName.parentElement.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemAddressMunicipality.value !== detail.querySelector('.addressMunicipality').innerHTML) {
                    toBeDetailItemAddressMunicipality.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemAddressMunicipality.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemPrivateTelNo.value !== detail.querySelector('.privateTelNo').innerHTML) {
                    toBeDetailItemPrivateTelNo.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemPrivateTelNo.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemPrivateEmail.value !== detail.querySelector('.privateEmail').innerHTML) {
                    toBeDetailItemPrivateEmail.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemPrivateEmail.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemWorkplaceTelNo.value !== detail.querySelector('.workplaceTelNo').innerHTML) {
                    toBeDetailItemWorkplaceTelNo.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemWorkplaceTelNo.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
                if (toBeDetailItemWorkplaceEmail.value !== detail.querySelector('.workplaceEmail').innerHTML) {
                    toBeDetailItemWorkplaceEmail.parentElement.parentElement.classList.add('table-warning');
                    toBeDetailItemWorkplaceEmail.parentElement.parentElement.parentElement.querySelector('.fieldtitle').classList.add('table-warning');
                }
            }
        });
    }
}
//# sourceMappingURL=staff_detail.js.map