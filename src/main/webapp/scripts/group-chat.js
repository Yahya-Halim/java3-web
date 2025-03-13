let wsProtocol = "ws://";
if(window.location.protocol === "https:") {
    wsProtocol = "wss://";
}
const wsUri = wsProtocol + document.location.host + document.location.pathname + "/endpoint";
const websocket = new WebSocket(wsUri);
const messageForm = document.getElementById("messageForm");
const errorText = document.getElementById("errorText");
const userNameField = document.getElementById("userName");
const messageInputField = document.getElementById("messageInput");
const messageOutput = document.getElementById("messageOutput");

// This function will run when we connect to the websock
websocket.onopen = function(event) {
    // console.log("Opened websocket: " + wsUri)
};

// This function will run when we receive a message from another user
websocket.onmessage = function(event) {
    updateMessageOutput(event.data, "in");
};

function updateMessageOutput(data, inOut) {
    const json = JSON.parse(data);
    const name = json.name;
    const message = json.message;
    let result = (inOut === "in") ? '<div class="in">' : '<div class="out">';
    result += `<p>${message}</p>`;
    result += `<span>${name}</span>`;
    result += "</div>";
    messageOutput.innerHTML += result;
    messageOutput.scrollTop = messageOutput.scrollHeight;
    // Extra Credit: Write code to only scroll down if the scrollbar was already at the bottom.
    // This can be done by checking if the scrollHeight is equal to the clientHeight (viewport height)
    // and the scrollTop is at the bottom (scrollHeight - clientHeight = 0).
    if(messageOutput.scrollHeight - messageOutput.clientHeight === messageOutput.scrollTop) {
        messageOutput.scrollHeight;
    }
}

function displayError(msg) {
    errorText.innerText = msg;
    errorText.classList.remove("d-none");
}

function resetErrorMessage() {
    errorText.innerText = "";
    errorText.classList.add("d-none");
}

// This function runs when we send a message
messageForm.addEventListener("submit", function(event) {
    event.preventDefault(); // Prevents the browser from submitting to an HTTP server
    resetErrorMessage();

    // Just like a servlet, get the data and validate it.
    const userName = userNameField.value;
    if(userName === null || userName === "") {
        displayError("Name is required");
        return;
    }
    const messageInput = messageInputField.value;
    if(messageInput === null || messageInput === "") {
        displayError("Message is required");
        return;
    }

    const json = JSON.stringify({"name":userName,"message":messageInput});
    sendMessage(json);
    resetMessageInput();
    updateMessageOutput(json, "out");
});

function resetMessageInput() {
    messageInputField.value = "";
    messageInputField.focus();
}

function sendMessage(json) {
    if(websocket.readyState === websocket.OPEN) {
        websocket.send(json);
    }
}