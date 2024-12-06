$(document).on('click', '#saveResultToFile', function () {
    const func = $('#resultTable').data('func');
    const modalHtml = `
        <div class="modal-header">
            <h5 class="modal-title">Сохранение функции в файл</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
            <form id="downloadForm">
                <div class="mb-3">
                    <label for="fileFormat" class="form-label">Формат файла</label>
                    <select class="form-select" id="fileFormat" name="format">
                        <option value="binary">Бинарный</option>
                        <option value="text">Текст</option>
                        <option value="json">JSON</option>
                        <option value="xml">XML</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="fileName" class="form-label">Имя файла</label>
                    <input type="text" class="form-control" id="fileName" name="fileName" required>
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    `;
    $('#functionModalContent').html(modalHtml);
    $('#functionModal').modal('show');

    $('#downloadForm').on('submit', async function (e) {
        e.preventDefault();
        const format = $('#fileFormat').val();
        const fileName = $('#fileName').val();
        func.name = fileName;
        const fileHandle = await window.showSaveFilePicker({
            suggestedName: `${fileName}.${format}`,
            types: [{
                description: 'Function File',
                accept: { 'application/json': ['.json'], 'text/plain': ['.txt'], 'application/xml': ['.xml'], 'application/octet-stream': ['.bin'] }
            }]
        });

        const writableStream = await fileHandle.createWritable();
        const response = await fetch('/api/files/download', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                tabulatedFunction: func,
                format: format
            })
        });
        const fileContent = await response.text();
        await writableStream.write(fileContent);
        await writableStream.close();
        $('#functionModal').modal('hide');
    });
});