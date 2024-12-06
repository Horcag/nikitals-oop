// Функция обновления списка функций
function updateFunctionTable(filters) {
    $.get('/api/tabulated-functions/search', filters, function (functions) {
        const rows = functions.map(func => `
            <tr>
                <td>${func.name}</td>
                <td>${func.factoryType}</td>
                <td>
                    <button class="btn btn-info viewBtn" data-id="${func.id}">Посмотреть</button>
                    <button class="btn btn-danger deleteBtn" data-id="${func.id}">Удалить</button>
                </td>
            </tr>
        `).join('');
        $('#functionsTable').html(rows);
    }).fail(function (jqXHR) {
        getError(jqXHR);
    });
}

// Применение фильтров
$('#searchByName, #filterByFactoryType, #sortByName').on('input change', function () {
    console.log("filter.js")
    const filters = {
        name: $('#searchByName').val(),
        type: $('#filterByFactoryType').val(),
        sortByName: $('#sortByName').is(':checked')
    };
    updateFunctionTable(filters);
});
