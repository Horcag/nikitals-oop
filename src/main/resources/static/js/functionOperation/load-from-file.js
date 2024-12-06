$(document).on('click', '#loadFromFileFirst, #loadFromFileSecond', function () {
    const target = $(this).attr('id').includes('First') ? 'first' : 'second';

    const modalHtml = `
        <div class="modal-header">
            <h5 class="modal-title">Загрузка функции из файла</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
            <form id="uploadForm">
                <div class="mb-3">
                    <label for="fileInput" class="form-label">Выберите файл</label>
                    <input type="file" class="form-control" id="fileInput" name="file" required>
                </div>
                <div class="mb-3">
                    <label for="fileFormat" class="form-label">Формат файла</label>
                    <select class="form-select" id="fileFormat" name="format">
                        <option value="binary">Бинарный</option>
                        <option value="text">Текст</option>
                        <option value="json">JSON</option>
                        <option value="xml">XML</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Загрузить</button>
            </form>
        </div>
    `;
    $('#functionModalContent').html(modalHtml);
    $('#functionModal').modal('show');

    $('#uploadForm').on('submit', function (e) {
        e.preventDefault();
        const formData = new FormData(this);

        $.ajax({
            url: '/api/files/upload',
            method: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (func) {
                updateOperandData(func, target);
                $(`#${target}OperandTable`).data('func', func);
                $('#functionModal').modal('hide');
            },
            error: function () {
                alert('Ошибка загрузки файла.');
            }
        });
    });
});
