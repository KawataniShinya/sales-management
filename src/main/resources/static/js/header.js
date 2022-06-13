export function initHeader(header) {
    if (header) {
        if (header.role === 'ROLE_USER') {
            const staffItems = document.querySelectorAll(".staff-item");
            staffItems.forEach(function (staffItem) {
                staffItem.remove();
            });
        }
        document.querySelector('.header .user').classList.remove('un-visible');
        document.querySelector('.left-nav__menu').classList.remove('un-visible');
    }
}
//# sourceMappingURL=header.js.map