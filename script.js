const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnlogin-popup');
const iconClose = document.querySelector('.icon-close');

registerLink.addEventListener('click', ()=>{
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', ()=>{
    wrapper.classList.remove('active');
}); 

btnPopup.addEventListener('click',()=>{
    wrapper.classList.add('active-popup');
});

iconClose.addEventListener('click', ()=>{
    wrapper.classList.remove('active-popup');
}); 
function triggerFileInput() {
    document.getElementById('fileInput').click();
}
// get file input
function displayFileName() {
    const fileInput = document.getElementById('fileInput');
    const fileNameDisplay = document.getElementById('fileNameDisplay');
    
    // Get the file name from the file input
    const file = fileInput.files[0];

    if (file) {
        fileNameDisplay.textContent = 'Selected File: ' + file.name;
    } else {
        fileNameDisplay.textContent = 'No file selected';
    }
}
// contact page
function showPage(pageId) {
    const pages = document.querySelectorAll('.container');
    pages.forEach(page => page.style.display = 'none');
    document.getElementById(pageId).style.display = 'block';
}