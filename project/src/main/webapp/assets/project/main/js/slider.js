$(document).ready(function () {
    $('.main-slider').slick({
        autoplay: true,
        draggable: false,
        autoplaySpeed: 1500,
        slidesToShow: 2,
        slidesToScroll: 1,
        dots: true
    });
    $('.sale-item-slider').slick({
        autoplay: true,
        draggable: false,
        autoplaySpeed: 1500,
        slidesToShow: 3,
        slidesToScroll: 1,
        dots: true
    });
    $('.new-item-slider').slick({
        autoplay: true,
        draggable: false,
        autoplaySpeed: 1500,
        slidesToShow: 4,
        slidesToScroll: 1,
        dots: true
    })
});