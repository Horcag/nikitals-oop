let chartInstance = null;

function renderGraph(points) {
    const ctx = document.getElementById('functionGraph').getContext('2d');

    if (chartInstance) {
        chartInstance.destroy();
    }

    chartInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: points.map(p => p.x),
            datasets: [{
                label: 'График функции',
                data: points.map(p => ({ x: p.x, y: p.y })),
                borderColor: 'rgba(75, 192, 192, 1)',
                tension: 0.1,
            }]
        },
        options: {
            scales: {
                x: { type: 'linear', position: 'bottom' },
                y: { type: 'linear' }
            }
        }
    });
}