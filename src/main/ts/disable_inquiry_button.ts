export function disableInquiryButtonInStaffDetail() {
    let details: NodeListOf<Element> = document.querySelectorAll('.staff-search__result__list__table tbody tr');
    details.forEach(detail => {
        if (detail.querySelector('.expirationStart')!.innerHTML === document.querySelector<HTMLInputElement>('.staff-detail__table__expirationStart')!.value) {
            detail.querySelector('.btn')!.classList.add('disabled');
        }
    });
}