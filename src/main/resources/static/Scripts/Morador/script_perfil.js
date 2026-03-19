document.getElementById("perfil-data").value = getCurrentLocalDateTime();


function getCurrentLocalDateTime() {
	let now = new Date();
	let year = now.getFullYear();
	let month = String(now.getMonth() + 1).padStart(2, '0');
	let day = String(now.getDate()).padStart(2, '0');
	let hours = String(now.getHours()).padStart(2, '0');
	let minutes = String(now.getMinutes()).padStart(2, '0');

	return `${year}-${month}-${day}T${hours}:${minutes}`;
}


function closePopup() {
	const popup = document.getElementById('popup');
	popup.style.display = 'none';
}
function toggleDropdown() {
	const dropdown = document.getElementById('user-dropdown');
	dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}

function logout() {
	// Redirecione para a página de login ou realize outra ação
	window.location.href = '../index.html';
}
function habilitarEdicao(id, btn) {
	const input = document.getElementById(id);
	input.disabled = false;
	input.focus();
	btn.style.display = 'none';
	btn.nextElementSibling.style.display = 'inline-block'; // Mostrar botão cancelar
	document.querySelector('.salvar-btn').style.display = 'block';
}

function cancelarEdicao(id, btn) {
	const input = document.getElementById(id);
	input.value = valoresOriginais[id];
	input.disabled = true;
	btn.style.display = 'none';
	btn.previousElementSibling.style.display = 'inline-block'; // Mostrar botão alterar
	// Esconde o botão salvar se nenhum campo estiver editável
	if ([...document.querySelectorAll('#perfil-form input')].every(inp => inp.disabled)) {
		document.querySelector('.salvar-btn').style.display = 'none';
	}
}

const valoresOriginais = {
	'perfil-nome': document.getElementById('perfil-nome').value,
	'perfil-email': document.getElementById('perfil-email').value,
	'perfil-telefone': document.getElementById('perfil-telefone').value,
	'perfil-senha': document.getElementById('perfil-senha').value
};

let campoEmEdicao = null;

document.getElementById('perfil-form').onsubmit = function(e) {
	e.preventDefault();
	// Verifica se algum campo editável foi alterado
	const campos = ['perfil-nome', 'perfil-email', 'perfil-telefone'];
	for (let id of campos) {
		const input = document.getElementById(id);
		if (!input.disabled && input.value !== valoresOriginais[id]) {
			campoEmEdicao = id;
			document.getElementById('confirmacao-alteracao').style.display = 'flex';
			document.getElementById('confirmacao-texto').innerText = 'Tem certeza que deseja alterar esta informação?';
			return;
		}
	}
	// Se não houver alteração, apenas salva normalmente
	salvarAlteracoes();
};

function confirmarAlteracao() {
	salvarAlteracoes();
	fecharConfirmacao();
}

function fecharConfirmacao() {
	document.getElementById('confirmacao-alteracao').style.display = 'none';
	campoEmEdicao = null;
}

function salvarAlteracoes() {
	document.querySelectorAll('#perfil-form input').forEach(inp => {
		inp.disabled = true;
		valoresOriginais[inp.id] = inp.value;
	});
	document.querySelectorAll('.cancelar-btn').forEach(btn => btn.style.display = 'none');
	document.querySelectorAll('#perfil-form button[type="button"]:not(.cancelar-btn)').forEach(btn => btn.style.display = 'inline-block');
	document.querySelector('.salvar-btn').style.display = 'none';
}
function closePopup() {
	const popup = document.getElementById('popup');
	popup.style.display = 'none';
}
function toggleDropdown() {
	const dropdown = document.getElementById('user-dropdown');
	dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}

function logout() {
	// Redirecione para a página de login ou realize outra ação
	window.location.href = '../../index.html';
}

// Fecha o dropdown ao clicar fora dele
document.addEventListener('click', function(event) {
	const dropdown = document.getElementById('user-dropdown');
	const userIcon = document.querySelector('.img_nav_login');
	if (!dropdown.contains(event.target) && event.target !== userIcon) {
		dropdown.style.display = 'none';
	}
});


