export function hilightSelectedInStaffDetailList() {
    let details = document.querySelectorAll('.staff-search__result__list__table tbody tr');
    details.forEach(detail => {
        if (detail.querySelector('.expirationStart').innerHTML === document.querySelector('.staff-detail__table__item[name=expirationStart]').value) {
            detail.querySelector('.btn').classList.add('disabled');
            detail.classList.add('table-primary');
        }
    });
}
//# sourceMappingURL=staff_detail_list.js.map