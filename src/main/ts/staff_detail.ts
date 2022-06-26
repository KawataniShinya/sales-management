export function visibleStaffDetailButton(formState: string) {
    if (formState === 'stateAddInit' || formState === 'stateUpdateInit') {
        const confirms = document.querySelectorAll('.btn-detail__confirm');
        confirms.forEach(confirm => {
            confirm.classList.remove('un-visible');
        });
    } else if (formState === 'stateAddConfirm' || formState === 'stateDeleteConfirm' || formState === 'stateUpdateConfirm') {
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

export function disableStaffDetailFields (formState: string) {
    if (formState === 'stateAddConfirm' || formState === 'stateDeleteConfirm' || formState === 'stateUpdateConfirm') {
        const detailItems: NodeListOf<HTMLInputElement> = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions: NodeListOf<HTMLOptionElement> = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    } else if (formState === 'stateAddExecute' || formState === 'stateDeleteExecute' || formState === 'stateUpdateExecute') {
        const detailItems: NodeListOf<HTMLInputElement> = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions: NodeListOf<HTMLOptionElement> = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    }
}

export function hilightUpdateField (formState: string) {
    if (formState === 'stateUpdateConfirm') {
        const toBeDetailItemUserId: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=userId]');
        const toBeDetailItemExpirationStart: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=expirationStart]');
        const toBeDetailItemFamilyName: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=familyName]');
        const toBeDetailItemFirstName: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=firstName]');
        const toBeDetailItemDepartmentCdSelect: HTMLSelectElement | null = document.querySelector<HTMLSelectElement>('.staff-detail__table__select[name=departmentCd]');
        const toBeDetailItemDepartmentName: HTMLOptionElement | null = toBeDetailItemDepartmentCdSelect!.options[toBeDetailItemDepartmentCdSelect!.selectedIndex];
        const toBeDetailItemGenderCdSelect: HTMLSelectElement | null = document.querySelector<HTMLSelectElement>('.staff-detail__table__select[name=genderCd]');
        const toBeDetailItemGenderName: HTMLOptionElement | null = toBeDetailItemGenderCdSelect!.options[toBeDetailItemGenderCdSelect!.selectedIndex];
        const toBeDetailItemBirthdate: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=birthdate]');
        const toBeDetailItemBloodTypeCdSelect: HTMLSelectElement | null = document.querySelector<HTMLSelectElement>('.staff-detail__table__select[name=bloodTypeCd]');
        const toBeDetailItemBloodTypeName: HTMLOptionElement | null = toBeDetailItemBloodTypeCdSelect!.options[toBeDetailItemBloodTypeCdSelect!.selectedIndex];
        const toBeDetailItemAddressPrefectureCdSelect: HTMLSelectElement | null = document.querySelector<HTMLSelectElement>('.staff-detail__table__select[name=addressPrefectureCd]');
        const toBeDetailItemAddressPrefectureName: HTMLOptionElement | null = toBeDetailItemAddressPrefectureCdSelect!.options[toBeDetailItemAddressPrefectureCdSelect!.selectedIndex];
        const toBeDetailItemAddressMunicipality: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=addressMunicipality]');
        const toBeDetailItemPrivateTelNo: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=privateTelNo]');
        const toBeDetailItemPrivateEmail: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=privateEmail]');
        const toBeDetailItemWorkplaceTelNo: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=workplaceTelNo]');
        const toBeDetailItemWorkplaceEmail: HTMLInputElement | null = document.querySelector<HTMLInputElement>('.staff-detail__table__item[name=workplaceEmail]');
        

        const preDetails: NodeListOf<Element> = document.querySelectorAll('.staff-search__result__list__table tbody tr');
        preDetails.forEach(detail => {
            const preDetailItemUserId: Element | null = detail.querySelector('.userId');
            const preDetailItemExpirationStart: Element | null = detail.querySelector('.expirationStart');

            if (toBeDetailItemUserId!.value === preDetailItemUserId!.innerHTML && toBeDetailItemExpirationStart!.value === preDetailItemExpirationStart!.innerHTML) {
                if (toBeDetailItemFamilyName!.value !== detail.querySelector('.familyName')!.innerHTML) {
                    toBeDetailItemFamilyName!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemFamilyName!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemFirstName!.value !== detail.querySelector('.firstName')!.innerHTML) {
                    toBeDetailItemFirstName!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemFirstName!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemDepartmentName!.text !== detail.querySelector('.departmentName')!.innerHTML) {
                    toBeDetailItemDepartmentName!.parentElement!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemDepartmentName!.parentElement!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemGenderName!.text !== detail.querySelector('.genderName')!.innerHTML) {
                    toBeDetailItemGenderName!.parentElement!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemGenderName!.parentElement!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemBirthdate!.value !== detail.querySelector('.birthdate')!.innerHTML) {
                    toBeDetailItemBirthdate!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemBirthdate!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemBloodTypeName!.text !== detail.querySelector('.bloodTypeName')!.innerHTML) {
                    toBeDetailItemBloodTypeName!.parentElement!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemBloodTypeName!.parentElement!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemAddressPrefectureName!.text !== detail.querySelector('.addressPrefectureName')!.innerHTML) {
                    toBeDetailItemAddressPrefectureName!.parentElement!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemAddressPrefectureName!.parentElement!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemAddressMunicipality!.value !== detail.querySelector('.addressMunicipality')!.innerHTML) {
                    toBeDetailItemAddressMunicipality!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemAddressMunicipality!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemPrivateTelNo!.value !== detail.querySelector('.privateTelNo')!.innerHTML) {
                    toBeDetailItemPrivateTelNo!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemPrivateTelNo!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemPrivateEmail!.value !== detail.querySelector('.privateEmail')!.innerHTML) {
                    toBeDetailItemPrivateEmail!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemPrivateEmail!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemWorkplaceTelNo!.value !== detail.querySelector('.workplaceTelNo')!.innerHTML) {
                    toBeDetailItemWorkplaceTelNo!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemWorkplaceTelNo!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
                if (toBeDetailItemWorkplaceEmail!.value !== detail.querySelector('.workplaceEmail')!.innerHTML) {
                    toBeDetailItemWorkplaceEmail!.parentElement!.parentElement!.classList.add('table-warning');
                    toBeDetailItemWorkplaceEmail!.parentElement!.parentElement!.parentElement!.querySelector('.fieldtitle')!.classList.add('table-warning');
                }
            }
        });
    }
}