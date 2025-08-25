// Gestion du personnel avec sauvegarde locale

const form = document.getElementById('employee-form');
const tableBody = document.querySelector('#employee-table tbody');

function getEmployees() {
    return JSON.parse(localStorage.getItem('employees') || '[]');
}

function saveEmployees(employees) {
    localStorage.setItem('employees', JSON.stringify(employees));
}

let currentAffectation = 'ALL';

function renderEmployees() {
    const employees = getEmployees();
    tableBody.innerHTML = '';
    const order = [
        "Cabinet",
        "Direction Securité Exterieure",
        "Direction Bangui",
        "Direction Prefectures"
    ];
    const fonctionOrder = [
        "Ministre",
        "Assistant",
        "Directeur",
        "Chef de Service",
        "Chef de Bureau"
    ];
    employees
        .filter(emp => currentAffectation === 'ALL' || formatAffectation(emp.affectation) === formatAffectation(currentAffectation))
        .sort((a, b) => { //Ici on trie par AFFECTATION
            const affA = order.indexOf(formatAffectation(a.affectation));
            const affB = order.indexOf(formatAffectation(b.affectation));
            if (affA !== affB) return affA - affB;
        })
		.sort((a, b) => { //Ici on trie par FONCTION
            const fA = fonctionOrder.indexOf(a.fonction);
            const fB = fonctionOrder.indexOf(b.fonction);
            if (fA === -1 && fB === -1) return 0;
            if (fA === -1) return 1;
            if (fB === -1) return -1;
            return fA - fB;
        })
        .forEach((emp, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${emp.name}</td>
                <td>${emp.firstname}</td>
                <td>${emp.email}</td>
                <td>${emp.phone || ''}</td>
                <td>${emp.status}</td>
                <td>${emp.startDate}</td>
                <td>${emp.endDate}</td>
                <td>${formatAffectation(emp.affectation)}</td>
                <td>${emp.fonction}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deleteEmployee(${index})">Supprimer</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
}

window.deleteEmployee = function(index) {
    const employees = getEmployees();
    employees.splice(index, 1);
    saveEmployees(employees);
    renderEmployees();
}

form.addEventListener('submit', function(e) {
    e.preventDefault();
    let name = document.getElementById('name').value.trim();
    let firstname = document.getElementById('firstname').value.trim();
    const email = document.getElementById('email').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const status = document.getElementById('status').value;
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    let affectation = document.getElementById('affectation').value;
    const fonction = document.getElementById('fonction').value;
    // Mise en forme du nom, prénom et affectation
    name = name.toUpperCase();
    firstname = firstname.charAt(0).toUpperCase() + firstname.slice(1).toLowerCase();
    affectation = affectation.split(' ').map(w => w.charAt(0).toUpperCase() + w.slice(1).toLowerCase()).join(' ');
    if (name && firstname && email && phone && status && (status === 'En Service' || (startDate && endDate)) && affectation && fonction) {
        const employees = getEmployees();
        // Vérification des doublons
        const exists = employees.some(emp => emp.name === name && emp.firstname === firstname);
        if (exists) {
            alert('Ces données Existent Déja dans la Base');
            return;
        }
        employees.push({ name, firstname, email, phone, status, startDate, endDate, affectation, fonction });
        saveEmployees(employees);
        renderEmployees();
        form.reset();
    }
});

// Gestion des visites officielles
const visitForm = document.getElementById('visit-form');
const visitTableBody = document.querySelector('#visit-table tbody');

function getVisits() {
    return JSON.parse(localStorage.getItem('visits') || '[]');
}

function saveVisits(visits) {
    localStorage.setItem('visits', JSON.stringify(visits));
}

function renderVisits() {
    const visits = getVisits();
    visitTableBody.innerHTML = '';
    visits.forEach((visit, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${visit.country}</td>
            <td>${visit.duration}</td>
            <td>${visit.visitStartDate}</td>
            <td>${visit.visitEndDate}</td>
            <td>${visit.purpose}</td>
            <td><button class="btn btn-danger btn-sm" onclick="deleteVisit(${index})">Supprimer</button></td>
        `;
        visitTableBody.appendChild(row);
    });
}

window.deleteVisit = function(index) {
    const visits = getVisits();
    visits.splice(index, 1);
    saveVisits(visits);
    renderVisits();
}

if (visitForm) {
    visitForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const country = document.getElementById('country').value.trim();
        const duration = document.getElementById('duration').value.trim();
        const visitStartDate = document.getElementById('visitStartDate').value;
        const visitEndDate = document.getElementById('visitEndDate').value;
        const purpose = document.getElementById('purpose').value.trim();
        if (country && duration && visitStartDate && visitEndDate && purpose) {
            const visits = getVisits();
            visits.push({ country, duration, visitStartDate, visitEndDate, purpose });
            saveVisits(visits);
            renderVisits();
            visitForm.reset();
        }
    });
}

// Gestion du filtrage par affectation
const affectationButtons = document.querySelectorAll('.direction-filter');
affectationButtons.forEach(btn => {
    btn.addEventListener('click', function() {
        currentAffectation = this.getAttribute('data-direction');
        renderEmployees();
    });
});

// Bouton Cabinet sous la liste du personnel
const filterCabinetBtn = document.getElementById('filter-cabinet');
if (filterCabinetBtn) {
    filterCabinetBtn.addEventListener('click', function() {
        currentAffectation = 'Cabinet';
        renderEmployees();
    });
}

// Fonction utilitaire pour la date formatée
function getFormattedDate() {
    const mois = ['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre'];
    const d = new Date();
    const jour = String(d.getDate()).padStart(2, '0');
    const moisNom = mois[d.getMonth()];
    const annee = d.getFullYear();
    return `Bangui le, ${jour} ${moisNom} ${annee}`;
}

// Fonction utilitaire pour l'en-tête imprimable
function getPrintableHeader() {
    return `
    <table style='width:100%;border-collapse:collapse;margin-bottom:20px;'>
      <tr>
        <th style='width:42%;text-align:center;font-size:1.1em;border:1px dotted #333;vertical-align:top;'>
          <div style='font-weight:bold;font-size:1.15em;margin-bottom:2px;'>PRESIDENCE DE LA REPUBLIQUE</div>
          <div>***************</div>
          <div style='font-weight:bold;'>DIRECTION DE CABINET</div>
          <div>***************</div>
          <div style='font-weight:bold;'>COORDINATION <br/>DE RENSEIGNEMENTS ET<br/> DE LA DOCUMENTATION D'ETAT.</div>
          <div>***********</div>
          <div style='font-weight:bold;'>SECRETARIAT PARTICULIER</div>
          <div>**********</div>
          <div style='margin-bottom:10px;'>
		  	<span style='text-decoration:underline;'>N°</span>
			<span>_______</span>
			<span style='text-decoration:underline;'>/PR/DIRCAB/CRDE/SP. 25</span>
		  </div>
        </th>
        <th style='width:15%;text-align:center;border:1px dotted #333;vertical-align:middle;align-items:center;justify-content:center;height:140px;padding:0;'>
          <img src='./blason.png' alt='Blason RCA' style='max-height:120px;max-width:90%;margin:auto;display:block;'>
        </th>
        <th style='width:40%;text-align:center;font-size:1.1em;border:1px dotted #333;vertical-align:top;'>
          <div style='font-weight:bold;font-size:1.15em;margin-bottom:2px;'>REPUBLIQUE CENTRAFRICAINE</div>
          <div>Unité – Dignité – Travail</div>
          <div>***********</div>
          <div style='height:40px;'></div>
        </th>
      </tr>
    </table>
    <div style='height:60px;'></div>
    `;
}

// Fonction utilitaire pour le pied de page
function getPrintableFooter() {
    return `
    <div style='width:80%;margin-top:40px; margin-right:10%;'>
      <div style='float:right;text-align:center;'>
        <span>Le Ministre Conseiller Spécial en charge</span><br/>
        <span>de la Coordination de Renseignements et </span><br/>
        <span>de la Documentation d'Etat</span><br/><br/><br/><br/>

        <span style='font-style:italic;'>Le Général d'Armée</span><br/>
        <span style='font-weight:bold;text-decoration:underline;font-size:1.1em;'>Henri WANZET LINGUISSARA</span>
      </div>
    </div>
    `;
}

// Impression principale et tri personnalisée
const printBtn = document.getElementById('print-personnel');
if (printBtn) {
    printBtn.addEventListener('click', function() {
        const employees = getEmployees();
        const order = [
            "Cabinet",
            "Direction Securité Exterieure",
            "Direction Bangui",
            "Direction Prefectures"
        ];
        const fonctionOrder = [
            "Ministre",
            "Assistant",
            "Directeur",
            "Chef de Service",
            "Chef de Bureau"
        ];
        const filtered = employees.filter(emp => currentAffectation === 'ALL' || formatAffectation(emp.affectation) === formatAffectation(currentAffectation))
			.sort((a, b) => { //Ici on trie par AFFECTATION
				const affA = order.indexOf(formatAffectation(a.affectation));
				const affB = order.indexOf(formatAffectation(b.affectation));
				if (affA !== affB) return affA - affB;
			})
			.sort((a, b) => { //Ici on trie par FONCTION
				const fA = fonctionOrder.indexOf(a.fonction);
				const fB = fonctionOrder.indexOf(b.fonction);
				if (fA === -1 && fB === -1) return 0;
				if (fA === -1) return 1;
				if (fB === -1) return -1;
				return fA - fB;
			});
        let tableHtml = `<h2 style="text-align:center;margin:0 0 40px 0;font-size:1.5em; text-decoration: underline;">LISTE DU PERSONNEL</h2><br> <table class='table table-striped' style="border: 1px solid black; border-collapse: collapse;"><thead><tr>` +
            `<th class='print-col-nom'>Nom</th><th class='print-col-nom'>Prénom</th><th class='print-col-email'>Email</th><th>Numéro de Téléphone</th><th>Statut</th><th class='print-col-fonction'>Affectation</th><th class='print-col-fonction'>Fonction</th></tr></thead><tbody>`;
        filtered.forEach(emp => {
            tableHtml += `<tr><td>${emp.name}</td><td>${emp.firstname}</td><td>${emp.email}</td><td>${emp.phone || ''}</td><td>${emp.status}</td><td>${formatAffectation(emp.affectation)}</td><td>${emp.fonction}</td></tr>`;
        });
        tableHtml += `</tbody></table>`;
        const win = window.open('', '', 'height=700,width=900');
        win.document.write('<html><head>');
        win.document.write('<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">');
        win.document.write('<style>td,th{border: 1px solid black; border-collapse: collapse; padding: 1px; margin: 1px;} .print-col-fonction{min-width:190px;padding-left:18px !important;padding-right:18px !important;} body{background:#f8fafc;position:relative;min-height:100vh;overflow-x:hidden;font-family:\'Times New Roman\',Times,serif;}body::before{content:"CRDE";position:fixed;top:50%;left:50%;width:100vw;height:100vh;transform:translate(-50%,-50%);font-size:10vw;color:rgba(44,62,80,0.07);font-weight:900;letter-spacing:1vw;z-index:0;pointer-events:none;user-select:none;display:flex;align-items:center;justify-content:center;}body::after{content:"";position:fixed;top:50%;left:50%;width:60vw;height:60vw;transform:translate(-50%,-50%);background:radial-gradient(circle at 50% 50%,rgba(80,180,255,0.12) 0%,rgba(80,180,255,0.08) 60%,transparent 80%),repeating-conic-gradient(rgba(80,180,255,0.15) 0deg 10deg,transparent 10deg 20deg);border-radius:50%;z-index:0;pointer-events:none;} .container, .card, .card-body, .table {position:relative;z-index:1;} .print-col-nom{min-width:150px;padding-left:18px !important;padding-right:18px !important;} .print-col-email{min-width:220px;padding-left:18px !important;padding-right:18px !important;}</style>');
        win.document.write('</head><body>');
        win.document.write(getPrintableHeader());
        win.document.write(tableHtml);
        win.document.write(getPrintableFooter());
        win.document.write('</body></html>');
        win.document.close();
        win.print();
    });
}

// Impression des congés
const printCongesBtn = document.getElementById('print-conges');
if (printCongesBtn) {
    printCongesBtn.addEventListener('click', function() {
        const employees = getEmployees();
        const order = [
            "Cabinet",
            "Direction Securité Exterieure",
            "Direction Bangui",
            "Direction Prefectures"
        ];
        const fonctionOrder = [
            "Ministre",
            "Assistant",
            "Directeur",
            "Chef de Service",
            "Chef de Bureau"
        ];
        const filtered = employees.filter(emp => emp.status === 'Congé')
			.sort((a, b) => { //Ici on trie par AFFECTATION
				const affA = order.indexOf(formatAffectation(a.affectation));
				const affB = order.indexOf(formatAffectation(b.affectation));
				if (affA !== affB) return affA - affB;
			})
			.sort((a, b) => { //Ici on trie par FONCTION
				const fA = fonctionOrder.indexOf(a.fonction);
				const fB = fonctionOrder.indexOf(b.fonction);
				if (fA === -1 && fB === -1) return 0;
				if (fA === -1) return 1;
				if (fB === -1) return -1;
				return fA - fB;
			});
        let tableHtml = `<h2 style="text-align:center;margin:0 0 40px 0;font-size:1.5em; text-decoration: underline;">LISTE DU PERSONNEL EN CONGÉ</h2><br> <table class='table table-striped' style="border: 1px solid black; border-collapse: collapse;"><thead><tr>` +
            `<th class='print-col-nom'>Nom</th><th class='print-col-nom'>Prénom</th><th class='print-col-email'>Email</th><th>Numéro de Téléphone</th><th>Statut</th><th>Date début</th><th>Date fin</th><th class='print-col-fonction'>Affectation</th><th class='print-col-fonction'>Fonction</th></tr></thead><tbody>`;
        filtered.forEach(emp => {
            tableHtml += `<tr><td>${emp.name}</td><td>${emp.firstname}</td><td>${emp.email}</td><td>${emp.phone || ''}</td><td>${emp.status}</td><td>${emp.startDate}</td><td>${emp.endDate}</td><td>${formatAffectation(emp.affectation)}</td><td>${emp.fonction}</td></tr>`;
        });
        tableHtml += `</tbody></table>`;
        const win = window.open('', '', 'height=700,width=900');
        win.document.write('<html><head>');
        win.document.write('<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">');
        win.document.write('<style>td,th{border: 1px solid black; border-collapse: collapse; padding: 1px; margin: 1px;}.print-col-fonction{min-width:190px;padding-left:18px !important;padding-right:18px !important;} .print-col-nom{min-width:150px;padding-left:18px !important;padding-right:18px !important;} .print-col-email{min-width:220px;padding-left:18px !important;padding-right:18px !important;}</style>');
        win.document.write('</head><body>');
        win.document.write(getPrintableHeader());
        win.document.write(tableHtml);
        win.document.write(getPrintableFooter());
        win.document.write('</body></html>');
        win.document.close();
        win.print();
    });
}

// Impression des missions
const printMissionsBtn = document.getElementById('print-missions');
if (printMissionsBtn) {
    printMissionsBtn.addEventListener('click', function() {
        const employees = getEmployees();
        const order = [
            "Cabinet",
            "Direction Securité Exterieure",
            "Direction Bangui",
            "Direction Prefectures"
        ];
        const fonctionOrder = [
            "Ministre",
            "Assistant",
            "Directeur",
            "Chef de Service",
            "Chef de Bureau"
        ];
        const filtered = employees.filter(emp => emp.status === 'Mission')
			.sort((a, b) => { //Ici on trie par AFFECTATION
				const affA = order.indexOf(formatAffectation(a.affectation));
				const affB = order.indexOf(formatAffectation(b.affectation));
				if (affA !== affB) return affA - affB;
			})
			.sort((a, b) => { //Ici on trie par FONCTION
				const fA = fonctionOrder.indexOf(a.fonction);
				const fB = fonctionOrder.indexOf(b.fonction);
				if (fA === -1 && fB === -1) return 0;
				if (fA === -1) return 1;
				if (fB === -1) return -1;
				return fA - fB;
			});
        let tableHtml = `<h2 style="text-align:center;margin:0 0 40px 0;font-size:1.5em;font-weight:bold;text-decoration: underline;">LISTE DU PERSONNEL EN MISSION</h2><br> <table class='table table-striped' style="border: 1px solid black; border-collapse: collapse;"><thead><tr>` +
            `<th class='print-col-nom'>Nom</th><th class='print-col-nom'>Prénom</th><th class='print-col-email'>Email</th><th>Numéro de Téléphone</th><th>Statut</th><th>Date début</th><th>Date fin</th><th class='print-col-fonction'>Affectation</th><th class='print-col-fonction'>Fonction</th></tr></thead><tbody>`;
        filtered.forEach(emp => {
            tableHtml += `<tr><td>${emp.name}</td><td>${emp.firstname}</td><td>${emp.email}</td><td>${emp.phone || ''}</td><td>${emp.status}</td><td>${emp.startDate}</td><td>${emp.endDate}</td><td>${formatAffectation(emp.affectation)}</td><td>${emp.fonction}</td></tr>`;
        });
        tableHtml += `</tbody></table>`;
        const win = window.open('', '', 'height=700,width=900');
        win.document.write('<html><head>');
        win.document.write('<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">');
        win.document.write('<style>td,th{border: 1px solid black; border-collapse: collapse; padding: 1px; margin: 1px;} .print-col-fonction{min-width:190px;padding-left:18px !important;padding-right:18px !important;} .print-col-nom{min-width:150px;padding-left:18px !important;padding-right:18px !important;} .print-col-email{min-width:220px;padding-left:18px !important;padding-right:18px !important;}</style>');
        win.document.write('</head><body>');
        win.document.write(getPrintableHeader());
        win.document.write(tableHtml);
        win.document.write(getPrintableFooter());
        win.document.write('</body></html>');
        win.document.close();
        win.print();
    });
}

// Impression des stagiaires
const printStagiairesBtn = document.getElementById('print-stagiaires');
if (printStagiairesBtn) {
    printStagiairesBtn.addEventListener('click', function() {
        const employees = getEmployees();
        const order = [
            "Cabinet",
            "Direction Securité Exterieure",
            "Direction Bangui",
            "Direction Prefectures"
        ];
        const fonctionOrder = [
            "Ministre",
            "Assistant",
            "Directeur",
            "Chef de Service",
            "Chef de Bureau"
        ];
        const filtered = employees.filter(emp => emp.status === 'Stage')
			.sort((a, b) => { //Ici on trie par AFFECTATION
            const affA = order.indexOf(formatAffectation(a.affectation));
            const affB = order.indexOf(formatAffectation(b.affectation));
            if (affA !== affB) return affA - affB;
        })
		.sort((a, b) => { //Ici on trie par FONCTION
            const fA = fonctionOrder.indexOf(a.fonction);
            const fB = fonctionOrder.indexOf(b.fonction);
            if (fA === -1 && fB === -1) return 0;
            if (fA === -1) return 1;
            if (fB === -1) return -1;
            return fA - fB;
        });
        let tableHtml = `<h2 style="text-align:center;margin:0 0 40px 0;font-size:1.5em;font-weight:bold;text-decoration: underline;">LISTE DES STAGIAIRES</h2><br> <table class='table table-striped'><thead><tr>` +
            `<th class='print-col-nom'>Nom</th><th class='print-col-nom'>Prénom</th><th class='print-col-email'>Email</th><th>Numéro de Téléphone</th><th>Statut</th><th>Date début</th><th>Date fin</th><th class='print-col-fonction'>Affectation</th><th class='print-col-fonction'>Fonction</th></tr></thead><tbody>`;
        filtered.forEach(emp => {
            tableHtml += `<tr><td>${emp.name}</td><td>${emp.firstname}</td><td>${emp.email}</td><td>${emp.phone || ''}</td><td>${emp.status}</td><td>${emp.startDate}</td><td>${emp.endDate}</td><td>${formatAffectation(emp.affectation)}</td><td>${emp.fonction}</td></tr>`;
        });
        tableHtml += `</tbody></table>`;
        const win = window.open('', '', 'height=700,width=900');
        win.document.write('<html><head>');
        win.document.write('<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">');
        win.document.write('<style>td,th{border: 1px solid black; border-collapse: collapse; padding: 1px; margin: 1px;} .print-col-fonction{min-width:190px;padding-left:18px !important;padding-right:18px !important;} .print-col-nom{min-width:150px;padding-left:18px !important;padding-right:18px !important;} .print-col-email{min-width:220px;padding-left:18px !important;padding-right:18px !important;}</style>');
        win.document.write('</head><body>');
        win.document.write(getPrintableHeader());
        win.document.write(tableHtml);
        win.document.write(getPrintableFooter());
        win.document.write('</body></html>');
        win.document.close();
        win.print();
    });
}

document.addEventListener('DOMContentLoaded', function() {
    renderEmployees();
    renderVisits();
});

// --- Gestion du mot de passe ---
const loginContainer = document.getElementById('login-container');
const mainApp = document.getElementById('main-app');
const mainAppHeader = document.getElementById('main-app-h');
const appHeaderNav = document.getElementById('app-h-nav');
const appHeader = document.getElementById('app-h');
const loginForm = document.getElementById('login-form');
const loginPassword = document.getElementById('login-password');
const loginError = document.getElementById('login-error');
const openChangePasswordBtn = document.getElementById('open-change-password');
const changePasswordModal = document.getElementById('change-password-modal');
const changePasswordForm = document.getElementById('change-password-form');
const oldPasswordInput = document.getElementById('old-password');
const newPasswordInput = document.getElementById('new-password');
const confirmPasswordInput = document.getElementById('confirm-password');
const changePasswordError = document.getElementById('change-password-error');
const loginUsername = document.getElementById('login-username');
const newUsernameInput = document.getElementById('new-username');

function getPassword() {
    return localStorage.getItem('admin_password') || 'admin123';
}

function setPassword(newPass) {
    localStorage.setItem('admin_password', newPass);
}

function getUsername() {
    return localStorage.getItem('admin_username') || 'admin';
}

function setUsername(newUser) {
    localStorage.setItem('admin_username', newUser);
}

function showApp() {
    loginContainer.style.display = 'none';
    mainApp.style.display = '';
	//mainAppHeader.style.display = '';
	//mainAppHeader.style.marginBottom = '1em';
	appHeader.style.display = '';
	appHeader.style.marginBottom = '1em';
	appHeaderNav.style.display = '';
    openChangePasswordBtn.style.display = '';
}

function showLogin() {
    loginContainer.style.display = '';
    mainApp.style.display = 'none';
	//mainAppHeader.style.display = 'none';
	appHeaderNav.style.display = 'none';
	appHeader.style.display = '';
	appHeader.style.marginBottom = '1em';
    openChangePasswordBtn.style.display = 'none';
}

if (loginForm) {
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        if (loginUsername.value.trim() !== getUsername()) {
            loginError.textContent = "Nom d'utilisateur incorrect";
            loginError.style.display = '';
            return;
        }
        if (loginPassword.value === getPassword()) {
            showApp();
            loginPassword.value = '';
            loginUsername.value = '';
            loginError.style.display = 'none';
        } else {
            loginError.textContent = 'Mot de passe incorrect';
            loginError.style.display = '';
        }
    });
}

if (openChangePasswordBtn) {
    openChangePasswordBtn.addEventListener('click', function() {
        // Afficher la modale Bootstrap
        var modal = new bootstrap.Modal(document.getElementById('change-password-modal'));
        changePasswordForm.reset();
        changePasswordError.style.display = 'none';
        modal.show();
    });
}

if (changePasswordForm) {
    changePasswordForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const oldPass = oldPasswordInput.value;
        const newPass = newPasswordInput.value;
        const confirmPass = confirmPasswordInput.value;
        const newUser = newUsernameInput.value.trim();
        if (oldPass !== getPassword()) {
            changePasswordError.textContent = 'Ancien mot de passe incorrect';
            changePasswordError.style.display = '';
            return;
        }
        if (newPass && newPass.length < 4) {
            changePasswordError.textContent = 'Le nouveau mot de passe doit contenir au moins 4 caractères';
            changePasswordError.style.display = '';
            return;
        }
        if (newPass && newPass !== confirmPass) {
            changePasswordError.textContent = 'Les mots de passe ne correspondent pas';
            changePasswordError.style.display = '';
            return;
        }
        if (newPass) {
            setPassword(newPass);
        }
        if (newUser) {
            setUsername(newUser);
        }
        changePasswordError.style.display = 'none';
        var modal = bootstrap.Modal.getInstance(document.getElementById('change-password-modal'));
        modal.hide();
        alert('Identifiants modifiés avec succès !');
    });
}

// Afficher l'app si déjà connecté (optionnel, sinon toujours demander le mot de passe)
showLogin();

function formatAffectation(aff) {
    return aff.split(' ').map(w => w.charAt(0).toUpperCase() + w.slice(1).toLowerCase()).join(' ');
}