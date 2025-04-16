<!-- Chat Widget Container -->
<!-- Chat Toggle Button -->
<div class="fixed bottom-6 right-6 z-50">
    <button id="chatToggle" class="bg-[#0077b5] text-white rounded-full p-4 shadow-lg hover:bg-[#005f8a] transition-all">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
        </svg>
    </button>
</div>

<!-- Chat Widget -->
<div id="chatWidget" class="fixed bottom-20 right-6 z-40 hidden transition-all duration-300 ease-in-out">

    <div class="bg-white rounded-lg shadow-lg w-96 h-[500px] flex flex-col overflow-hidden">
        <!-- Chat Header -->
        <div class="bg-[#0077b5] text-white p-4 flex items-center justify-between">
            <div class="flex items-center space-x-3">
                <img src="https://via.placeholder.com/40" alt="Profile" class="w-10 h-10 rounded-full">
                <div>

<h2                     class="font-semibold">
                            Chat<c:if test="${not empty activeUser.firstName}">, ${fn:escapeXml(activeUser.firstName)}</c:if>
                    </h2>
                </div>
            </div>
            <button id="closeChat" class="text-gray-200 hover:text-white">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </button>
        </div>

        <!-- Message List -->
        <div id="messageList" class="message-list flex-1 p-4 overflow-y-auto bg-gray-50">
            <!-- Messages will appear here dynamically -->
        </div>

        <!-- Chat Input Area -->
        <div class="p-4 border-t border-gray-200">
            <form id="messageForm" class="flex flex-col gap-2">
                <!-- Hidden or replace with actual name input -->
                <input type="hidden" id="userName" value="${fn:escapeXml(activeUser.firstName)}" />

                <div class="flex items-center space-x-2">
                    <input type="text" id="message" placeholder="Type a message..."
                           class="flex-1 p-2 border border-gray-300 rounded-lg text-black placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#0077b5]">
                    <button type="submit"
                            class="bg-[#0077b5] text-white p-2 rounded-lg hover:bg-[#005f8a] transition-all">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                             stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                  d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"></path>
                        </svg>
                    </button>
                </div>

                <p id="errorText" class="text-red-500 text-sm hidden"></p>
            </form>
        </div>
    </div>
</div>


<style>
    .message-list::-webkit-scrollbar {
        width: 6px;
    }
    .message-list::-webkit-scrollbar-track {
        background: #f1f1f1;
    }
    .message-list::-webkit-scrollbar-thumb {
        background: #888;
        border-radius: 3px;
    }
    .message-list::-webkit-scrollbar-thumb:hover {
        background: #555;
    }
</style>
<script>
    const chatToggle = document.getElementById('chatToggle');
    const chatWidget = document.getElementById('chatWidget');
    const closeChat = document.getElementById('closeChat');

    chatToggle.addEventListener('click', () => {
        chatWidget.classList.toggle('hidden');
    });

    closeChat.addEventListener('click', () => {
        chatWidget.classList.add('hidden');
    });
</script>
