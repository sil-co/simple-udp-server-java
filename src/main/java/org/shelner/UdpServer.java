package org.shelner;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {
    public void runServer() {
        final int PORT = 8883;

        try (
                DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("Server is running and waiting for message...");

            while (true) {
                // Receive packet
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Receive from client: " + clientMessage);

                // Prepare response
                String response = "Hello from server!";
                sendData = response.getBytes();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
