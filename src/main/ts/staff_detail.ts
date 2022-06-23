export function visibleStaffDetailButton(formState: string) {
    if (formState === 'stateAddInit') {
        const confirms = document.querySelectorAll('.btn-detail__confirm');
        confirms.forEach(confirm => {
            confirm.classList.remove('un-visible');
        });
    } else if (formState === 'stateAddConfirm') {
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
    if (formState === 'stateAddConfirm') {
        document.querySelector('.messageConfirmation')!.classList.remove('un-visible');
        const detailItems: NodeListOf<HTMLInputElement> = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions: NodeListOf<HTMLOptionElement> = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    } else if (formState === 'stateAddExecute') {
        document.querySelector('.messageExecuted')!.classList.remove('un-visible');
        const detailItems: NodeListOf<HTMLInputElement> = document.querySelectorAll('.staff-detail__table__item');
        detailItems.forEach(detailItem => detailItem.readOnly = true);
        const selectOptions: NodeListOf<HTMLOptionElement> = document.querySelectorAll('.staff-detail__table__select option');
        selectOptions.forEach(selectOption => selectOption.disabled = !selectOption.selected);
        document.querySelectorAll('.staff-detail__table__select').forEach(select => select.classList.add('bg-readonly'));
    }
}