let data = null;
let currentPage = 1;
const pointsPerPage = 10;

// Отображение таблицы
const renderTable = (data, page) => {
    const start = (page - 1) * pointsPerPage;
    const end = start + pointsPerPage;
    const pointsPage = data.points.slice(start, end);

    const pointsTable = pointsPage.map((point, index) => `
        <tr data-id="${point.id}">
            <td>${start + index + 1}</td>
            <td><input type="number" class="form-control point-x" value="${point.x}" disabled></td>
            <td>
                <input type="number" class="form-control point-y" value="${point.y}" data-id="${point.id}">
            </td>
            <td>
                <button class="btn btn-danger btn-sm remove-point">Удалить</button>
                <button class="btn btn-success btn-sm confirm-update hidden">Подтвердить</button>
            </td>
        </tr>
    `).join('');
    $('#pointsTableBody').html(pointsTable);
    $('#paginationInfo').text(`Страница ${page} из ${Math.ceil(data.points.length / pointsPerPage)}`);

    $('#prevPageBtn').prop('disabled', page === 1);
    $('#nextPageBtn').prop('disabled', page === Math.ceil(data.points.length / pointsPerPage));
};

// Переход между страницами
const updatePagination = (direction) => {
    if (!data) {
        console.error('Данные не загружены.');
        return;
    }
    currentPage += direction;
    renderTable(data, currentPage);
};

// Кнопки пагинации
$(document).on('click', '#prevPageBtn', function () {
    if (currentPage > 1) {
        updatePagination(-1);
    }
});

$(document).on('click', '#nextPageBtn', function () {
    if (currentPage < Math.ceil(data.points.length / pointsPerPage)) {
        updatePagination(1);
    }
});

