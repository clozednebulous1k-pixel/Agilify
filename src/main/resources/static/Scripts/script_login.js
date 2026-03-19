function showRecoveryForm() {
	document.querySelector('.login-container').style.display = 'none';
	document.getElementById('recovery-container').style.display = 'block';
}

function hideRecoveryForm() {
	document.querySelector('.login-container').style.display = 'block';
	document.getElementById('recovery-container').style.display = 'none';
}

function showRegisterForm() {
	document.querySelector('.login-container').style.display = 'none';
	document.getElementById('register-container').style.display = 'block';
}

function hideRegisterForm() {
	document.querySelector('.login-container').style.display = 'block';
	document.getElementById('register-container').style.display = 'none';
}

function togglePasswordVisibility(inputId) {
	const input = document.getElementById(inputId);
	const icon = input.nextElementSibling;

	if (input.type === "password") {
		input.type = "text";
		icon.classList.remove('fa-eye');
		icon.classList.add('fa-eye-slash');
	} else {
		input.type = "password";
		icon.classList.remove('fa-eye-slash');
		icon.classList.add('fa-eye');
	}
}

const recoveryOption = document.getElementById('recovery-option');
const emailRecoveryGroup = document.getElementById('email-recovery-group');
const phoneRecoveryGroup = document.getElementById('phone-recovery-group');

recoveryOption.addEventListener('change', () => {
	if (recoveryOption.value === 'email') {
		emailRecoveryGroup.style.display = 'block';
		phoneRecoveryGroup.style.display = 'none';
	} else if (recoveryOption.value === 'phone') {
		emailRecoveryGroup.style.display = 'none';
		phoneRecoveryGroup.style.display = 'block';
	}
});

var valorRecuperacao = 0;

function sendRecoveryCode() {
	const recoveryOption = document.getElementById('recovery-option').value;
	email1 = document.getElementById('recovery-email').value;

	if (recoveryOption === 'email' && !email1) {
		alert('Por favor, insira seu e-mail.');
		return;
	}
	valorRecuperacao = Math.round(Math.random() * (200 - 100) + 100);
	fetch("/emailSender/sendCodMor", {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: new URLSearchParams({
			titulo: "Código troca de senha",
			mensagem: "O código para validar a sua senha é: " + valorRecuperacao,
			email: email1
		})
	})
		.then(response => response.text())
		.then(data => window.alert(data))
		.catch(error => window.alert(error));


	document.getElementById('code-group').style.display = 'block';
	document.getElementById('send-code-button').style.display = 'none';
	document.getElementById('verify-code-button').style.display = 'inline-block';
}

document.getElementById('recoveryForm').addEventListener('submit', (e) => {
	e.preventDefault();

	const code = document.getElementById('recovery-code').value;
	const codeError = document.getElementById('code-error');
	console.log("3 texto " + valorRecuperacao);

	if (!code) {
		codeError.textContent = 'Por favor, insira o código de recuperação.';
		codeError.style.display = 'block';
		return;
	}

	if (code == valorRecuperacao) {
		codeError.style.display = 'none';
		document.getElementById('recovery-container').style.display = 'none';
		document.getElementById('reset-password-container').style.display = 'block';
	} else {
		codeError.textContent = 'Código inválido. Tente novamente.';
		codeError.style.display = 'block';
	}
});

document.getElementById('resetPasswordForm').addEventListener('submit', (e) => {
	e.preventDefault();

	const newPassword = document.getElementById('nova_senha').value;
	const confirmNewPassword = document.getElementById('confirm-new-password').value;
	const resetPasswordError = document.getElementById('reset-password-error');

	if (newPassword !== confirmNewPassword) {
		resetPasswordError.textContent = 'As senhas não coincidem.';
		resetPasswordError.style.display = 'block';
		return;
	}

	fetch("/updateSenha", {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: new URLSearchParams({
			nova_senha: newPassword,
			email: email1
		})
	})	
			.then(response => response.text())
			.then(data => window.alert(data))
			.catch(error => console.log(error));

	resetPasswordError.style.display = 'none';
	window.location.href = 'index.html';
});