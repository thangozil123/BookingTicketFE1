$ = document.querySelector.bind(document)
$$ = document.querySelectorAll.bind(document)
var list = $$('.category-item')
list.forEach((item,index) => {
    var nameItem = item.querySelector('.name-item')
    nameItem.onclick = function (){
        this.classList.toggle('category-item--active')
    }
});
