// Fecha o dropdown ao clicar fora dele
document.addEventListener('click', function(event) {
	const dropdown = document.getElementById('user-dropdown');
	const userIcon = document.querySelector('.img_nav_login');
	if (!dropdown.contains(event.target) && event.target !== userIcon) {
		dropdown.style.display = 'none';
	}
});

function closePopup() {
	const popup = document.getElementById('popup');
	popup.style.display = 'none';
}
function toggleDropdown() {
	const dropdown = document.getElementById('user-dropdown');
	dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}

function openAddModal() {
	const modal = document.getElementById("event-modal-add");
	modal.style.display = "block"; 
	
	document.getElementById('func-nome').value = '';
	document.getElementById('func-email').value = '';
	document.getElementById('func-senha').value = '';
	document.getElementById('func-OAuth2').value = false;
}

function closeModal() {
	const modal1 = document.getElementById("event-modal-add");

	modal1.style.display = "none";

}
