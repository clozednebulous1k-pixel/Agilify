
function closePopup() {
	const popup = document.getElementById('popup');
	popup.style.display = 'none';
}

function toggleDropdown() {
	const dropdown = document.getElementById('user-dropdown');
	dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}

// Fecha o dropdown ao clicar fora dele
document.addEventListener('click', function(event) {
	const dropdown = document.getElementById('user-dropdown');
	const userIcon = document.querySelector('.img_nav_login');
	if (!dropdown.contains(event.target) && event.target !== userIcon) {
		dropdown.style.display = 'none';
	}
});

window.addEventListener("load", function() {
	let inputData = document.getElementById("perfil-data");
	console.log(inputData); // Verifique se ele está sendo encontrado
	if (inputData) {
		inputData.value = getCurrentLocalDateTime();
		console.log("Valor atribuído:", inputData.value);
	} else {
		console.error("Elemento #perfil-data não encontrado.");
	}
});

