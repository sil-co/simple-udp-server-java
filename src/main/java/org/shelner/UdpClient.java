package org.shelner;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public void sendToUdpServer() {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 8883;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress IPAddress = InetAddress.getByName(SERVER_ADDRESS);

            String message = "Hello from client!";
            byte[] sendData = message.getBytes();
            byte[] receiveData = new byte[1024];

            // Send packet to server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, SERVER_PORT);
            clientSocket.send(sendPacket);
            System.out.println("Send to server: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
