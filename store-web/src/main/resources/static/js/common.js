$(document).ready(function() {
    $('.add-to-cart').on('submit', addToCart);
});

function addToCart(e) {
    e.preventDefault();
    $('#errors-section').empty();

    var url = $(this).attr('action');
    var data = $(this).serialize();

    $.post(url, data)
        .done(function(data) {
            $('#cart-item .item-number').text(data.numberOfItems);
            $('#cart-item .item-price').text(data.subtotal);
        })
        .fail(function(data) {
            data.responseJSON.errors.forEach(function(msg) {
                $('#errors-section').append(
                    $('<div>').addClass('alert alert-danger alert-dismissable').text(msg).append(
                        $('<a>').addClass('close').attr({href:'#', 'aria-label':'close', title:'close', 'data-dismiss':'alert'}).html('&times;')
                    )
                );
            });
        });
}