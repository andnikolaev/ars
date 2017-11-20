document.onload = scrollDown();

function scrollDown() {
	var objDiv = document.getElementById("chat_mes");
	objDiv.scrollTop = objDiv.scrollHeight;
}

document.querySelector('.user_list').addEventListener('click', e => {
	  // e.target - целевой элемент
	  let content = e.target.innerHTML;
	  
	  document.getElementById('inputMsg').value = `${content}, `;
	  document.getElementById('inputMsg').focus();
	  console.info(`Содержимое элемента: "${content}"!`);
	});