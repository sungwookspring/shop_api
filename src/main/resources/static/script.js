const navbarBtn = document.querySelector(".navbar_Btn");
const navbarmenu = document.querySelector(".navbar_menu");
const navbaruser = document.querySelector(".navbar_user");

navbarBtn.addEventListener('click', ()=>{
    navbarmenu.classList.toggle('active');
    navbaruser.classList.toggle('active');
});