let selectedEvent = null;
let deleteMode = false; // Indica se o modo de exclusão está ativo

// Fecha o dropdown ao clicar fora dele
document.addEventListener('click', function(event) {
	const dropdown = document.getElementById('user-dropdown');
	const userIcon = document.querySelector('.img_nav_login');
	if (!dropdown.contains(event.target) && event.target !== userIcon) {
		dropdown.style.display = 'none';
	}
});

// Adiciona o evento de clique para selecionar um evento
document.querySelectorAll(".item_box").forEach((item) => {
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
	const previousSelected = document.querySelector(".item_box.selected");
	if (previousSelected) {
		previousSelected.classList.remove("selected");
	}
	// Marca o novo evento como selecionado
	element.classList.add("selected");
	selectedEvent = element;
}

// Abre o modal para adicionar um evento
function openAddItemModal() {
	const modal = document.getElementById("event-modal-add");
	modal.style.display = "block"; // Exibe o modal

	// Limpa os campos do formulário
	document.getElementById("item-img").value = "";
	document.getElementById("item-description").value = "";
	document.getElementById("item-time").value = "";
}

function openEditEventModal() {
	const selectedEvent = document.querySelector(".item_box.selected");
	if (!selectedEvent) {
		alert("Por favor, selecione um evento para editar.");
		return;
	}

	const modal = document.getElementById("event-modal-edit");
	modal.style.display = "block";

	const idElement = selectedEvent.querySelector(".item-id");
	const nameElement = selectedEvent.querySelector(".item-nome");
	const descriptionElement = selectedEvent.querySelector(".item-descr");
	const hourElement = selectedEvent.querySelector(".item-hora");
	const lugarElement = selectedEvent.querySelector(".item-lugar");
	
	// Preenchendo os campos do formulário	
	document.getElementById("edit-item-id").value = idElement.textContent;
	document.getElementById("edit-item-name").value = nameElement.textContent;
	document.getElementById("edit-item-description").value = descriptionElement.textContent;
	document.getElementById("edit-item-time").value = hourElement.textContent;
	document.getElementById("edit-item-location").value = lugarElement.textContent;

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
    deleteMode = true; // Ativa o modo de exclusão
	alert("Clique em um achado para selecioná-lo para exclusão.");
}

// Função para selecionar um evento para exclusão
function selectEventForDeletion(element) {
	console.log(deleteMode);
	console.log(selectedEvent);
	console.log(selectedEvent.querySelector(".item-id").textContent);
	
	if (!deleteMode) return; // Só permite selecionar se o modo de exclusão estiver ativo

	// Remove a seleção anterior/*
	const previousSelected = document.querySelector(".item_box.selected");
	if (previousSelected) {
		previousSelected.classList.remove("selected");
	}

	const id = selectedEvent.querySelector(".item-id").textContent;
	// Marca o novo evento como selecionado
	element.classList.add("selected");
	selectedEvent = element;
	// Confirmação antes de excluir
	const confirmDelete = confirm("Tem certeza de que deseja excluir este evento?");
	if (confirmDelete) {
		fetch(`deleteAchado/${id}`, {
			method: 'POST'
		})
			.then(response => {
				if (response.redirected || response.ok) {
					//selectedEvent.remove();
					location.reload();
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
		// Caso o usuário cancele, desativa o modo de exclusão
		deleteMode = false;
		selectedEvent.classList.remove("selected");
		selectedEvent = null;
	}
}