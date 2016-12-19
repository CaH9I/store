$(document).ready(function() {
    $('.add-to-cart').on('submit', addToCart);
});

function addToCart(e) {
    e.preventDefault();
    $('#errors-section').empty();
    
    var url = $(this).attr('action');
    var data = $(this).serialize();
    
    $.post(url, data, function(response) {
        if (!response.errors) {
            $('#cart-item .item-number').html(response.itemsText);
            $('#cart-item .item-price').html(response.priceText);
        } else {
            response.errors.forEach(function(msg) {
                $('#errors-section').append(msg);
            });
        }
    });
}