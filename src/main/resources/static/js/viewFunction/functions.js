let functionIdToDelete = null;

$(document).on('click', '.deleteBtn', function () {
    functionIdToDelete = $(this).data('id');
    $('#confirmDeleteModal').modal('show');
});

$(document).on('click', '#confirmDeleteBtn', function () {
    if (functionIdToDelete) {
        $.ajax({
            url: `/functions/${functionIdToDelete}`,
            type: 'DELETE',
            success: function () {
                location.reload();
            },
            error: function (jqXHR) {
                getError(jqXHR);
            }
        });
        $('#confirmDeleteModal').modal('hide');
    }
});

$(document).on('click', '#addFunctionBtn', function () {
    $.get('/functions/modal/new', function (data) {
        $('#functionModalContent').html(data);
        $.get('/api/functions/available', function (functions) {
            const select = $('#mathFunctionName');
            select.empty();
            functions.sort().forEach(function (func) {
                select.append(new Option(func, func));
            });
        });
        $('#functionModal').modal('show');
    });
});

$(document).on('click', '#generateTable', function () {
    const count = parseInt($('#pointCount').val());
    if (count >= 2) {
        const tbody = $('#pointsTable tbody');
        tbody.empty(); // Очищаем таблицу
        for (let i = 0; i < count; i++) {
            tbody.append(`
                    <tr>
                        <td><input type="number" class="form-control x-input" name="pointX[${i}]" required step="0.01"></td>
                        <td><input type="number" class="form-control y-input" name="pointY[${i}]" required step="0.01"></td>
                    </tr>
                `);
        }
        $('#pointsTable').show(); // Показываем таблицу
        $('#createFunction').show(); // Показываем кнопку "Создать"
    } else {
        alert('Количество точек должно быть не менее 2.');
    }
});

// Отправка формы на сервер
$(document).on('submit', '#functionForm', function (e) {
    e.preventDefault();

    const invalidFields = $('#functionForm :input:visible').filter(function () {
        return !this.checkValidity();
    });

    if (invalidFields.length > 0) {
        alert('Пожалуйста, заполните все поля корректно.');
        invalidFields.first().focus();
        return;
    }
    const formData = $(this).serializeArray();
    const creationType = formData.find(f => f.name === 'creationType').value;
    let requestData = {
        name: formData.find(f => f.name === 'name').value,
        factoryType: formData.find(f => f.name === 'factoryType').value
    };
    console.log("Creation type", creationType);
    if (creationType === 'table') {
        const xValues = formData.filter(f => f.name.startsWith('pointX')).map(f => parseFloat(f.value));
        const yValues = formData.filter(f => f.name.startsWith('pointY')).map(f => parseFloat(f.value));
        requestData.points = xValues.map((x, i) => ({x, y: yValues[i]}));
        console.log("Create with table", requestData);
    } else if (creationType === 'mathFunction') {
        requestData.mathFunctionName = formData.find(f => f.name === "mathFunctionName").value;
        requestData.xFrom = parseFloat(formData.find(f => f.name === "xFrom").value);
        requestData.xTo = parseFloat(formData.find(f => f.name === "xTo").value);
        requestData.count = parseInt(formData.find(f => f.name === "count").value);
        console.log("Create with mathFunction", requestData);
    }

    $.ajax({
        url: '/functions',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(requestData),
        success: function () {
            alert('Функция создана');
            $('#functionModal').modal('hide');
            location.reload(); // Перезагрузка таблицы функций
        },
        error: function (jqXHR) {
            getError(jqXHR);
        }
    });
});


$(document).on('change', '#creationType', function () {
    const type = $(this).val();
    if (type === 'table') {
        $('#tableFields').show();
        $('#mathFunctionFields').hide();
        $('#pointCount').attr('required', true);
        $('#mathFunctionName, #xFrom, #xTo, #count').removeAttr('required');
    } else if (type === 'mathFunction') {
        $('#tableFields').hide();
        $('#mathFunctionFields').show();
        $('#pointCount').removeAttr('required');
        $('#mathFunctionName, #xFrom, #xTo, #count').attr('required', true);
    }
});


$(document).on('click', '.viewBtn', function () {
    const id = $(this).data('id');
    console.log('Посмотреть функцию с ID:', id);

    // Загружаем HTML-шаблон
    $.get('/templates/modal-view.html', function (template) {
        $('#functionModalContent').html(template);

        // Устанавливаем данные
        $.get(`/api/tabulated-functions/${id}`, function (response) {
            data = response;
            currentPage = 1;
            $('#functionName').text(response.name);
            renderGraph(response.points);
            renderTable(response, 1);
            $('#functionModal').data('function-id', id).modal('show');
        }).fail(function (jqXHR) {
            getError(jqXHR);
        });
    }).fail(function () {
        alert('Ошибка загрузки шаблона модального окна.');
    });
});

$(document).on('click', '#updateFunctionNameBtn', function () {
    const newName = $('#editFunctionName').val().trim();
    const functionId = $('#functionModal').data('function-id');
    if (!newName) {
        alert('Название функции не может быть пустым.');
        return;
    }

    $.ajax({
        url: `/api/tabulated-functions/${functionId}/name`,
        type: 'PUT',
        contentType: 'application/json',
        data: newName,
        success: function () {
            $('#functionName').text(newName);
            $(`#functionsTable tr[data-id="${functionId}"] td:first`).text(newName);
        },
        error: function (jqXHR) {
            getError(jqXHR);
        }
    });
});

// Вычисление значения функции
$(document).on('click', '#applyFunctionBtn', function () {
    const x = parseFloat($('#applyXValue').val());
    if (isNaN(x)) {
        alert('Введите корректное значение x.');
        return;
    }
    const id = $('#functionModal').data('function-id');
    console.log('Вычисляем значение функции с ID:', id);
    $.get(`/api/tabulated-functions/apply/${id}?x=${x}`, function (y) {
        alert(`Значение функции в точке x=${x}: y=${y}`);
    }).fail(function (jqXHR) {
        getError(jqXHR);
    });
});


const reloadTable = () => {
    $.get(`/api/tabulated-functions/${$('#functionModal').data('function-id')}`, function (data) {
        renderTable(data, currentPage);
        renderGraph(data.points);
    }).fail(function (jqXHR) {
        getError(jqXHR);
    });
};

