<!-- Chat Widget Container -->
<div class="fixed bottom-6 right-6 z-50">
    <!-- Chat Toggle Button -->
    <button id="chatToggle" class="bg-blue-600 text-white rounded-full p-4 shadow-lg hover:bg-blue-700 transition-all">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
        </svg>
    </button>

    <!-- Chat Window -->
    <div id="chatWindow" class="hidden bg-white rounded-lg shadow-lg w-96 h-[500px] flex flex-col">
        <!-- Chat Header -->
        <div class="bg-blue-600 text-white p-4 rounded-t-lg flex justify-between items-center">
            <h2 class="text-lg font-semibold">Chat</h2>
            <button id="closeChat" class="text-white hover:text-gray-200">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </button>
        </div>

        <!-- Chat Messages -->
        <div id="messageOutput" class="flex-1 p-4 overflow-y-auto bg-gray-50">
            <!-- Messages will appear here -->
        </div>

        <!-- Chat Input Form -->
        <form id="messageForm" class="p-4 border-t border-gray-200">
            <div class="mb-4">
                <input type="text" id="userName" placeholder="Your Name" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div class="mb-4">
                <textarea id="messageInput" rows="3" placeholder="Type your message..." class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
            </div>
            <button type="submit" class="w-full bg-blue-600 text-white p-2 rounded-lg hover:bg-blue-700 transition-all">Send</button>
        </form>

        <!-- Error Notification -->
        <div id="errorText" class="hidden bg-red-100 border border-red-400 text-red-700 px-4 py-2 rounded-lg m-4"></div>
    </div>
</div>

<script>
    // WebSocket Configuration
    let wsProtocol = "ws://";
    if (window.location.protocol === "https:") {
        wsProtocol = "wss://";
    }
    const wsUri = wsProtocol + document.location.host + "/chat";
    const websocket = new WebSocket(wsUri);

    // DOM Elements
    const chatToggle = document.getElementById("chatToggle");
    const chatWindow = document.getElementById("chatWindow");
    const closeChat = document.getElementById("closeChat");
    const messageForm = document.getElementById("messageForm");
    const errorText = document.getElementById("errorText");
    const userNameField = document.getElementById("userName");
    const messageInputField = document.getElementById("messageInput");
    const messageOutput = document.getElementById("messageOutput");

    // Toggle Chat Window
    chatToggle.addEventListener("click", () => {
        chatWindow.classList.toggle("hidden");
    });

    // Close Chat Window
    closeChat.addEventListener("click", () => {
        chatWindow.classList.add("hidden");
    });

    // WebSocket Event Handlers
    websocket.onopen = function(event) {
        console.log("WebSocket connection established.");
    };

    websocket.onmessage = function(event) {
        updateMessageOutput(event.data, "in");
    };

    websocket.onerror = function(event) {
        displayError("WebSocket error. Please try again.");
    };

    websocket.onclose = function(event) {
        displayError("WebSocket connection closed. Refresh the page to reconnect.");
    };

    // Update Chat Messages
    function updateMessageOutput(data, inOut) {
        const json = JSON.parse(data);
        const name = DOMPurify.sanitize(json.name);
        const message = DOMPurify.sanitize(json.message);
        const messageClass = inOut === "in" ? "bg-gray-200" : "bg-blue-600 text-white";
        const alignment = inOut === "in" ? "justify-start" : "justify-end";

        const messageElement = `
                <div class="flex ${alignment} mb-4">
                    <div class="max-w-[70%] p-3 rounded-lg ${messageClass}">
                        <p>${message}</p>
                        <span class="text-xs text-gray-500">${name}</span>
                    </div>
                </div>
            `;
        messageOutput.innerHTML += messageElement;
        messageOutput.scrollTop = messageOutput.scrollHeight;
    }

    // Display Error Messages
    function displayError(msg) {
        errorText.innerText = msg;
        errorText.classList.remove("hidden");
    }

    // Reset Error Messages
    function resetErrorMessage() {
        errorText.innerText = "";
        errorText.classList.add("hidden");
    }

    // Handle Form Submission
    messageForm.addEventListener("submit", function(event) {
        event.preventDefault();
        resetErrorMessage();

        const userName = userNameField.value.trim();
        if (!userName) {
            displayError("Name is required");
            return;
        }

        const messageInput = messageInputField.value.trim();
        if (!messageInput) {
            displayError("Message is required");
            return;
        }

        const json = JSON.stringify({ name: userName, message: messageInput });
        sendMessage(json);
        resetMessageInput();
        updateMessageOutput(json, "out");
    });

    // Reset Message Input
    function resetMessageInput() {
        messageInputField.value = "";
        messageInputField.focus();
    }

    // Send Message via WebSocket
    function sendMessage(json) {
        if (websocket.readyState === WebSocket.OPEN) {
            websocket.send(json);
        } else {
            displayError("WebSocket connection is not open. Please refresh the page.");
        }
    }
</script>