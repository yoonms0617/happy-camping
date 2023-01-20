$(document).ready(function () {
    $('.main-slider').slick({
        autoplay: true,
        draggable: false,
        autoplaySpeed: 1500,
        slidesToShow: 2,
        slidesToScroll: 1,
        arrows: false,
        dots: true
    });
    $('.sale-item-slider').slick({
        autoplay: true,
        draggable: false,
        autoplaySpeed: 1500,
        slidesToShow: 3,
        slidesToScroll: 1,
        arrows: false,
        dots: true
    });
    $('.new-item-slider').slick({
        autoplay: true,
        draggable: false,
        autoplaySpeed: 1500,
        slidesToShow: 4,
        slidesToScroll: 1,
        arrows: false,
        dots: true
    });
    $('.brand-menu-slider').slick({
        autoplay: true,
        draggable: false,
        autoplaySpeed: 1500,
        slidesToShow: 7,
        slidesToScroll: 1,
        arrows: false,
        dots: false
    });
});