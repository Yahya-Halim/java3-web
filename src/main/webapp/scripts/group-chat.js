var wsProtocol = 'ws://';
if (window.location.protocol === 'https:') {
    wsProtocol = 'wss://';
}
const wsUri = wsProtocol + document.location.host + document.location.pathname + "/endpoint";
const websocket = new WebSocket(wsUri);

websocket.onopen = function (event) {
    // console.log("opened websocket: " + wsUri);
};

websocket.onmessage = function (event) {
    // this function is called when we receive a message from another user
    updateMessages(event.data, "in");
};

function updateMessages(data, inOut) {
    const json = JSON.parse(data);
    const name = json.name;
    const message = json.message;
    let result = (inOut === "in") ? '<div class="in">' : '<div class="out">';
    result += `<p>${message}</p>`;
    result += `<span>${(inOut === "in") ? name : "Me"}</span>`;
    result += "</div>";
    const messageBox = document.getElementById("messages");
    messageBox.innerHTML += result;
    // This code auto scrolls the message box to the bottom when a new message comes in.
    // TODO: Extra Credit, only scroll to the bottom if the scrollbar is already at the bottom.
    // Don't scroll down if the user has scrolled up.
    messageBox.scrollTop = messageBox.scrollHeight;
}

function displayError(msg) {
    const errorText = document.getElementById("errorText");
    errorText.innerText = msg;
    errorText.classList.remove("d-none"); // Displays the errors message
}

const messageForm = document.getElementById("messageForm");
messageForm.addEventListener("submit", function(event) {
    event.preventDefault(); // Do not send WS data to a servlet
    const errorText = document.getElementById("errorText");
    errorText.classList.add("d-none"); // Hides the errors message
    errorText.innerText = ""; // Resets the previous error message
    const userName = document.getElementById("userName").value;
    if(userName === "") {
        displayError("Name is required");
        return;
    }

    const message = document.getElementById("message").value;
    if(message === "") {
        displayError("Message is required");
        return;
    }

    // Both inputs are valid
    const json = JSON.stringify({"name": userName, "message": message});
    sendMessage(json);
    resetMessageBox();
    updateMessages(json, "out");
});

function sendMessage(json) {
    // this function is called when we send a message to other users
    if(websocket.readyState === websocket.OPEN) {
        websocket.send(json);
    }
}

function resetMessageBox() {
    const message = document.getElementById("message");
    message.value = ""; // removes text from message box
    message.focus(); // keeps cursor in message box
}