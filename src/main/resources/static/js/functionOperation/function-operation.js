$(document).ready(function () {
    // Загрузка списка функций при нажатии на кнопку "Выбрать из существующих"
    $(document).on('click', '#selectFromExistingFirst, #selectFromExistingSecond', function () {
        const target = $(this).attr('id').includes('First') ? 'first' : 'second';

        // Загружаем модальное окно для выбора функции
        $.get('/templates/modal-load-functions.html', function (html) {
            $('#functionModalContent').html(html);
            $('#functionModal').modal('show');

            // Загрузка списка функций с фильтрацией
            loadFunctions(target);
        });
    });

    // Функция для загрузки и отображения функций
    function loadFunctions(target) {
        $.get('/api/tabulated-functions', function (functions) {
            const listHtml = functions.map(func => `
                <tr>
                    <td>${func.name}</td>
                    <td>${func.factoryType}</td>
                    <td>
                        <button class="btn btn-primary select-function" data-id="${func.id}" data-target="${target}">
                            Выбрать
                        </button>
                    </td>
                </tr>
            `).join('');

            $('#functionList').html(listHtml);

            // Фильтрация по имени
            $('#functionSearch').on('input', function () {
                const searchTerm = $(this).val().toLowerCase();
                $('tr').each(function () {
                    const rowName = $(this).find('td:first').text().toLowerCase();
                    $(this).toggle(rowName.indexOf(searchTerm) !== -1);
                });
            });

            // Фильтрация по фабрике
            $('#filterByType').on('change', function () {
                const filterValue = $(this).val();
                $('tr').each(function () {
                    const rowValue = $(this).find('td:nth-child(2)').text();
                    $(this).toggle(rowValue === filterValue || filterValue === 'all');
                });
            });

            // Сортировка по имени
            $('#ByName').on('change', function () {
                const sortByName = $(this).is(':checked');
                const rows = $('#functionList tr').toArray();
                rows.sort((a, b) => {
                    const nameA = $(a).find('td:first').text().toLowerCase();
                    const nameB = $(b).find('td:first').text().toLowerCase();
                    return sortByName ? nameA.localeCompare(nameB) : nameB.localeCompare(nameA);
                });
                $('#functionList').html(rows);
            });

            // Выбор функции
            $('.select-function').on('click', function () {
                const functionId = $(this).data('id');
                const target = $(this).data('target');

                $.get(`/api/tabulated-functions/${functionId}`, function (func) {
                    updateOperandData(func, target);
                    $('#functionModal').modal('hide');
                    $(`#${target}OperandTable`).data('func', func);


                });
            });
        });
    }
});

// Обработка операций
$('.btn-info').on('click', function () {
    const operation = $(this).attr('id').replace('Operation', '');
    const firstFunc = $('#firstOperandTable').data('func');
    const secondFunc = $('#secondOperandTable').data('func');

    if (!firstFunc || !secondFunc) {
        alert('Выберите оба операнда.');
        return;
    }
    const operands = {
        function1: firstFunc,
        function2: secondFunc
    };

    $.ajax({
        url: `/api/tabulated-functions/${operation}`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(operands),
        success: function (resultFunc) {
            updateOperandData(resultFunc, 'result');
            $('#resultTable').data('func', resultFunc);
        },
        error: function (jqXHR) {
            getError(jqXHR);
        }
    });
});



