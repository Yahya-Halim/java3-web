
<style>
    body {
      font-family: Arial, sans-serif;
      display: flex;
      flex-direction: column;
      align-items: center;
      background-color: #f0f0f0;
      padding: 20px;
    }

    h1 {
      color: #333;
      margin-bottom: 20px;
    }

    .game-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 20px;
    }

    .game-board {
      display: grid;
      grid-template-columns: repeat(7, 60px);
      grid-template-rows: repeat(6, 60px);
      gap: 5px;
      background-color: #1a73e8;
      padding: 10px;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    .cell {
      width: 60px;
      height: 60px;
      background-color: white;
      border-radius: 50%;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .cell:hover {
      background-color: #e0e0e0;
    }

    .cell.player1 {
      background-color: #ff5252;
    }

    .cell.player2 {
      background-color: #ffeb3b;
    }

    .status {
      font-size: 1.2em;
      font-weight: bold;
      padding: 10px 20px;
      border-radius: 5px;
      background-color: white;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .player1-turn {
      color: #ff5252;
    }

    .player2-turn {
      color: #ffeb3b;
    }

    .restart-btn {
      padding: 10px 20px;
      background-color: #1a73e8;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 1em;
      transition: background-color 0.3s;
    }

    .restart-btn:hover {
      background-color: #0d5bba;
    }
</style>

<body>
  <h1>Five Bros Connect Four</h1>
  <div class="game-container">
    <div class="status player1-turn">Player 1's Turn</div>
    <div class="game-board" id="gameBoard"></div>
    <button class="restart-btn" id="restartBtn">Restart Game</button>
  </div>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    const gameBoard = document.getElementById('gameBoard');
    const statusDisplay = document.querySelector('.status');
    const restartBtn = document.getElementById('restartBtn');

    let currentPlayer = 1;
    let socket;

    // Initialize the game board
    function initializeBoard() {
      gameBoard.innerHTML = '';
      for (let row = 0; row < 6; row++) {
        for (let col = 0; col < 7; col++) {
          const cell = document.createElement('div');
          cell.classList.add('cell');
          cell.dataset.row = row;
          cell.dataset.col = col;
          cell.addEventListener('click', () => handleCellClick(col));
          gameBoard.appendChild(cell);
        }
      }
    }

    // Connect to WebSocket
    function connectWebSocket() {
      const protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
      const host = window.location.host;
      socket = new WebSocket(`${protocol}${host}/game`);

      socket.onopen = function() {
        console.log('WebSocket connection established');
      };

      socket.onmessage = function(event) {
        const gameState = JSON.parse(event.data);
        updateBoard(gameState);
        updateStatus(gameState.currentPlayer);
      };

      socket.onclose = function() {
        console.log('WebSocket connection closed');
        // Try to reconnect after a delay
        setTimeout(connectWebSocket, 1000);
      };

      socket.onerror = function(error) {
        console.error('WebSocket error:', error);
      };
    }

    // Handle cell click
    function handleCellClick(column) {
      if (socket && socket.readyState === WebSocket.OPEN) {
        const move = {
          column: column,
          player: currentPlayer
        };
        socket.send(JSON.stringify(move));
      }
    }

    // Update the board based on game state
    function updateBoard(gameState) {
      const cells = document.querySelectorAll('.cell');
      gameState.board.forEach((row, rowIndex) => {
        row.forEach((cellValue, colIndex) => {
          const cell = cells[rowIndex * 7 + colIndex];
          cell.className = 'cell';
          if (cellValue === 1) {
            cell.classList.add('player1');
          } else if (cellValue === 2) {
            cell.classList.add('player2');
          }
        });
      });
    }

    // Update status display
    function updateStatus(player) {
      currentPlayer = player;
      statusDisplay.className = 'status';
      if (player === 1) {
        statusDisplay.textContent = "Player 1's Turn";
        statusDisplay.classList.add('player1-turn');
      } else {
        statusDisplay.textContent = "Player 2's Turn";
        statusDisplay.classList.add('player2-turn');
      }
    }

    // Restart game
    restartBtn.addEventListener('click', function() {
      if (socket && socket.readyState === WebSocket.OPEN) {
        // You'll need to implement a restart endpoint on the server
        fetch('/game?restart=true', { method: 'POST' })
                .then(response => response.json())
                .then(data => {
                  updateBoard(data);
                  updateStatus(1);
                });
      }
    });

    // Initialize the game
    initializeBoard();
    connectWebSocket();

    // Fetch initial game state
    fetch('/game')
            .then(response => response.json())
            .then(data => {
              updateBoard(data);
              updateStatus(data.currentPlayer);
            });
  });
</script>
</body>
