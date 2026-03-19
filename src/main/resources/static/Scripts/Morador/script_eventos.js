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

// Fecha o dropdown ao clicar fora dele
document.addEventListener('click', function(event) {
	const dropdown = document.getElementById('user-dropdown');
	const userIcon = document.querySelector('.img_nav_login');
	if (!dropdown.contains(event.target) && event.target !== userIcon) {
		dropdown.style.display = 'none';
	}
});