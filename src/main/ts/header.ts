interface Header {
    role: string
}
export function initHeader(header: Header) {
    if (header) {
        if (header.role === 'ROLE_USER') {
            const staffItems = document.querySelectorAll(".staff-item");
            staffItems.forEach(function(staffItem) {
                staffItem.remove()
            })
        }
        document.querySelector('.header .user')!.classList.remove('un-visible');
        document.querySelector('.left-nav__menu')!.classList.remove('un-visible');
    }
}