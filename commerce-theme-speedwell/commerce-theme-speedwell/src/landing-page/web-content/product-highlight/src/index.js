const slides = Array.from(document.querySelectorAll('.image, .product-name')),
    goBack = document.querySelector('#backwards'),
    goOn = document.querySelector('#forwards');

const statesMap = {
    ['will-be-next']: {
        backwards: 'current',
        forwards: 'next'
    },
    ['next']: {
        backwards: 'will-be-next',
        forwards: 'current'
    },
    ['current']: {
        backwards: 'next',
        forwards: 'will-be-next'
    }
};

function setNextState(direction, slide) {
    slide.classList.remove(`is-sliding-${direction}`);
    slide.dataset.state = statesMap[slide.dataset.state][direction];
}

function onTransitionEnd(direction, restoreInteraction) {
    slides.forEach(slide => setNextState(direction, slide));
    restoreInteraction && restoreInteraction({isEnabled: true})
}

function applyAnimation(direction) {
    return new Promise(restoreInteraction => {
        const currentSlide = document.querySelector('[data-state="current"]');

        currentSlide.addEventListener(
            'webkitTransitionEnd',
            onTransitionEnd.bind(null, direction, restoreInteraction),
            {once: true}
        );

        slides.forEach(slide => {
            slide.classList.add(`is-sliding-${direction}`);
        });
    });
}

function toggleControls({isEnabled}) {
    if (isEnabled) {
        goBack.removeAttribute('disabled');
        goOn.removeAttribute('disabled');
    } else {
        goBack.setAttribute('disabled', isEnabled);
        goOn.setAttribute('disabled', isEnabled);
    }
}

function throttleInteraction(event) {
    toggleControls({isEnabled: false});
    applyAnimation(event.target.id)
        .then(restore => toggleControls(restore));
}

goBack.addEventListener('click', throttleInteraction, true);
goOn.addEventListener('click', throttleInteraction, true);
