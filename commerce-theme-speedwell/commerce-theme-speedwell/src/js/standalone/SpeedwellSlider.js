const CURRENT = 'current',
    NEXT = 'next',
    WILL_BE_NEXT = 'will-be-next';

const STATES_MAP = {
    [CURRENT]: {
        backwards: NEXT,
        forwards: WILL_BE_NEXT
    },
    [NEXT]: {
        backwards: WILL_BE_NEXT,
        forwards: CURRENT
    },
    [WILL_BE_NEXT]: {
        backwards: CURRENT,
        forwards: NEXT
    }
};

const BACKWARDS = 'backwards',
    FORWARDS = 'forwards';

function validateInterval(interval) {
    const MIN = 4000;

    switch(true) {
        case interval > 0 && interval <= MIN:
            return MIN;
        case interval > MIN:
            return interval;
        default:
            return null;
    }
}

const SpeedwellSlider = function (sliderContainer, setupDOMSlideFn,
                                  renderSlideContentFn, interval) {

    this.sliderWrapper = sliderContainer;
    this.setupDOMSlide = setupDOMSlideFn;
    this.renderSlideContent = renderSlideContentFn;
    this.interval = validateInterval(interval);

    if (!!this.sliderWrapper) {
        this.init();
    } else {
        throw new Error('Container not found.');
    }
};

SpeedwellSlider.prototype = {
    constructor: SpeedwellSlider,
    controls: {
        prevBtn: null,
        nextBtn: null
    },
    dataset: [],
    datasetSize: null,
    interval: null,
    slides: [],
    stateCycleMap: {},
    setupDOMSlide: function() {},
    renderSlideContent: function() {},

    attachListeners() {
        this.controls = {
            container: this.sliderWrapper.querySelector('div[class*=controls]'),
            prevBtn: this.sliderWrapper.querySelector('button[class*=control--prev]'),
            nextBtn: this.sliderWrapper.querySelector('button[class*=control--next]')
        };

        this.controls.prevBtn.addEventListener('click', this.throttleInteraction.bind(this), true);
        this.controls.nextBtn.addEventListener('click', this.throttleInteraction.bind(this), true);

        this.checkControls();
    },

    init() {
        this.setupData()
            .then(this.setupSliders.bind(this))
            .then(this.attachListeners.bind(this))
            .catch(e => {
                console.log(e);
            });
    },

    checkControls() {
        if (this.interval) {
            this.controls.container.classList.add('self-sliding');
            this.interval = setInterval((function() {
                this.throttleInteraction(null);
            }).bind(this), this.interval);
        }
    },

    getNextSlideContent(direction) {
        const nextSlideIndex = direction === FORWARDS ?
            this.stateCycleMap[WILL_BE_NEXT].index + 1 :
            this.stateCycleMap[CURRENT].index - 1;

        if (!!this.dataset[nextSlideIndex]) {
            return this.dataset[nextSlideIndex];
        }

        return direction === FORWARDS ?
            this.dataset[0] : this.dataset[this.datasetSize - 1];
    },

    setNextState(direction, slide) {
        slide.classList.remove(`is-sliding-${direction}`);
        slide.dataset.state = STATES_MAP[slide.dataset.state][direction];
    },

    didPrepare(nextSlideContent) {
        return Promise.resolve(nextSlideContent);
    },

    prepareLater() {
        return Promise.resolve(null);
    },

    prepareNow(direction) {
        return this.prepareNextSlide(direction);
    },

    prepareNextSlide(direction) {
        return new Promise(resolve => {
            const nextSlideContent = this.getNextSlideContent(direction);

            this.renderSlideContent(this.sliderWrapper, nextSlideContent);

            resolve(nextSlideContent);
        });
    },

    applyAnimation(direction, nextSlideContent) {
        return new Promise(restoreInteraction => {
            const currentSlide = this.sliderWrapper.querySelector('[data-state="current"]');

            currentSlide.addEventListener(
                'webkitTransitionEnd',
                this.handleSlideChange.bind(this, direction, restoreInteraction, nextSlideContent),
                {once: true}
            );

            this.slides.forEach(slide => {
                slide.classList.add(`is-sliding-${direction}`);
            });
        });
    },

    throttleInteraction(e) {
        const direction = e instanceof Event &&
                        e.currentTarget.className.indexOf('prev') > -1 ?
                        BACKWARDS : FORWARDS,
            prepare = direction === BACKWARDS ? this.prepareNow.bind(this) : this.prepareLater;

        this.toggleControls({isEnabled: false});

        prepare(direction)
            .then(nextSlideContent => this.applyAnimation(direction, nextSlideContent))
            .then(restore => this.toggleControls(restore));
    },

    toggleControls({isEnabled}) {
        if (isEnabled) {
            this.controls.prevBtn.removeAttribute('disabled');
            this.controls.nextBtn.removeAttribute('disabled');
        } else {
            this.controls.prevBtn.setAttribute('disabled', isEnabled);
            this.controls.nextBtn.setAttribute('disabled', isEnabled);
        }
    },

    handleSlideChange(direction, restoreInteraction, nextSlideContent) {
        this.slides.forEach(slide => this.setNextState(direction, slide));

        const $prepare = !!nextSlideContent ?
            this.didPrepare(nextSlideContent) : this.prepareNow(direction);

        $prepare.then(slideContent => {
            this.updateStateCycle(direction, slideContent);
            restoreInteraction && restoreInteraction({isEnabled: true});
        });
    },

    setupData() {
        return new Promise((resolve, reject) => {
            try {
                const ldJson = this.sliderWrapper.querySelector('.slider-dataset').innerText;
                this.dataset = this.validateDataset(JSON.parse(ldJson));

                this.dataset.forEach((object, index) => {
                    object.index = index;
                });

                this.datasetSize = this.dataset.length;
                resolve();
            } catch (e) {
                reject(new Error(e));
            }
        });
    },

    removeControls() {
        const controlsElement = this.sliderWrapper.querySelector('div[class*=controls]');

        controlsElement.remove();
    },

    oneSlideSetup() {
        this.removeControls();
        this.setupDOMSlide(CURRENT, 0);

        this.sliderWrapper
            .querySelector('[data-state=current]')
            .classList.add('is-single-slide');
    },

    twoSlidesSetup() {
        this.dataset.push(this.dataset[0]);
        this.defaultSetup();
    },

    defaultSetup() {
        const dataset = this.dataset;
        const that = this;

        Object.keys(STATES_MAP).forEach(function (state, index) {
            that.setupDOMSlide(state, index, dataset);
            that.stateCycleMap[state] = dataset[index];

        });
    },

    validateDataset(data) {
        return data;
    },

    setupSliders() {
        return new Promise((resolve, reject) => {
            switch (this.datasetSize) {
                case 0:
                    reject(new Error('No dataset size.'));
                    break;
                case 1:
                    this.oneSlideSetup();
                    break;
                case 2:
                    this.twoSlidesSetup();
                    break;
                default:
                    this.defaultSetup();
                    break;
            }

            this.slides = Array.from(this.sliderWrapper
                .querySelectorAll('[data-state]'));

            resolve();
        });
    },

    updateStateCycle(direction, nextSlideContent) {
        if (direction === FORWARDS) {
            this.stateCycleMap[CURRENT] = this.stateCycleMap[NEXT];
            this.stateCycleMap[NEXT] = this.stateCycleMap[WILL_BE_NEXT];
            this.stateCycleMap[WILL_BE_NEXT] = nextSlideContent;
        } else {
            this.stateCycleMap[WILL_BE_NEXT] = this.stateCycleMap[NEXT];
            this.stateCycleMap[NEXT] = this.stateCycleMap[CURRENT];
            this.stateCycleMap[CURRENT] = nextSlideContent;
        }
    }
};

Liferay.component('SpeedwellSlider', (function() {
    return {
        initialize(setupDOMSlideFn, renderSlideContentFn, interval) {
            const sliderContainer = window.document.querySelector('[data-will-load]');

            sliderContainer.removeAttribute('data-will-load');
            return new SpeedwellSlider(sliderContainer, setupDOMSlideFn, renderSlideContentFn, interval);
        }
    };
})());