function getError(jqXHR) {
    let errorMessage = 'Неизвестная ошибка';
    try {
        // Извлечение JSON-части из ответа
        const responseText = jqXHR.responseText;
        const jsonStartIndex = responseText.indexOf('{');
        const jsonEndIndex = responseText.lastIndexOf('}');
        console.log('JSON start index:', jsonStartIndex);
        console.log('JSON end index:', jsonEndIndex);
        const jsonResponse = JSON.parse(responseText.substring(jsonStartIndex, jsonEndIndex + 1));
        errorMessage = jsonResponse.detail || 'Ошибка: ' + jsonResponse.title;

    } catch (e) {
        console.error('Ошибка при разборе JSON:', e);
    }
    alert(errorMessage);
}