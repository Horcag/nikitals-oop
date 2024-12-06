$(document).on('click', '.confirm-update', function () {
    const row = $(this).closest('tr');
    const pointId = row.data('id');
    const newY = parseFloat(row.find('.point-y').val());
    console.log('Обновление точки с ID:', pointId, 'Новое значение Y:', newY);
    $.ajax({
        url: '/api/points',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ id: pointId, y: newY }),
        success: function () {
            reloadTable()
        },
        error: function (jqXHR) {
            getError(jqXHR);
        }
    });
});

$(document).on('click', '.remove-point', function () {
    const row = $(this).closest('tr');
    const pointId = row.data('id');
    console.log('Удаление точки с ID:', pointId);
    if (confirm('Вы уверены, что хотите удалить эту точку?')) {
        $.ajax({
            url: `/api/points/${pointId}`,
            type: 'DELETE',
            success: function () {
                reloadTable()
            },
            error: function (jqXHR) {
                getError(jqXHR);
            }
        });
    }
});

$(document).on('input', '.point-y', function () {
    const row = $(this).closest('tr');
    row.find('.confirm-update').removeClass('hidden');
});

// Добавление новой точки
$(document).on('click', '#addPointConfirmBtn', function () {
    const newX = parseFloat($('#newXValue').val());
    const newY = parseFloat($('#newYValue').val());
    const functionId = $('#functionModal').data('function-id');

    // Проверка на корректность ввода
    if (isNaN(newX) || isNaN(newY)) {
        alert('Введите корректные значения для x и y.');
        return;
    }

    $.ajax({
        url: '/api/points',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ functionId, x: newX, y: newY }),
        success: function () {
            reloadTable()
        },
        error: function (jqXHR) {
            getError(jqXHR);
        }
    });
});


