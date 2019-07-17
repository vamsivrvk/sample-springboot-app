/**
 * 
 */

'use strict';

var usernameForm;
var stompClient = null;
var username = "Vamsi";

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect() {
    var socket = new SockJS('/socket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
    
    //event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/chat', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/send/message",
        {},
        "connected!")
    )

    //connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(message) {
	//message = "hello"
   stompClient.send("/app/send/message", {}, message);
    //event.preventDefault();
}

