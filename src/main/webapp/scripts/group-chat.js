// WebSocket setup
var wsProtocol = 'ws://';
if (window.location.protocol === 'https:') {
    wsProtocol = 'wss://';
}
const wsUri = wsProtocol + document.location.host + document.location.pathname + "/endpoint";
const websocket = new WebSocket(wsUri);

websocket.onopen = function (event) {
    console.log("WebSocket opened:", wsUri);
};

websocket.onmessage = function (event) {
    updateMessages(event.data, "in");
};

// Message rendering
function updateMessages(data, inOut) {
    const json = JSON.parse(data);
    const name = json.name;
    const message = json.message;

    let bubble = document.createElement('div');
    bubble.className = (inOut === "in") ? "mb-2 p-3 bg-gray-200 rounded-lg w-fit" : "mb-2 p-3 bg-blue-500 text-white rounded-lg w-fit self-end";
    bubble.innerHTML = `
            <p class="text-sm">${message}</p>
            <span class="block text-xs text-gray-500 mt-1">${inOut === "in" ? name : "Me"}</span>
        `;

    const messageBox = document.getElementById("messages");
    messageBox.appendChild(bubble);
    messageBox.scrollTop = messageBox.scrollHeight;
}

function displayError(msg) {
    const errorText = document.getElementById("errorText");
    errorText.innerText = msg;
    errorText.classList.remove("hidden");
}

function resetMessageBox() {
    const message = document.getElementById("message");
    message.value = "";
    message.focus();
}

// Form submit
const messageForm = document.getElementById("messageForm");
messageForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const errorText = document.getElementById("errorText");
    errorText.classList.add("hidden");
    errorText.innerText = "";

    const userName = document.getElementById("userName").value.trim();
    const message = document.getElementById("message").value.trim();

    if (userName === "") {
        displayError("Name is required");
        return;
    }

    if (message === "") {
        displayError("Message is required");
        return;
    }

    const json = JSON.stringify({ name: userName, message: message });
    sendMessage(json);
    updateMessages(json, "out");
    resetMessageBox();
});

function sendMessage(json) {
    if (websocket.readyState === WebSocket.OPEN) {
        websocket.send(json);
    }
}