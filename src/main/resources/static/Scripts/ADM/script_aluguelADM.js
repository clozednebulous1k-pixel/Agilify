//pop up de log
function toggleDropdown() {
        const dropdown = document.getElementById('user-dropdown');
        dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
    }


    // Fecha o dropdown ao clicar fora dele
    document.addEventListener('click', function (event) {
        const dropdown = document.getElementById('user-dropdown');
        const userIcon = document.querySelector('.img_nav_login');
        if (!dropdown.contains(event.target) && event.target !== userIcon) {
            dropdown.style.display = 'none';
        }
    });
document.getElementById('reservation-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const location = document.getElementById('location').value;
    const date = document.getElementById('date').value;
    const time = document.getElementById('time').value;

    alert(`Reserva confirmada para ${location} no dia ${date} às ${time}.`);
});
function updateLocationInfo() {
    const locationSelect = document.getElementById('location');
    const selectedOption = locationSelect.options[locationSelect.selectedIndex];
    const imageSrc = selectedOption.getAttribute('data-img');
    const description = selectedOption.getAttribute('data-desc');

    // Atualiza a imagem e a descrição
    document.getElementById('location-image').src = imageSrc;
    document.getElementById('location-description').textContent = description;
}
// Simulação do status de disponibilidade
    const isAvailable = false; // Altere para true para testar a disponibilidade

    // Selecionar o elemento de status
    const statusElement = document.getElementById('reservation-status');

    // Atualizar a mensagem com base na disponibilidade
    if (isAvailable) {
        statusElement.textContent = 'Este local está disponível para reserva.';
        statusElement.classList.add('available');
    } else {
        statusElement.textContent = 'Este local já foi reservado.';
        statusElement.classList.add('unavailable');
    }
    document.addEventListener('DOMContentLoaded', function () {
        const calendarEl = document.getElementById('calendar');

        const calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'timeGridWeek', // Exibe a grade semanal com horários
            locale: 'pt-br', // Define o idioma para português
            slotMinTime: '08:00:00', // Horário inicial (8h)
            slotMaxTime: '22:00:00', // Horário final (22h)
            businessHours: {
                // Define os dias e horários disponíveis
                daysOfWeek: [1, 2, 3, 4, 5, 6], // Segunda a sábado
                startTime: '08:00', // Início do horário
                endTime: '22:00', // Fim do horário
            },
            selectable: true, // Permite selecionar horários
            select: function (info) {
                // Ação ao selecionar um horário
                alert(`Você selecionou: ${info.startStr} até ${info.endStr}`);
            },
            events: [
                // Exemplo de eventos já reservados
                {
                    title: 'Reservado',
                    start: '2023-10-10T10:00:00',
                    end: '2023-10-10T12:00:00',
                    color: 'red', // Cor para indicar indisponibilidade
                },
                {
                    title: 'Reservado',
                    start: '2023-10-11T14:00:00',
                    end: '2023-10-11T16:00:00',
                    color: 'red',
                },
            ],
        });

        calendar.render();
    });
function selectTime(button) {
    // Remove a classe 'selected' de todos os botões
    const buttons = document.querySelectorAll('.time-button');
    buttons.forEach(btn => btn.classList.remove('selected'));

    // Adiciona a classe 'selected' ao botão clicado
    button.classList.add('selected');

    // Define o valor do horário no campo oculto
    const timeInput = document.getElementById('time');
    timeInput.value = button.getAttribute('data-time');
}
