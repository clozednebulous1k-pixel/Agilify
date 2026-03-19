
let selectedEvent = null;
let deleteMode = false; 

// Fecha o dropdown ao clicar fora dele
document.addEventListener('click', function(event) {
	const dropdown = document.getElementById('user-dropdown');
	const userIcon = document.querySelector('.img_nav_login');
	if (!dropdown.contains(event.target) && event.target !== userIcon) {
		dropdown.style.display = 'none';
	}
});

// Adiciona o evento de clique para selecionar um evento
document.querySelectorAll(".gallery-item").forEach((item) => {
	item.addEventListener("click", function() {
		selectEventForDeletion(this);
	});
});

//pop up de log
function toggleDropdown() {
	const dropdown = document.getElementById('user-dropdown');
	dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}

function openPopup(imgSrc, description, date) {
	const popup = document.getElementById('popup');
	const popupImg = document.getElementById('popup-img');
	const popupDescription = document.getElementById('popup-description');
	const popupDate = document.getElementById('popup-date');

	popupImg.src = imgSrc;
	popupDescription.textContent = description;
	popupDate.textContent = date;

	popup.style.display = 'flex';
}

function closePopup() {
	const popup = document.getElementById('popup');
	popup.style.display = 'none';
}

// Função para selecionar um evento
function selectEvent(element) {
	// Remove a seleção anterior
	const previousSelected = document.querySelector(".gallery-item.selected");
	if (previousSelected) {
		previousSelected.classList.remove("selected");
	}
	// Marca o novo evento como selecionado
	element.classList.add("selected");
	selectedEvent = element;
}

// Abre o modal para adicionar um evento
function openAddEventModal() {
	const modal = document.getElementById("event-modal-add");
	modal.style.display = "block"; // Exibe o modal

	// Limpa os campos do formulário
	document.getElementById("event-img").value = "";
	document.getElementById("event-description").value = "";
	document.getElementById("event-date").value = "";
}

function openEditEventModal() {
	const selectedItem = document.querySelector(".gallery-item.selected");
	if (!selectedItem) {
		alert("Por favor, selecione um evento para editar.");
		return;
	}

	const modal = document.getElementById("event-modal-edit");
	modal.style.display = "block";

	const idElement = selectedItem.querySelector(".gallery-id");
	const descriptionElement = selectedItem.querySelector(".gallery-description");
	const dateElement = selectedItem.querySelector(".gallery-date");

	// Preenchendo os campos do formulário
	document.getElementById("event-id-edit").value = idElement.textContent;
	document.getElementById("event-description-edit").value = descriptionElement.textContent;

	const rawDate = dateElement.textContent.trim();
	const [day, month, year] = rawDate.split("/");

	const formattedDate = `${year}-${month}-${day}`;
	document.getElementById("event-date-edit").value = formattedDate;

}

// Fecha o modal
function closeModal() {
	const modal1 = document.getElementById("event-modal-add");
	const modal2 = document.getElementById("event-modal-edit");
	modal1.style.display = "none";
	modal2.style.display = "none";
}

// Função para habilitar o modo de exclusão
function enableDeleteMode() {
	deleteMode = true;
	alert("Clique em um evento para selecioná-lo para exclusão.");
}

function selectEventForDeletion(element) {
	if (!deleteMode) return; 

	const previousSelected = document.querySelector(".item_box.selected");
	if (previousSelected) {
		previousSelected.classList.remove("selected");
	}

	const id = selectedEvent.querySelector(".gallery-id").textContent;
	element.classList.add("selected");
	selectedEvent = element;
	const confirmDelete = confirm("Tem certeza de que deseja excluir este evento?");
	if (confirmDelete) {
		fetch(`deleteEvento/${id}`, {
			method: 'POST'
		})
			.then(response => {
				if (response.redirected || response.ok) {
					selectedEvent.remove();
					selectedEvent = null;
					deleteMode = false;
					alert("Evento excluído com sucesso!");
				} else {
					alert("Erro ao excluir evento.");
				}
			})
			.catch(error => {
				console.error("Erro:", error);
				alert("Erro ao tentar excluir o evento.");
			});
	} else {
		deleteMode = false;
		selectedEvent.classList.remove("selected");
		selectedEvent = null;
	}
}
