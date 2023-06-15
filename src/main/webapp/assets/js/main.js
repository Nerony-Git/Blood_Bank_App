"use strict";

/**
 * Easy selector helper function
 */
const select = (el, all = false) => {
    el = el.trim()
    if (all) {
        return [...document.querySelectorAll(el)]
    } else {
        return document.querySelector(el)
    }
}

/**
 * Easy event listener function
 */
const on = (type, el, listener, all = false) => {
    let selectEl = select(el, all)
    if (selectEl) {
        if (all) {
            selectEl.forEach(e => e.addEventListener(type, listener))
        } else {
            selectEl.addEventListener(type, listener)
        }
    }
}

/**
 * Easy on scroll event listener
 */
const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
}

/**
 * Header fixed top on scroll
 */
let selectHeader = select('#header')
if (selectHeader) {
    let headerOffset = selectHeader.offsetTop
    let nextElement = selectHeader.nextElementSibling
    const headerFixed = () => {
        if ((headerOffset - window.scrollY) <= 0) {
            selectHeader.classList.add('fixed-top')
            nextElement.classList.add('scrolled-offset')
        } else {
            selectHeader.classList.remove('fixed-top')
            nextElement.classList.remove('scrolled-offset')
        }
    }
    window.addEventListener('load', headerFixed)
    onscroll(document, headerFixed)
}

/**
 * Back to top button
 */
let backtotop = select('.back-to-top')
if (backtotop) {
    const toggleBacktotop = () => {
        if (window.scrollY > 100) {
            backtotop.classList.add('active')
        } else {
            backtotop.classList.remove('active')
        }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
}


/**
 * Mobile nav toggle
 */
on('click', '.mobile-nav-toggle', function(e) {
    select('#navbar').classList.toggle('navbar-mobile')
    this.classList.toggle('bi-list')
    this.classList.toggle('bi-x')
})

/**
 * Mobile nav dropdowns activate
 */
on('click', '.navbar .dropdown > a', function(e) {
    if (select('#navbar').classList.contains('navbar-mobile')) {
        e.preventDefault()
        this.nextElementSibling.classList.toggle('dropdown-active')
    }
}, true)

/**
 * Scrool with ofset on links with a class name .scrollto
 */
on('click', '.scrollto', function(e) {
    if (select(this.hash)) {
        e.preventDefault()

        let navbar = select('#navbar')
        if (navbar.classList.contains('navbar-mobile')) {
            navbar.classList.remove('navbar-mobile')
            let navbarToggle = select('.mobile-nav-toggle')
            navbarToggle.classList.toggle('bi-list')
            navbarToggle.classList.toggle('bi-x')
        }
        scrollto(this.hash)
    }
}, true)

/**
 * Scroll with ofset on page load with hash links in the url
 */
window.addEventListener('load', () => {
    if (window.location.hash) {
        if (select(window.location.hash)) {
            scrollto(window.location.hash)
        }
    }
});

/**
 * FlexSlider
 */
$(window).on('load', function () {
    $('.flexslider').flexslider({
        animation: "slide",
        animationLoop: false,
        itemWidth: 210,
        itemMargin: 5,
        minItems: 2,
        maxItems: 4,
        start: function (slider) {
            $('body').removeClass('loading');
        }
    });
});

/**
 * Set Header Active
 */
const currentURL = window.location.href;

// Get the list items
const homeLi = document.getElementById('home');
const aboutLi = document.getElementById('about');
const contactLi = document.getElementById('contact');
const loginLi = document.getElementById('login');
const signupLi = document.getElementById('signup');

// Check if the current URL matches the respective page URL and add the 'active' class
if (currentURL.includes('/about_us')) {
    aboutLi.classList.add('active');
} else if (currentURL.includes('/contact_us')) {
    contactLi.classList.add('active');
} else if (currentURL.includes('/user_login') || currentURL.includes('/admin_login')) {
    loginLi.classList.add('active');
} else if (currentURL.includes('/user_register') || currentURL.includes('/admin_register')) {
    signupLi.classList.add('active');
} else {
    homeLi.classList.add('active');
}

