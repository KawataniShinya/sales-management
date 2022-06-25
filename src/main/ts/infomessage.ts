export function removeInfoMessagesDisuse (formState: string) {
    if (formState === 'stateAddConfirm' || formState === 'stateDeleteConfirm' || formState === 'stateUpdateConfirm') {
        document.querySelector('.messageConfirmation')!.classList.remove('.info un-visible');
    } else if (formState === 'stateAddExecute' || formState === 'stateDeleteExecute' || formState === 'stateUpdateExecute') {
        document.querySelector('.messageExecuted')!.classList.remove('.info un-visible');
    }

    const disUses = document.querySelectorAll('.info .un-visible');
    disUses.forEach(disUse => {
        disUse.remove();
    });
}