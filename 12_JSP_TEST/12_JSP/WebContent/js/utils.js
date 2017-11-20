
function validateNick() {
	var nick = loginForm.nick.value;
	var pattern = /[\w\S\d.\-]+@[\w\S\d.\-]+\.[a-z]+/;
	var valid = pattern.test(nick);
	if (!valid) {
		loginForm.nick.className = "TextInput ErrorInput";
		loginForm.nick.value = "Nick '" + nick + "' is't valid";
	}
	return valid;
}
