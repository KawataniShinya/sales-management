export function setPageNation(count, limitSize, page) {
    removeNumberLink();
    const pagenationWidthOneSide = 2;
    const maxPage = Math.ceil(count / limitSize);
    const pagenationStart = setPagenationStart(page, pagenationWidthOneSide);
    const pagenationEnd = setPagenationEnd(page, pagenationWidthOneSide, maxPage);
    const pagenationUl = document.querySelector('ul.pagination');
    const previousElement = document.querySelector('ul.pagination .previous');
    const nextElement = document.querySelector('ul.pagination .next');
    for (let i = pagenationStart; i < pagenationEnd + 1; i++) {
        const newUrl = replaceQueryParamPage(i);
        const newLinkElement = createLinkElement(i, page, newUrl);
        const newPageNumberElement = createPageNumberElement(i, page);
        addNumberElement(pagenationUl, newPageNumberElement, newLinkElement, nextElement);
    }
    setPreviousElement(page, previousElement);
    setNextElement(page, maxPage, nextElement);
}
function removeNumberLink() {
    document.querySelectorAll('.page-item.num').forEach(function (e) {
        e.remove();
    });
}
function setPagenationStart(page, pagenationWidthOneSide) {
    let pagenationStart = page - pagenationWidthOneSide;
    if (pagenationStart <= 0) {
        pagenationStart = 1;
    }
    return pagenationStart;
}
function setPagenationEnd(page, pagenationWidthOneSide, maxPage) {
    let pagenationEnd = page + pagenationWidthOneSide;
    if (pagenationEnd > maxPage) {
        pagenationEnd = maxPage;
    }
    return pagenationEnd;
}
function replaceQueryParamPage(i) {
    let newUrl = new URL(location.href);
    newUrl.searchParams.set('page', String(i));
    return newUrl;
}
function createLinkElement(i, page, newUrl) {
    let newLinkElement = document.createElement('a');
    newLinkElement.classList.add('page-link');
    if (i !== page) {
        newLinkElement.setAttribute('href', String(newUrl));
    }
    newLinkElement.innerHTML = String(i);
    return newLinkElement;
}
function createPageNumberElement(i, page) {
    let newPageNumberElement = document.createElement('li');
    newPageNumberElement.classList.add('page-item', 'num');
    if (i === page) {
        newPageNumberElement.classList.add('active');
    }
    return newPageNumberElement;
}
function addNumberElement(pagenationUl, newPageNumberElement, newLinkElement, nextLi) {
    newPageNumberElement.appendChild(newLinkElement);
    pagenationUl.insertBefore(newPageNumberElement, nextLi);
}
function setPreviousElement(page, previousElement) {
    if (page === 1) {
        previousElement.classList.add('disabled');
    }
    else {
        let newUrl = new URL(location.href);
        newUrl.searchParams.set('page', String(page - 1));
        document.querySelector('.page-item.previous .page-link').setAttribute('href', String(newUrl));
    }
}
function setNextElement(page, maxPage, nextElement) {
    if (page === maxPage) {
        nextElement.classList.add('disabled');
    }
    else {
        let newUrl = new URL(location.href);
        newUrl.searchParams.set('page', String(page + 1));
        document.querySelector('.page-item.next .page-link').setAttribute('href', String(newUrl));
    }
}
export function setMaxRowSelector(limitSize) {
    const selectorElement = document.querySelector('#maxRow');
    selectorElement.value = String(limitSize);
    const limitSizeDefineElementInForm = document.querySelector('.search__hiddle__limitSize');
    limitSizeDefineElementInForm.value = String(limitSize);
}
export function addEventSetRowLimitSizeToPageNationHrefParam() {
    const selectorElement = document.querySelector('#maxRow');
    selectorElement.addEventListener('change', function () {
        changePreviousHrefParamLimitSize(selectorElement);
        changeNumbersHrefParamLimitSize(selectorElement);
        changeNextHrefParamLimitSize(selectorElement);
        changeLimitSizeInSubmitForm(selectorElement);
    });
}
function changePreviousHrefParamLimitSize(selectorElement) {
    let attributeHref = document.querySelector('.page-item.previous .page-link').getAttribute('href');
    let newUrlPrevious;
    if (attributeHref === '#') {
        newUrlPrevious = new URL(location.href);
    }
    else {
        newUrlPrevious = new URL(attributeHref);
    }
    newUrlPrevious.searchParams.set('limitSize', selectorElement.value);
    document.querySelector('.page-item.previous .page-link').setAttribute('href', String(newUrlPrevious));
}
function changeNumbersHrefParamLimitSize(selectorElement) {
    let nums = document.querySelectorAll('.page-item.num .page-link');
    nums.forEach(num => {
        let href = num.getAttribute('href');
        if (href) {
            let newUrlNum = new URL(num.getAttribute('href'));
            newUrlNum.searchParams.set('limitSize', selectorElement.value);
            num.setAttribute('href', String(newUrlNum));
        }
    });
}
function changeNextHrefParamLimitSize(selectorElement) {
    let attributeHref = document.querySelector('.page-item.next .page-link').getAttribute('href');
    let newUrlNext;
    if (attributeHref === '#') {
        newUrlNext = new URL(location.href);
    }
    else {
        newUrlNext = new URL(attributeHref);
    }
    newUrlNext.searchParams.set('limitSize', selectorElement.value);
    document.querySelector('.page-item.next .page-link').setAttribute('href', String(newUrlNext));
}
function changeLimitSizeInSubmitForm(selectorElement) {
    const limitSizeDefineElementInForm = document.querySelector('.search__hiddle__limitSize');
    limitSizeDefineElementInForm.value = selectorElement.value;
}
//# sourceMappingURL=pagenation_maxrowselector.js.map