const firstOperandState = { data: null, currentPage: 1 };
const secondOperandState = { data: null, currentPage: 1 };
const resultState = { data: null, currentPage: 1 };


const renderOperandTable = (state, tableSelector, paginationInfoSelector, target) => {
    const start = (state.currentPage - 1) * pointsPerPage;
    const end = start + pointsPerPage;
    const pageData = state.data.points.slice(start, end);

    const tableBody = pageData.map((point, index) => `
        <tr>
            <td>${start + index + 1}</td>
            <td>${point.x}</td>
            <td>${point.y}</td>
        </tr>
    `).join('');
    $(tableSelector).html(tableBody);

    // Обновляем информацию о пагинации
    $(paginationInfoSelector).text(`Страница ${state.currentPage} из ${Math.ceil(state.data.points.length / pointsPerPage)}`);

    // Управление кнопками
    if(target === 'result') {
        $(`#${target}PrevPageBtn`).prop('disabled', state.currentPage === 1);
        $(`#${target}NextPageBtn`).prop('disabled', state.currentPage >= Math.ceil(state.data.points.length / pointsPerPage));
        return;
    }
    $(`#${target}OperandPrevPageBtn`).prop('disabled', state.currentPage === 1);
    $(`#${target}OperandNextPageBtn`).prop('disabled', state.currentPage >= Math.ceil(state.data.points.length / pointsPerPage));
};


const updateOperandData = (func, target) => {
    let state;
    let tableSelector;
    let paginationInfoSelector;
    if (target === 'result') {
        state = resultState;
        tableSelector = '#resultTable tbody';
        paginationInfoSelector = '#resultPaginationInfo';
    } else {
        state = target === 'first' ? firstOperandState : secondOperandState;
        tableSelector = `#${target}OperandTable tbody`;
        paginationInfoSelector = `#${target}OperandPaginationInfo`;
    }

    // Проверка на наличие точек
    if (!func.points || func.points.length === 0) {
        alert('Функция не содержит точек.');
        return;
    }

    // Инициализация состояния
    state.data = func;
    state.currentPage = 1;

    // Рендер таблицы
    renderOperandTable(state, tableSelector, paginationInfoSelector, target);
};


$('#firstOperandPrevPageBtn').on('click', () => {
    if (firstOperandState.currentPage > 1) {
        firstOperandState.currentPage -= 1;
        renderOperandTable(firstOperandState, '#firstOperandTable tbody', '#firstOperandPaginationInfo', 'first');
    }
});

$('#firstOperandNextPageBtn').on('click', () => {
    if (firstOperandState.currentPage < Math.ceil(firstOperandState.data.points.length / pointsPerPage)) {
        firstOperandState.currentPage += 1;
        renderOperandTable(firstOperandState, '#firstOperandTable tbody', '#firstOperandPaginationInfo', 'first');
    }
});

$('#secondOperandPrevPageBtn').on('click', () => {
    if (secondOperandState.currentPage > 1) {
        secondOperandState.currentPage -= 1;
        renderOperandTable(secondOperandState, '#secondOperandTable tbody', '#secondOperandPaginationInfo', 'second');
    }
});

$('#secondOperandNextPageBtn').on('click', () => {
    if (secondOperandState.currentPage < Math.ceil(secondOperandState.data.points.length / pointsPerPage)) {
        secondOperandState.currentPage += 1;
        renderOperandTable(secondOperandState, '#secondOperandTable tbody', '#secondOperandPaginationInfo', 'second');
    }
});

$('#resultPrevPageBtn').on('click', () => {
    if (resultState.currentPage > 1) {
        resultState.currentPage -= 1;
        renderOperandTable(resultState, '#resultTable tbody', '#resultPaginationInfo', 'result');
    }
});

$('#resultNextPageBtn').on('click', () => {
    if (resultState.currentPage < Math.ceil(resultState.data.points.length / pointsPerPage)) {
        resultState.currentPage += 1;
        renderOperandTable(resultState, '#resultTable tbody', '#resultPaginationInfo', 'result');
    }
});

