export function setPageNation(count: number, limitSize: number, page: number) {
    removeNumberLink();

    const pagenationWidthOneSide: number = 2;
    const maxPage: number = Math.ceil(count / limitSize);
    const pagenationStart: number = setPagenationStart(page, pagenationWidthOneSide);
    const pagenationEnd: number = setPagenationEnd(page, pagenationWidthOneSide, maxPage);

    const pagenationUl: Element | null = document.querySelector('ul.pagination');
    const previousElement: Element | null = document.querySelector('ul.pagination .previous');
    const nextElement: Element | null = document.querySelector('ul.pagination .next');

    for (let i: number = pagenationStart; i < pagenationEnd + 1; i++) {
        const newUrl: URL = replaceQueryParamPage(i);
        const newLinkElement: HTMLAnchorElement = createLinkElement(i, page, newUrl);
        const newPageNumberElement: HTMLElement = createPageNumberElement(i, page);
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

function setPagenationStart(page: number, pagenationWidthOneSide: number) {
    let pagenationStart: number = page - pagenationWidthOneSide;
    if (pagenationStart <= 0) {
        pagenationStart = 1;
    }
    return pagenationStart;
}

function setPagenationEnd(page: number, pagenationWidthOneSide: number, maxPage: number) {
    let pagenationEnd: number = page + pagenationWidthOneSide;
    if (pagenationEnd > maxPage) {
        pagenationEnd = maxPage;
    }
    return pagenationEnd;
}

function replaceQueryParamPage(i: number) {
    let newUrl: URL = new URL(location.href);
    newUrl.searchParams.set('page', String(i));
    return newUrl;
}

function createLinkElement(i: number, page: number, newUrl: URL) {
    let newLinkElement: HTMLAnchorElement = document.createElement('a');
    newLinkElement.classList.add('page-link');
    if (i !== page) {
        newLinkElement.setAttribute('href', String(newUrl));
    }
    newLinkElement.innerHTML = String(i);
    return newLinkElement;
}

function createPageNumberElement(i: number, page: number) {
    let newPageNumberElement: HTMLElement = document.createElement('li');
    newPageNumberElement.classList.add('page-item', 'num');
    if (i === page) {
        newPageNumberElement.classList.add('active');
    }
    return newPageNumberElement;
}

function addNumberElement(pagenationUl: Element | null, newPageNumberElement: HTMLElement, newLinkElement: HTMLAnchorElement, nextLi: Element | null) {
    newPageNumberElement.appendChild(newLinkElement);
    pagenationUl!.insertBefore(newPageNumberElement, nextLi);
}

function setPreviousElement(page: number, previousElement: Element | null) {
    if (page === 1) {
        previousElement!.classList.add('disabled');
    } else {
        let newUrl: URL = new URL(location.href);
        newUrl.searchParams.set('page', String(page - 1));
        document.querySelector('.page-item.previous .page-link')!.setAttribute('href', String(newUrl));
    }
}

function setNextElement(page: number, maxPage: number, nextElement: Element | null) {
    if (page === maxPage) {
        nextElement!.classList.add('disabled');
    } else {
        let newUrl: URL = new URL(location.href);
        newUrl.searchParams.set('page', String(page + 1));
        document.querySelector('.page-item.next .page-link')!.setAttribute('href', String(newUrl));
    }
}

export function setMaxRowSelector(limitSize: number) {
    const selectorElement: HTMLSelectElement | null = document.querySelector('#maxRow');
    selectorElement!.value = String(limitSize);
}

export function addEventSetRowLimitSizeToPageNationHrefParam() {
    const selectorElement: HTMLSelectElement | null = document.querySelector('#maxRow');
    selectorElement!.addEventListener('change', function () {
        changePreviousHrefParamLimitSize(selectorElement);
        changeNumbersHrefParamLimitSize(selectorElement);
        changeNextHrefParamLimitSize(selectorElement);
    })
}

function changePreviousHrefParamLimitSize(selectorElement: HTMLSelectElement | null) {
    let newUrlPrevious: URL = new URL(document.querySelector('.page-item.previous .page-link')!.getAttribute('href')!);
    newUrlPrevious.searchParams.set('limitSize', selectorElement!.value);
    document.querySelector('.page-item.previous .page-link')!.setAttribute('href', String(newUrlPrevious));
}

function changeNumbersHrefParamLimitSize(selectorElement: HTMLSelectElement | null) {
    let nums: NodeListOf<Element> = document.querySelectorAll('.page-item.num .page-link');
    nums.forEach(num => {
        let href = num.getAttribute('href');
        if (href) {
            let newUrlNum = new URL(num.getAttribute('href')!);
            newUrlNum.searchParams.set('limitSize', selectorElement!.value);
            num.setAttribute('href', String(newUrlNum));
        }
    });
}

function changeNextHrefParamLimitSize(selectorElement: HTMLSelectElement | null) {
    let newUrlNext: URL = new URL(document.querySelector('.page-item.next .page-link')!.getAttribute('href')!);
    newUrlNext.searchParams.set('limitSize', selectorElement!.value);
    document.querySelector('.page-item.next .page-link')!.setAttribute('href', String(newUrlNext));
}