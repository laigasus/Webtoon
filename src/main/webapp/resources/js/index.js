const editor = document.querySelector(".editor");
editor.focus();
///editor에 focus맞춰서 입력이 일정하게 유지 


///////////////////////////////////////////////////
// const color = document.querySelector('.dropdown-color');
// const dropdowncontent_color = document.querySelector('.dropdown-content-color');
// color.addEventListener('click',(e)=>{
//     if(dropdowncontent_color.style.display=="block"){
//         dropdowncontent_color.style.display="none";
//     }else{
//         dropdowncontent_color.style.display="block";
//     }    

// });
// 글자 색 드롭다운
////////////////////////////////////////////

// const size = document.querySelector('.dropdown-fontsize');
// const dropdowncontent_size = document.querySelector('.dropdown-content-fontsize');
// size.addEventListener('click',(e)=>{
//     if(dropdowncontent_size.style.display=="block"){
//         dropdowncontent_size.style.display="none";
//     }else{
//         dropdowncontent_size.style.display="block";
//     }
// })
// 글자 크기 드롭다운
////////////////////////////////////////////////////


//input에서 받은 컬러의 값을 텍스트 창 font-color에 적용
let color = document.getElementById('textcolor')
color.value="#000000";
color.addEventListener('change',()=>{
  let changedColor = color.value;
  document.execCommand('foreColor',false,changedColor);
})

//font-color선택후 focus가 다시 text창으로 맞춰짐
color.addEventListener('click',()=>{
 editor.focus();	
})

$('.blue').click(()=>{document.getElementById("my-textarea").value=document.querySelector(".editor").innerHTML});


//fon-color 제외한 나머지 toolbar optino에서 지정한 데이터 값을 가져와서 command에 입력 후 execCommand로
//text창에 등록 
document.querySelectorAll('.toolbar a')
      .forEach(aEvent => aEvent.addEventListener('click', (e)=> {  
        e.preventDefault(); 
        const command = aEvent.dataset.command;

        if (command == 'h1' || command == 'h2' || command == 'h3' || command == 'p') { 
			document.execCommand('formatBlock', false, 'div');  
            document.execCommand('formatBlock', false, command);
        }
        else{
            document.execCommand(command);
        }
        editor.focus();
    
      }))

