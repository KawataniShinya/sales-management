export function visibleStaffDetailButton(formState) {
    if (formState === 'stateAddInit') {
        const confirms = document.querySelectorAll('.btn-detail__confirm');
        confirms.forEach(confirm => {
            confirm.classList.remove('un-visible');
        });
    }
    else if (formState === 'stateAddConfirm' || formState === 'stateDeleteConfirm') {
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
    if (formState === 'stateAddConfirm' || formState === 'stateDeleteConfirm') {
        document.querySelector('.messageConfirmation').classList.remove('un-visible');
        const detailItems = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    }
    else if (formState === 'stateAddExecute' || formState === 'stateDeleteExecute') {
        document.querySelector('.messageExecuted').classList.remove('un-visible');
        const detailItems = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    }
}
//# sourceMappingURL=staff_detail.js.map