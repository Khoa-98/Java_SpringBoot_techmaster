jQuery(document).ready(function ($) {
    "use strict";

    $(window).scroll(function () {
        var scroll = $(window).scrollTop();
        var box = $('.header-text').height();
        var header = $('header').height();

        if (scroll >= box - header) {
            $("header").addClass("background-header");
        } else {
            $("header").removeClass("background-header");
        }
    });

    const menuEls = document.querySelectorAll("#navbarResponsive li")

    const activeMenu= () =>{
        let path = window.location.pathname;

        Array.from(menuEls).map(e => e.classList.remove("active"));
        menuEls.forEach(e => {
            if (e.firstElementChild.getAttribute("href") === path){
                e.classList.add("active")
            }
        })
    }
    activeMenu();

});
