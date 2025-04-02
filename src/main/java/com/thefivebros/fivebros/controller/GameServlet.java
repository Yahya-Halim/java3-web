package com.thefivebros.fivebros.controller;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.OnClose;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static GameState gameState = new GameState();
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Serve the HTML page for root path
        if (req.getRequestURI().equals("/game") || req.getRequestURI().equals("/WEB-INF/connect-4-row.jsp")) {
            resp.setContentType("text/html");
            resp.getWriter().write(getHtmlContent());
            return;
        }

        // Handle API requests
        if (req.getRequestURI().equals("/game")) {
            resp.setContentType("application/json");
            resp.getWriter().write(gameState.toJson().toString());
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    private String getHtmlContent() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Connect Four</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }\n" +
                "        #gameBoard { display: inline-block; background-color: blue; padding: 10px; border-radius: 10px; }\n" +
                "        .row { display: flex; }\n" +
                "        .cell { width: 50px; height: 50px; margin: 5px; border-radius: 50%; background-color: white; cursor: pointer; }\n" +
                "        .player1 { background-color: red; }\n" +
                "        .player2 { background-color: yellow; }\n" +
                "        #status { margin: 20px; font-size: 24px; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Connect Four</h1>\n" +
                "    <div id=\"status\">Player 1's Turn</div>\n" +
                "    <div id=\"gameBoard\"></div>\n" +
                "    <script>\n" +
                "        document.addEventListener('DOMContentLoaded', function() {\n" +
                "            const gameBoard = document.getElementById('gameBoard');\n" +
                "            const statusDisplay = document.getElementById('status');\n" +
                "            \n" +
                "            // Initialize board\n" +
                "            for (let row = 0; row < 6; row++) {\n" +
                "                const rowDiv = document.createElement('div');\n" +
                "                rowDiv.className = 'row';\n" +
                "                for (let col = 0; col < 7; col++) {\n" +
                "                    const cell = document.createElement('div');\n" +
                "                    cell.className = 'cell';\n" +
                "                    cell.dataset.col = col;\n" +
                "                    cell.addEventListener('click', () => makeMove(col));\n" +
                "                    rowDiv.appendChild(cell);\n" +
                "                }\n" +
                "                gameBoard.appendChild(rowDiv);\n" +
                "            }\n" +
                "            \n" +
                "            // WebSocket connection\n" +
                "            const socket = new WebSocket(`ws://${window.location.host}/game`);\n" +
                "            \n" +
                "            socket.onmessage = function(event) {\n" +
                "                const gameState = JSON.parse(event.data);\n" +
                "                updateBoard(gameState);\n" +
                "                updateStatus(gameState.currentPlayer);\n" +
                "            };\n" +
                "            \n" +
                "            function makeMove(column) {\n" +
                "                if (socket.readyState === WebSocket.OPEN) {\n" +
                "                    socket.send(JSON.stringify({ column: column, player: 1 }));\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function updateBoard(gameState) {\n" +
                "                const cells = document.querySelectorAll('.cell');\n" +
                "                for (let row = 0; row < 6; row++) {\n" +
                "                    for (let col = 0; col < 7; col++) {\n" +
                "                        const index = row * 7 + col;\n" +
                "                        cells[index].className = 'cell';\n" +
                "                        if (gameState.board[row][col] === 1) {\n" +
                "                            cells[index].classList.add('player1');\n" +
                "                        } else if (gameState.board[row][col] === 2) {\n" +
                "                            cells[index].classList.add('player2');\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            function updateStatus(currentPlayer) {\n" +
                "                statusDisplay.textContent = `Player ${currentPlayer}'s Turn`;\n" +
                "            }\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static Set<Session> getSessions() {
        return sessions;
    }
}

@ServerEndpoint("/game")
class GameWebSocket {
    @OnOpen
    public void onOpen(Session session) {
        GameServlet.getSessions().add(session);
        try {
            session.getBasicRemote().sendText(GameServlet.getGameState().toJson().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        GameServlet.getSessions().remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        try {
            JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
            int column = jsonObject.getInt("column");
            int player = jsonObject.getInt("player");

            GameState gameState = GameServlet.getGameState();
            if (gameState.makeMove(column, player)) {
                String updatedState = gameState.toJson().toString();
                for (Session s : GameServlet.getSessions()) {
                    if (s.isOpen()) {
                        s.getBasicRemote().sendText(updatedState);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getBasicRemote().sendText("{\"error\":\"Invalid message format\"}");
        }
    }
}

class GameState {
    private final int ROWS = 6;
    private final int COLS = 7;
    private int[][] board = new int[ROWS][COLS];
    private int currentPlayer = 1;

    public boolean makeMove(int column, int player) {
        if (column < 0 || column >= COLS || player != currentPlayer) {
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == 0) {
                board[row][column] = player;
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
                return true;
            }
        }
        return false;
    }

    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder rowsBuilder = Json.createArrayBuilder();

        for (int i = 0; i < ROWS; i++) {
            JsonArrayBuilder colsBuilder = Json.createArrayBuilder();
            for (int j = 0; j < COLS; j++) {
                colsBuilder.add(board[i][j]);
            }
            rowsBuilder.add(colsBuilder);
        }

        builder.add("board", rowsBuilder);
        builder.add("currentPlayer", currentPlayer);

        return builder.build();
    }
}