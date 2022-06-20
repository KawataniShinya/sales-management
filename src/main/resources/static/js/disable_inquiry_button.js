export function disableInquiryButtonInStaffDetail() {
    let details = document.querySelectorAll('.staff-search__result__list__table tbody tr');
    details.forEach(detail => {
        if (detail.querySelector('.expirationStart').innerHTML === document.querySelector('.staff-detail__table__expirationStart').value) {
            detail.querySelector('.btn').classList.add('disabled');
        }
    });
}
//# sourceMappingURL=disable_inquiry_button.js.map